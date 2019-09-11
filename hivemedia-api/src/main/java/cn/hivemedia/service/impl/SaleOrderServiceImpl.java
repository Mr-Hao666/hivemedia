package cn.hivemedia.service.impl;

import cn.hivemedia.dao.SaleOrderDao;
import cn.hivemedia.entity.model.OrderGoods;
import cn.hivemedia.entity.model.SaleOrderQueryDto;
import cn.hivemedia.entity.model.SellOrder;
import cn.hivemedia.utils.CommonUtils;
import cn.hivemedia.utils.Query;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.enums.BuyOrderStatus;
import cn.hivemedia.common.enums.SaleOrderStatus;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.entity.*;
import cn.hivemedia.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Service("saleOrderService")
public class SaleOrderServiceImpl extends ServiceImpl<SaleOrderDao, SaleOrderEntity> implements SaleOrderService {


    @Autowired
    private BuyOrderService buyOrderService;
    @Autowired
    private OrderGoodsDetailService orderGoodsDetailService;
    @Autowired
    private FeeConfigService feeConfigService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private SizeService sizeService;
    @Autowired
    private SysOssService ossService;
    @Autowired
    private SysConfigService configService;
    @Autowired
    private OrderLogisticsService orderLogisticsService;
    @Autowired
    private UserMessageService userMessageService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SaleOrderEntity> page = this.selectPage(
                new Query<SaleOrderEntity>(params).getPage(),
                new EntityWrapper<SaleOrderEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SaleOrderEntity createSaleOrder(SellOrder sellOrder) {

        SaleOrderEntity saleOrderEntity = new SaleOrderEntity();
        BeanUtils.copyProperties(sellOrder, saleOrderEntity);
        FeeConfigEntity feeConfigEntity = feeConfigService.selectOne(null);
        if (feeConfigEntity != null) {
            System.out.println(sellOrder);
            BigDecimal totalPrice = sellOrder.getGoodsPrice().add(saleOrderEntity.getPlatformFee());
            //保证金
            saleOrderEntity.setDeposit(sellOrder.getGoodsPrice().multiply(feeConfigEntity.getDepositPercent()));
            //平台费用
            saleOrderEntity.setPlatformFee(feeConfigEntity.getPlatformFee());
            //包装费
            saleOrderEntity.setPackFee(feeConfigEntity.getPacFee());
            //手续费
            saleOrderEntity.setProceduresFee(feeConfigEntity.getProceduresFee());
            //查验费
            saleOrderEntity.setGoodsCheckFee(feeConfigEntity.getGoodsCheckFee());
            //银行转账费
            saleOrderEntity.setBankFee(sellOrder.getGoodsPrice().multiply(feeConfigEntity.getBankFeePercent()));
            //订单支付总价
            saleOrderEntity.setOrderAmountTotal(saleOrderEntity.getBankFee().add(totalPrice));
            saleOrderEntity.setAmountTotal(sellOrder.getGoodsPrice());
        }
        saleOrderEntity.setOrderNo(CommonUtils.getOrderIdByTime());
        saleOrderEntity.setCreateTime(new Date());
        if (sellOrder.getSaleType().equals(1) || sellOrder.getSaleType().equals(2)) {
            saleOrderEntity.setOrderStatus(SaleOrderStatus.UNPAID_EPOSIT.getCode());
            baseMapper.insert(saleOrderEntity);
        } else if (sellOrder.getSaleType().equals(3)) {
            baseMapper.insert(saleOrderEntity);
            //TODO 立即变现相关买家订单校验
            //立即变现
            //修改求购最高求购价订单的状态为待收费状态
            BuyOrderEntity buyOrderEntity = new BuyOrderEntity();
            //待付款状态
            buyOrderEntity.setOrderStatus(BuyOrderStatus.PENDING_PAYMENT.getCode());
            buyOrderEntity.setId(sellOrder.getBuyOrderId().longValue());
            buyOrderEntity.setSaleUserId(sellOrder.getSaleUserId().intValue());
            buyOrderEntity.setSaleOrderId(saleOrderEntity.getId());
            buyOrderService.updateById(buyOrderEntity);
            //TODO 立即变现消息通知买家
        }

        //订单商品详情
        OrderGoodsDetailEntity orderGoodsDetailEntity = new OrderGoodsDetailEntity();
        orderGoodsDetailEntity.setCreatedTime(new Date());
        orderGoodsDetailEntity.setDesc(sellOrder.getDesc());
        orderGoodsDetailEntity.setGoodsId(sellOrder.getGoodsId().longValue());
        orderGoodsDetailEntity.setGoodsName(sellOrder.getGoodsName());
        orderGoodsDetailEntity.setGoodsPrice(sellOrder.getGoodsPrice());
        orderGoodsDetailEntity.setOrderId(saleOrderEntity.getId());
        orderGoodsDetailEntity.setOrderType(1);
        orderGoodsDetailService.insert(orderGoodsDetailEntity);
        SizeEntity sizeEntity = sizeService.selectById(sellOrder.getSizeId());
        saleOrderEntity.setSizeDesc(sizeEntity.getDesc());
        return saleOrderEntity;

    }

    @Override
    public List<SaleOrderQueryDto> purchaseList(Integer goodsId) {
        List<SaleOrderQueryDto> saleOrderQueryDtos = baseMapper.purchaseList(goodsId);
        if (CollectionUtils.isEmpty(saleOrderQueryDtos)) {
            return Collections.EMPTY_LIST;
        }
        return saleOrderQueryDtos;
    }

    /*
     * (non-Javadoc)
     *
     * @see SaleOrderService#queryMySale(java.lang.String,
     * java.lang.String, java.lang.Long)
     */
    @Override
    public Page<SaleOrderEntity> queryMySaleOrder(String pageNo, String pageSize, Long userId, List orderStatusList) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        Page<SaleOrderEntity> orderPage = this.selectPage(new Query<SaleOrderEntity>(params).getPage(),
                new EntityWrapper<SaleOrderEntity>().eq("sale_user_id", userId)
                        .eq("cur_state", 1)
                        .in("order_status", orderStatusList)
                        .orderBy("create_time",false));
        for (SaleOrderEntity saleOrderEntity : orderPage.getRecords()) {
            cover(saleOrderEntity);
        }
        return orderPage;
    }

    @Override
    public SaleOrderEntity detail(Integer saleOrderId) {
        SaleOrderEntity saleOrderEntity = this.selectById(saleOrderId);
        cover(saleOrderEntity);
        OrderLogisticsEntity orderLogisticsEntity = orderLogisticsService.selectBySaleOrderId(saleOrderId);
        saleOrderEntity.setOrderLogistics(orderLogisticsEntity);
        return saleOrderEntity;
    }

    private void cover(SaleOrderEntity saleOrderEntity) {
        //商品名称 商品图片
        GoodsEntity goodsEntity = goodsService.selectById(saleOrderEntity.getGoodsId());
        saleOrderEntity.setPicShowUrl(ossService.selectById(goodsEntity.getPicShow()).getUrl());
        saleOrderEntity.setGoodsName(goodsEntity.getName());
        SizeEntity sizeEntity = sizeService.selectById(saleOrderEntity.getSizeId());
        saleOrderEntity.setSizeDesc(sizeEntity.getDesc());
    }

    @Override
    public Page<OrderGoods> queryListByGoodsId(Integer goodsId, Integer sizeId, Integer pageNo, Integer pageSize, Integer orderStatus) {
        Page<OrderGoods> page = new Page<>(pageNo, pageSize);

        return page.setRecords(this.baseMapper.queryListByGoodsId(page, goodsId, sizeId, orderStatus));

    }

    @Override
    public Page<OrderGoods> queryListAllByGoodsId(Integer goodsId, Integer sizeId, Integer pageNo, Integer pageSize) {
        Page<OrderGoods> page = new Page<>(pageNo, pageSize);

        return page.setRecords(this.baseMapper.queryListAllByGoodsId(page, goodsId, sizeId));
    }

    @Override
    public Boolean saleOrderPayRedirect(Integer orderId, Integer payChannel) {
        /**
         * 查询订单数据
         */
        SaleOrderEntity saleOrderEntity = baseMapper.selectById(orderId);
        //判断订单是否存在
        if (saleOrderEntity == null) {
            return false;
        }
        /**
         * 保证金支付
         */
        saleOrderEntity.setOrderStatus(SaleOrderStatus.IN_SALE.getCode());
        saleOrderEntity.setPayChannel(payChannel);
        log.info("卖家支付出售订单保证金");
        saleOrderEntity.setOrderStatus(SaleOrderStatus.IN_SALE.getCode());
        this.baseMapper.updateById(saleOrderEntity);
        return true;
    }

    @Override
    public Boolean cancelSaleOrder(Integer orderId) {
        SaleOrderEntity saleOrderEntity = baseMapper.selectById(orderId);
        //订单状态为取消
        saleOrderEntity.setOrderStatus(SaleOrderStatus.CANCEL_TRANSACTION.getCode());
        return this.updateById(saleOrderEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deliverGoods(Integer orderId, String returnConsigneeAddress, String returnConsigneePhone, String returnConsigneeName,String expressNo){
        SaleOrderEntity saleOrderEntity = baseMapper.selectById(orderId);
        //订单状态为已发货
        saleOrderEntity.setOrderStatus(SaleOrderStatus.SHIPPED.getCode());
        this.updateById(saleOrderEntity);
        BuyOrderEntity buyOrderEntity = buyOrderService.selectById(saleOrderEntity.getBuyOrderId());
        buyOrderEntity.setOrderStatus(BuyOrderStatus.SHIPPED_PLATFORM.getCode());
        buyOrderService.updateById(buyOrderEntity);
        //修改订单物流信息
        OrderLogisticsEntity orderLogisticsEntity = new OrderLogisticsEntity();
        orderLogisticsEntity.setReturnConsigneeAddress(returnConsigneeAddress);
        orderLogisticsEntity.setReturnConsigneeRealname(returnConsigneeName);
        orderLogisticsEntity.setReturnConsigneeTelphone(returnConsigneePhone);
        orderLogisticsEntity.setConsigneeAddress(configService.getValue("platform_address"));
        orderLogisticsEntity.setConsigneeRealname(configService.getValue("platform_name"));
        orderLogisticsEntity.setConsigneeTelphone(configService.getValue("platform_phone"));
        orderLogisticsEntity.setExpressNo(expressNo);
        orderLogisticsEntity.setOrderType(1);
        orderLogisticsEntity.setOrderId(orderId.longValue());
        orderLogisticsService.insert(orderLogisticsEntity);
        //发货消息通知
        userMessageService.orderNotify(buyOrderEntity.getId(), BuyOrderStatus.SHIPPED_PLATFORM.getCode());
        return true;
    }

    @Override
    public Boolean delSaleOrder(Integer orderId) {
        SaleOrderEntity saleOrderEntity = baseMapper.selectById(orderId);
        //修改状态为无效
        saleOrderEntity.setCurState(2);
        return this.updateById(saleOrderEntity);
    }
}
