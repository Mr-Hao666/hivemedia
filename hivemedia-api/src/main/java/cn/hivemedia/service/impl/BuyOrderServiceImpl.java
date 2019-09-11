package cn.hivemedia.service.impl;

import cn.hivemedia.dao.BuyOrderDao;
import cn.hivemedia.utils.CollectionUtil;
import cn.hivemedia.utils.CommonUtils;
import cn.hivemedia.utils.Query;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.enums.BuyOrderStatus;
import cn.hivemedia.common.enums.SaleOrderStatus;
import cn.hivemedia.common.enums.UserCouponStatus;
import cn.hivemedia.common.exception.RRException;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.entity.*;
import cn.hivemedia.entity.model.*;
import cn.hivemedia.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;


@Service("buyOrderService")
@Slf4j
public class BuyOrderServiceImpl extends ServiceImpl<BuyOrderDao, BuyOrderEntity> implements BuyOrderService {

    @Autowired
    private OrderGoodsDetailService orderGoodsDetailService;
    @Autowired
    private OrderLogisticsService orderLogisticsService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private SysOssService ossService;
    @Autowired
    private SizeService sizeService;
    @Autowired
    private SaleOrderService saleOrderService;
    @Autowired
    private FeeConfigService feeConfigService;
    @Autowired
    private UserMessageService userMessageService;
    @Autowired
    private UserCouponService userCouponService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BuyOrderEntity> page = this.selectPage(
                new Query<BuyOrderEntity>(params).getPage(),
                new EntityWrapper<BuyOrderEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Page<OrderGoods> queryListByGoodsId(Integer goodsId, Integer buyType, Integer sizeId, Integer pageNo, Integer pageSize, Integer orderStatus) {
//        PageHelper.startPage(pageNo, pageSize);

        Page<OrderGoods> page = new Page<>(pageNo, pageSize);
//        Map<String, Object> map = new HashMap<>();
//        map.put("goodsId", goodsId);
//        map.put("buyType", buyType);
//        map.put("sizeId", sizeId);
//        page.setCondition(map);

        return page.setRecords(this.baseMapper.queryListByGoodsId(page, goodsId, buyType, sizeId, orderStatus));

    }

    @Override
    public List<OrderGoods> queryLastFiveOrderByGoodsId(Integer goodsId, Integer buyType, Integer sizeId) {
        return this.baseMapper.queryLastFiveOrderByGoodsId(goodsId, buyType, sizeId);
    }


    @Override
    public List<SaleGoods> querySaleGoodsAmountOrderBySize(Integer goodsId) {
        List<SaleGoods> saleGoods = baseMapper.querySaleGoodsAmountOrderBySize(goodsId);
        if (saleGoods != null && !saleGoods.isEmpty()) {
            for (SaleGoods saleGoods1 : saleGoods) {
                saleGoods1.validate();
                SaleGoods saleGoods2 = baseMapper.queryBuyOrderAmountByGoodsSize(goodsId, saleGoods1.getSizeId());
                saleGoods1.setAmountMin(saleGoods2.getAmountTotal());
            }
            return saleGoods;
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public List<SaleGoods> queryBuyGoodsAmountOrderBySize(Integer goodsId) {
        List<SaleGoods> saleGoods = baseMapper.queryBuyGoodsAmountOrderBySize(goodsId);
        if (CollectionUtils.isEmpty(saleGoods)) {
            return Collections.EMPTY_LIST;
        }
        return saleGoods;

    }

    @Override
    public SaleGoods queryBuyOrderAmountByGoodsSize(Integer goodsId, Integer sizeId) {

        return baseMapper.queryBuyOrderAmountByGoodsSize(goodsId, sizeId);

    }

    @Override
    public List<OfferPurchaseDto> offerPurchaseList(Integer goodsId) {
        List<OfferPurchaseDto> offerPurchaseDtos = baseMapper.offerPurchaseList(goodsId);
        if (CollectionUtils.isEmpty(offerPurchaseDtos)) {
            return Collections.EMPTY_LIST;
        }
        //最低售价
        List<SaleGoods> saleGoods = baseMapper.queryBuyGoodsAmountOrderBySize(goodsId);
        Map<Integer, SaleGoods> saleGoodsMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(saleGoods)) {
            saleGoodsMap = CollectionUtil.listToMap(saleGoods, SaleGoods::getSizeId);
        }
        Map<Integer, SaleGoods> finalSaleGoodsMap = saleGoodsMap;
        offerPurchaseDtos.forEach(offerPurchaseDto -> {
            SaleGoods saleGoods1 = finalSaleGoodsMap.get(offerPurchaseDto.getSizeId());
            if (saleGoods1 != null) {
                offerPurchaseDto.setMinAmountTotal(saleGoods1.getAmountTotal());
            }
            offerPurchaseDto.validate();
        });

        return offerPurchaseDtos;
    }

    @Override
    public BuyOrderEntity buyerCreateOrder(BuyOrder buyOrder) {
        BuyOrderEntity buyOrderEntity = new BuyOrderEntity();
        GoodsEntity goodsEntity = goodsService.selectById(buyOrder.getGoodsId());
        BeanUtils.copyProperties(buyOrder, buyOrderEntity);
        if (buyOrder.getSaleOrderId() != null && saleOrderService.selectById(buyOrder.getSaleOrderId()) != null) {
            OrderGoodsDetailEntity orderGoodsDetailEntity = new OrderGoodsDetailEntity();
            FeeConfigEntity feeConfigEntity = feeConfigService.selectOne(null);
            if (feeConfigEntity != null) {
                //保证金
                buyOrderEntity.setDeposit(buyOrder.getAmountTotal().multiply(feeConfigEntity.getDepositPercent()));
                buyOrderEntity.setExpressFee(feeConfigEntity.getExpressFee());
                //优惠券使用记录暂无
                if (buyOrder.getCouponId() != null && buyOrder.getCouponId() > 0) {
                    UserCouponEntity userCouponEntity = userCouponService.selectById(buyOrder.getCouponId());
                    if (userCouponEntity.getUserId().equals(buyOrder.getUserId()) &&
                            userCouponEntity.getStatus().equals(UserCouponStatus.NOT_USED.getCode())) {
                        userCouponEntity.setStatus(UserCouponStatus.USED.getCode());
                        buyOrderEntity.setCouponId(buyOrder.getCouponId());
                    } else {
                        throw new RRException("优惠券无效");
                    }
                }

                //判断是否包邮
                if (goodsEntity.getIsFreePost().equals(1)) {
                    // 方便前端调试暂时注释订单总价
                    buyOrderEntity.setOrderAmountTotal(buyOrder.getAmountTotal());
                    orderGoodsDetailEntity.setIsFreePost(1);
                } else {
                    // 方便前端调试暂时注释订单总价
                    buyOrderEntity.setOrderAmountTotal(buyOrder.getAmountTotal().add(feeConfigEntity.getExpressFee()));
                    orderGoodsDetailEntity.setIsFreePost(0);
                }

            }
            buyOrderEntity.setSaleUserId(buyOrder.getSaleUserId().intValue());
            buyOrderEntity.setOrderNo(CommonUtils.getOrderIdByTime());
            buyOrderEntity.setCreateTime(new Date());
            buyOrderEntity.setBuyUserId(buyOrder.getUserId().longValue());
            buyOrderEntity.setOrderStatus(BuyOrderStatus.PENDING_PAYMENT.getCode());
            buyOrderEntity.setBuyType(1);
            this.insert(buyOrderEntity);
            //订单商品详情
            orderGoodsDetailEntity.setCreatedTime(new Date());
            orderGoodsDetailEntity.setDesc(buyOrder.getDesc());
            orderGoodsDetailEntity.setGoodsId(Long.parseLong(buyOrder.getGoodsId()));
            orderGoodsDetailEntity.setGoodsName(buyOrder.getGoodsName());
            orderGoodsDetailEntity.setGoodsPrice(buyOrder.getAmountTotal());
            orderGoodsDetailEntity.setOrderId(buyOrderEntity.getId());
            orderGoodsDetailEntity.setOrderType(2);
            orderGoodsDetailEntity.setPicShow(goodsEntity.getPicShow());
            orderGoodsDetailService.insert(orderGoodsDetailEntity);
            //订单物流信息
            OrderLogisticsEntity orderLogisticsEntity = new OrderLogisticsEntity();
            orderLogisticsEntity.setOrderId(buyOrderEntity.getId());
            orderLogisticsEntity.setConsigneeAddress(buyOrder.getConsigneeAddress());
            orderLogisticsEntity.setConsigneeRealname(buyOrder.getConsigneeRealname());
            orderLogisticsEntity.setConsigneeTelphone(buyOrder.getConsigneeTelphone());
            orderLogisticsEntity.setOrderType(2);
            orderLogisticsService.insert(orderLogisticsEntity);
        } else {
            throw new RRException("卖家订单不存在");
        }
        return buyOrderEntity;
    }

    @Override
    public BuyOrderEntity offerPurchaseCreateOrder(BuyOrder buyOrder) {
        BuyOrderEntity buyOrderEntity = new BuyOrderEntity();
        BeanUtils.copyProperties(buyOrder, buyOrderEntity);
        buyOrderEntity.setSaleUserId(buyOrder.getSaleUserId().intValue());
        buyOrderEntity.setOrderNo(CommonUtils.getOrderIdByTime());
        buyOrderEntity.setCreateTime(new Date());
        buyOrderEntity.setBuyUserId(buyOrder.getUserId().longValue());
        buyOrderEntity.setOrderStatus(BuyOrderStatus.UNPAID_EPOSIT.getCode());
        buyOrderEntity.setBuyType(2);
        //TODO 计算实际订单价格
        this.insert(buyOrderEntity);
        //订单商品详情
        OrderGoodsDetailEntity orderGoodsDetailEntity = new OrderGoodsDetailEntity();
        orderGoodsDetailEntity.setCreatedTime(new Date());
        orderGoodsDetailEntity.setDesc(buyOrder.getDesc());
        orderGoodsDetailEntity.setGoodsId(Long.parseLong(buyOrder.getGoodsId()));
        orderGoodsDetailEntity.setGoodsName(buyOrder.getGoodsName());
        orderGoodsDetailEntity.setGoodsPrice(buyOrder.getAmountTotal());
        orderGoodsDetailEntity.setOrderId(buyOrderEntity.getId());
        orderGoodsDetailEntity.setOrderType(2);
        orderGoodsDetailService.insert(orderGoodsDetailEntity);
        //订单物流信息
        OrderLogisticsEntity orderLogisticsEntity = new OrderLogisticsEntity();
        orderLogisticsEntity.setOrderId(buyOrderEntity.getId());
        orderLogisticsEntity.setConsigneeAddress(buyOrder.getConsigneeAddress());
        orderLogisticsEntity.setConsigneeRealname(buyOrder.getConsigneeRealname());
        orderLogisticsEntity.setConsigneeTelphone(buyOrder.getConsigneeTelphone());
        orderLogisticsEntity.setOrderType(2);
        orderLogisticsService.insert(orderLogisticsEntity);
        return buyOrderEntity;
    }

    @Override
    public BuyOrderEntity selectLatestBuyOrder(Integer goodsId) {
        return baseMapper.selectLatestBuyOrder(goodsId);
    }


    @Override
    public Page<BuyOrderEntity> queryMyBuyOrder(String pageNo, String pageSize, Long userId, List orderStatusList) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        Page<BuyOrderEntity> orderPage = this.selectPage(new Query<BuyOrderEntity>(params).getPage(),
                new EntityWrapper<BuyOrderEntity>().eq("buy_user_id", userId)
                        .eq("cur_state", 1)
                        .in("order_status", orderStatusList)
                        .orderBy("create_time", false));
        for (BuyOrderEntity buyOrderEntity : orderPage.getRecords()) {
            cover(buyOrderEntity);
        }
        return orderPage;
    }

    @Override
    public BuyOrderEntity detail(Integer buyOrderId) {
        BuyOrderEntity buyOrderEntity = this.selectById(buyOrderId);
        //收货人姓名 手机号
        Wrapper<OrderLogisticsEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("order_id", buyOrderId).eq("order_type", 2);
        OrderLogisticsEntity orderLogisticsEntity = orderLogisticsService.selectOne(wrapper);
        if (orderLogisticsEntity != null) {
            buyOrderEntity.setExpressNo(orderLogisticsEntity.getExpressNo());
            buyOrderEntity.setConsigneeRealname(orderLogisticsEntity.getConsigneeRealname());
            buyOrderEntity.setConsigneeAddress(orderLogisticsEntity.getConsigneeAddress());
            buyOrderEntity.setConsigneeTelphone(orderLogisticsEntity.getConsigneeTelphone());
        }
        cover(buyOrderEntity);
        return buyOrderEntity;
    }

    private void cover(BuyOrderEntity buyOrderEntity) {
        //商品名称 商品图片
        GoodsEntity goodsEntity = goodsService.selectById(buyOrderEntity.getGoodsId());
        if (goodsEntity != null) {
            buyOrderEntity.setPicShowUrl(ossService.selectById(goodsEntity.getPicShow()).getUrl());
            buyOrderEntity.setGoodsName(goodsEntity.getName());
            SizeEntity sizeEntity = sizeService.selectById(buyOrderEntity.getSizeId());
            buyOrderEntity.setSizeDesc(sizeEntity.getDesc());
        }
        if (buyOrderEntity.getSaleOrderId() != null) {
            SaleOrderEntity saleOrderEntity = saleOrderService.selectById(buyOrderEntity.getSaleOrderId());
            buyOrderEntity.setSaleType(saleOrderEntity == null ? null : saleOrderEntity.getSaleType());
        }
    }

    @Override
    public GoodsOfferPurchase getGoodsOfferPurchase(Integer goodsId, Integer sizeId) {
        return baseMapper.getGoodsOfferPurchase(goodsId, sizeId);
    }


    @Override
    public Boolean buyOrderPayRedirect(Integer orderId, Integer payType, Integer payChannel) {
        log.info("orderId:"+orderId);
        log.info("payType:"+payType);
        /**
         * 查询订单数据
         */
        BuyOrderEntity buyOrderEntity = baseMapper.selectById(orderId);
        //判断订单是否存在
        if (buyOrderEntity == null) {
            return false;
        }
        log.info("buyOrderEntity:"+buyOrderEntity);

        buyOrderEntity.setPayChannel(payChannel);


        /**
         * 保证金支付
         */
        if (payType == 1) {
            buyOrderEntity.setOrderStatus(BuyOrderStatus.IN_BUY.getCode());
            log.info("买家支付保证金成功");
        }
        /**
         * 订单金额支付
         */
        if (payType == 2) {
            buyOrderEntity.setPayTime(new Date());
            buyOrderEntity.setOrderStatus(BuyOrderStatus.PAID.getCode());
            userMessageService.orderNotify(buyOrderEntity.getId(), BuyOrderStatus.PAID.getCode());
        }
        this.baseMapper.updateById(buyOrderEntity);

        /**
         * 非保证金支付订单支付成功之后需要修改出售订单是否已售出的状态
         */
        if (payType == 2) {
            /**
             * 查询对应出售订单
             */
            SaleOrderEntity saleOrderEntity = saleOrderService.selectById(buyOrderEntity.getSaleOrderId());
            saleOrderEntity.setOrderStatus(SaleOrderStatus.PAID.getCode());
            saleOrderEntity.setPayTime(new Date());
            saleOrderEntity.setBuyOrderId(buyOrderEntity.getId().intValue());
            saleOrderEntity.setBuyUserId(buyOrderEntity.getBuyUserId().intValue());
            log.info("买家支付尾款成功");
            //出售订单是否出售状态为已出售
            saleOrderEntity.setIsSale(true);
            //TODO 支付成功后通知卖家发货
            saleOrderService.updateById(saleOrderEntity);
        }
        return true;
    }

    @Override
    public Boolean signOrder(Integer orderId) {
        BuyOrderEntity buyOrderEntity = baseMapper.selectById(orderId);
        //订单状态为已签收
        buyOrderEntity.setOrderStatus(BuyOrderStatus.ALREADY_SIGNED.getCode());
        userMessageService.orderNotify(buyOrderEntity.getId(), BuyOrderStatus.ALREADY_SIGNED.getCode());
        return this.updateById(buyOrderEntity);
    }

    @Override
    public Boolean cancelBuyOrder(Integer orderId) {
        BuyOrderEntity buyOrderEntity = baseMapper.selectById(orderId);
        //订单状态为取消
        buyOrderEntity.setOrderStatus(BuyOrderStatus.CANCEL_TRANSACTION.getCode());
        userMessageService.orderNotify(buyOrderEntity.getId(), BuyOrderStatus.CANCEL_TRANSACTION.getCode());
        return this.updateById(buyOrderEntity);
    }

    @Override
    public Boolean delBuyOrder(Integer orderId) {
        BuyOrderEntity buyOrderEntity = baseMapper.selectById(orderId);
        //删除状态
        buyOrderEntity.setCurState(2);
        return this.updateById(buyOrderEntity);
    }

    @Override
    public Boolean deliverGoods(Integer orderId, String expressNo) {
        BuyOrderEntity buyOrderEntity = baseMapper.selectById(orderId);
        //订单状态为已发货
        buyOrderEntity.setOrderStatus(BuyOrderStatus.SHIPPED_PLATFORM.getCode());
        buyOrderEntity.setUpdateTime(new Date());
        this.updateById(buyOrderEntity);
        //修改订单物流信息
        OrderLogisticsEntity orderLogisticsEntity = new OrderLogisticsEntity();
        orderLogisticsEntity.setOrderType(2);
        orderLogisticsEntity.setExpressNo(expressNo);
        orderLogisticsService.updateOrderLogistics(orderLogisticsEntity);
        //发货消息通知
        userMessageService.orderNotify(buyOrderEntity.getId(), BuyOrderStatus.SHIPPED_PLATFORM.getCode());
        return true;
    }

}
