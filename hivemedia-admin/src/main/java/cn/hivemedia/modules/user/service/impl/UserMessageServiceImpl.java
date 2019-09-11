package cn.hivemedia.modules.user.service.impl;

import cn.jpush.api.push.PushResult;
import cn.hivemedia.modules.goods.entity.GoodsEntity;
import cn.hivemedia.modules.goods.service.GoodsService;
import cn.hivemedia.modules.order.dao.BuyOrderDao;
import cn.hivemedia.modules.order.dao.OrderGoodsDetailDao;
import cn.hivemedia.modules.order.dao.SaleOrderDao;
import cn.hivemedia.modules.order.service.BuyOrderService;
import cn.hivemedia.modules.order.service.SaleOrderService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.enums.BiuGoodsMsgTypeEnum;
import cn.hivemedia.common.enums.MessageTypeEnum;
import cn.hivemedia.common.utils.JPushUtils;
import cn.hivemedia.common.utils.JsonUtil;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.Query;
import cn.hivemedia.modules.order.entity.*;
import cn.hivemedia.modules.oss.entity.SysOssEntity;
import cn.hivemedia.modules.oss.service.SysOssService;
import cn.hivemedia.modules.user.dao.UserMessageDao;
import cn.hivemedia.modules.user.entity.UserMessageEntity;
import cn.hivemedia.modules.user.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("userMessageService")
public class UserMessageServiceImpl extends ServiceImpl<UserMessageDao, UserMessageEntity> implements UserMessageService {

    @Autowired
    private BuyOrderService buyOrderService;
    @Autowired
    private OrderGoodsDetailDao orderGoodsDetailDao;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private SysOssService sysOssService;
    @Autowired
    private SaleOrderService saleOrderService;
    @Autowired
    private SaleOrderDao saleOrderDao;
    @Autowired
    private BuyOrderDao buyOrderDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<UserMessageEntity> page = this.selectPage(
                new Query<UserMessageEntity>(params).getPage(),
                new EntityWrapper<UserMessageEntity>().eq("user_id", params.get("user_id"))
                        .and().ne("status", 3)
                        .and().eq("type", params.get("type"))
                        .orderBy("id", false)
        );

        return new PageUtils(page);
    }

    @Override
    @Async("asyncServiceExecutor")
    public void orderNotify(Long orderId, Integer orderStatus) {

        BuyOrderEntity buyOrderEntity = buyOrderService.selectById(orderId);
        if (buyOrderEntity!=null){
            Date sendTime = new Date();
            OrderGoodsDetailEntity orderGoodsDetailEntity = orderGoodsDetailDao.selectByOrderId(orderId,2);
            GoodsEntity goodsEntity = goodsService.selectById(buyOrderEntity.getGoodsId());
            OrderMsgDto orderMsgDto = new OrderMsgDto();
            orderMsgDto.setGoodsId(Long.parseLong(buyOrderEntity.getGoodsId()));
            orderMsgDto.setGoodsName(orderGoodsDetailEntity.getGoodsName());
            orderMsgDto.setOrderId(orderId);
            orderMsgDto.setOrderStatus(orderStatus);
            SysOssEntity sysOssEntity = sysOssService.selectById(goodsEntity.getPicShow());
            orderMsgDto.setPicShowUrl(sysOssEntity.getUrl());
            orderMsgDto.setSendTime(sendTime);
            orderMsgDto.setSizeDesc(orderGoodsDetailEntity.getDesc());
            orderMsgDto.setSizeId(Long.parseLong(buyOrderEntity.getSizeId()));
            MessageDto messageDto = new MessageDto();
            messageDto.setMsgType(MessageTypeEnum.ORDER_NOTIFY.getCode());
            messageDto.setOrderMsgDto(orderMsgDto);

            UserMessageEntity userMessageEntity = new UserMessageEntity();
            PushResult result = JPushUtils.push(buyOrderEntity.getBuyUserId().toString(), JsonUtil.toString(messageDto));

            userMessageEntity.setMessageId(result.msg_id);
            userMessageEntity.setTitle("订单通知");
            userMessageEntity.setContent(JsonUtil.toString(messageDto));
            userMessageEntity.setCreatedTime(sendTime);
            userMessageEntity.setUserId(buyOrderEntity.getBuyUserId());
            userMessageEntity.setType(MessageTypeEnum.ORDER_NOTIFY.getCode()+"");
            userMessageEntity.setCreatedTime(sendTime);
            baseMapper.insert(userMessageEntity);
        }
    }

    /**
     * 出售提醒：当用户有出售订单时，该订单对应的尺码产品有更低售价时，推送提醒
     * @param saleOrderId
     */
    @Override
    @Async("asyncServiceExecutor")
    public void biuGoodsSaleNotify(Long saleOrderId) {
        SaleOrderEntity saleOrderEntity = saleOrderService.selectById(saleOrderId);
        if (saleOrderEntity!=null){
            List<SaleOrderEntity> saleOrderEntities = saleOrderDao.selectPriceOrdersBiMinPrice(saleOrderEntity.getAmountTotal(),saleOrderEntity.getGoodsId(),saleOrderEntity.getSizeId());
            if (!CollectionUtils.isEmpty(saleOrderEntities)){
                OrderGoodsDetailEntity orderGoodsDetailEntity = orderGoodsDetailDao.selectByOrderId(saleOrderId,1);
                GoodsEntity goodsEntity = goodsService.selectById(saleOrderEntity.getGoodsId());
                SysOssEntity sysOssEntity = sysOssService.selectById(goodsEntity.getPicShow());
                saleOrderEntities.forEach(saleOrderEntity1 -> {
                    BiuGoodsMsgDto biuGoodsMsgDto = new BiuGoodsMsgDto();
                    biuGoodsMsgDto.setAmountTotal(saleOrderEntity1.getAmountTotal());
                    biuGoodsMsgDto.setSaleAmountTotal(saleOrderEntity.getAmountTotal());
                    biuGoodsMsgDto.setSenderAvatar("");
                    biuGoodsMsgDto.setPicShowUrl(sysOssEntity.getUrl());
                    biuGoodsMsgDto.setGoodsId(saleOrderEntity.getGoodsId().longValue());
                    biuGoodsMsgDto.setGoodsName(goodsEntity.getName());
                    biuGoodsMsgDto.setBiuGoodsMsgType(BiuGoodsMsgTypeEnum.BIU_GOODS_SALE_NOTIFY.getCode());
                    biuGoodsMsgDto.setSaleOrderId(saleOrderEntity1.getId());
                    biuGoodsMsgDto.setSizeDesc(orderGoodsDetailEntity.getDesc());
                    biuGoodsMsgDto.setSizeId(saleOrderEntity.getSizeId().longValue());

                    MessageDto messageDto = new MessageDto();
                    messageDto.setMsgType(MessageTypeEnum.BIU_GOODS.getCode());
                    messageDto.setBiuGoodsMsgDto(biuGoodsMsgDto);

                    Date sendTime = new Date();
                    UserMessageEntity userMessageEntity = new UserMessageEntity();
                    PushResult result = JPushUtils.push(saleOrderEntity1.getSaleUserId().toString(),JsonUtil.toString(messageDto));
                    userMessageEntity.setMessageId(result.msg_id);
                    userMessageEntity.setTitle("Biu好货");
                    userMessageEntity.setContent(JsonUtil.toString(messageDto));
                    userMessageEntity.setCreatedTime(sendTime);
                    userMessageEntity.setUserId(saleOrderEntity1.getSaleUserId().longValue());
                    userMessageEntity.setType(MessageTypeEnum.BIU_GOODS.getCode()+"");
                    userMessageEntity.setCreatedTime(sendTime);
                    baseMapper.insert(userMessageEntity);
                });
            }
        }
    }

    /**
     * 变现提醒：当出售订单产品有求购价接近出售价时（100以内），推送提醒
     * @param buyOrderId
     */
    @Override
    @Async("asyncServiceExecutor")
    public void liquidationNotify(Long buyOrderId) {
        BuyOrderEntity buyOrderEntity = buyOrderService.selectById(buyOrderId);
        if (buyOrderEntity!=null){
            List<SaleOrderEntity> saleOrderEntities = saleOrderDao.selectByPurchasePrice(buyOrderEntity.getAmountTotal(),Integer.parseInt(buyOrderEntity.getGoodsId()),Integer.parseInt(buyOrderEntity.getSizeId()));
            if (!CollectionUtils.isEmpty(saleOrderEntities)){
                OrderGoodsDetailEntity orderGoodsDetailEntity = orderGoodsDetailDao.selectByOrderId(buyOrderId,2);
                GoodsEntity goodsEntity = goodsService.selectById(buyOrderEntity.getGoodsId());
                SysOssEntity sysOssEntity = sysOssService.selectById(goodsEntity.getPicShow());
                saleOrderEntities.forEach(saleOrderEntity1 -> {
                    BiuGoodsMsgDto biuGoodsMsgDto = new BiuGoodsMsgDto();
                    biuGoodsMsgDto.setAmountTotal(saleOrderEntity1.getAmountTotal());
                    biuGoodsMsgDto.setSaleAmountTotal(buyOrderEntity.getAmountTotal());
                    biuGoodsMsgDto.setSenderAvatar("");
                    biuGoodsMsgDto.setPicShowUrl(sysOssEntity.getUrl());
                    biuGoodsMsgDto.setGoodsId(Long.parseLong(buyOrderEntity.getGoodsId()));
                    biuGoodsMsgDto.setGoodsName(goodsEntity.getName());
                    biuGoodsMsgDto.setBiuGoodsMsgType(BiuGoodsMsgTypeEnum.LIQUIDATION_NOTIFY.getCode());
                    biuGoodsMsgDto.setSaleOrderId(saleOrderEntity1.getId());
                    biuGoodsMsgDto.setSizeDesc(orderGoodsDetailEntity.getDesc());
                    biuGoodsMsgDto.setSizeId(Long.parseLong(buyOrderEntity.getSizeId()));

                    MessageDto messageDto = new MessageDto();
                    messageDto.setMsgType(MessageTypeEnum.BIU_GOODS.getCode());
                    messageDto.setBiuGoodsMsgDto(biuGoodsMsgDto);

                    Date sendTime = new Date();
                    UserMessageEntity userMessageEntity = new UserMessageEntity();
                    PushResult result = JPushUtils.push(saleOrderEntity1.getSaleUserId().toString(),JsonUtil.toString(messageDto));
                    userMessageEntity.setMessageId(result.msg_id);
                    userMessageEntity.setTitle("Biu好货");
                    userMessageEntity.setContent(JsonUtil.toString(messageDto));
                    userMessageEntity.setCreatedTime(sendTime);
                    userMessageEntity.setUserId(saleOrderEntity1.getSaleUserId().longValue());
                    userMessageEntity.setType(MessageTypeEnum.BIU_GOODS.getCode()+"");
                    userMessageEntity.setCreatedTime(sendTime);
                    baseMapper.insert(userMessageEntity);
                });
            }
        }
    }

    /**
     * 更高求购价提醒：当求购订单产品有更高的求购价时，推送提醒
     * @param buyOrderId
     */
    @Override
    @Async("asyncServiceExecutor")
    public void maxPurchasePriceNotify(Long buyOrderId) {
        BuyOrderEntity buyOrderEntity = buyOrderService.selectById(buyOrderId);
        if (buyOrderEntity!=null){
            BigDecimal maxPurchasePrice = buyOrderDao.selectMaxPurchasePrice(Integer.parseInt(buyOrderEntity.getGoodsId()),Integer.parseInt(buyOrderEntity.getSizeId()));
            if (buyOrderEntity.getAmountTotal().compareTo(maxPurchasePrice)==0){
                List<SaleOrderEntity> saleOrderEntities = saleOrderDao.selectSaleOrderList(Integer.parseInt(buyOrderEntity.getGoodsId()),Integer.parseInt(buyOrderEntity.getSizeId()));
                if (!CollectionUtils.isEmpty(saleOrderEntities)){
                    OrderGoodsDetailEntity orderGoodsDetailEntity = orderGoodsDetailDao.selectByOrderId(buyOrderId,1);
                    GoodsEntity goodsEntity = goodsService.selectById(buyOrderEntity.getGoodsId());
                    SysOssEntity sysOssEntity = sysOssService.selectById(goodsEntity.getPicShow());
                    saleOrderEntities.forEach(saleOrderEntity1 -> {
                        BiuGoodsMsgDto biuGoodsMsgDto = new BiuGoodsMsgDto();
                        biuGoodsMsgDto.setAmountTotal(saleOrderEntity1.getAmountTotal());
                        biuGoodsMsgDto.setSaleAmountTotal(maxPurchasePrice);
                        biuGoodsMsgDto.setSenderAvatar("");
                        biuGoodsMsgDto.setPicShowUrl(sysOssEntity.getUrl());
                        biuGoodsMsgDto.setGoodsId(Long.parseLong(buyOrderEntity.getGoodsId()));
                        biuGoodsMsgDto.setGoodsName(goodsEntity.getName());
                        biuGoodsMsgDto.setBiuGoodsMsgType(BiuGoodsMsgTypeEnum.MAX_PRICE.getCode());
                        biuGoodsMsgDto.setSaleOrderId(saleOrderEntity1.getId());
                        biuGoodsMsgDto.setSizeDesc(orderGoodsDetailEntity.getDesc());
                        biuGoodsMsgDto.setSizeId(Long.parseLong(buyOrderEntity.getSizeId()));

                        MessageDto messageDto = new MessageDto();
                        messageDto.setMsgType(MessageTypeEnum.BIU_GOODS.getCode());
                        messageDto.setBiuGoodsMsgDto(biuGoodsMsgDto);

                        Date sendTime = new Date();
                        UserMessageEntity userMessageEntity = new UserMessageEntity();
                        PushResult result = JPushUtils.push(saleOrderEntity1.getSaleUserId().toString(),JsonUtil.toString(messageDto));
                        userMessageEntity.setMessageId(result.msg_id);
                        userMessageEntity.setTitle("Biu好货");
                        userMessageEntity.setContent(JsonUtil.toString(messageDto));
                        userMessageEntity.setCreatedTime(sendTime);
                        userMessageEntity.setUserId(saleOrderEntity1.getSaleUserId().longValue());
                        userMessageEntity.setType(MessageTypeEnum.BIU_GOODS.getCode()+"");
                        userMessageEntity.setCreatedTime(sendTime);
                        baseMapper.insert(userMessageEntity);
                    });
                }
            }
        }
    }

}
