package cn.hivemedia.modules.user.service;

import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.modules.user.entity.UserMessageEntity;

import java.util.Map;

/**
 * 用户消息信息表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:24:48
 */
public interface UserMessageService extends IService<UserMessageEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 订单通知
     * @param orderId 购买订单ID
     * @param orderStatus 订单状态，3:已发往Biu平台；11：平台已签收，查验中；4:买家待收货(平台验货通过)；5:平台验货未通过；
     */
    void orderNotify(Long orderId, Integer orderStatus);

    /**
     * 出售提醒：当用户有出售订单时，该订单对应的尺码产品有更低售价时，推送提醒
     * @param saleOrderId
     */
    void biuGoodsSaleNotify(Long saleOrderId);

    /**
     * 变现提醒：当出售订单产品有求购价接近出售价时（100以内），推送提醒
     * @param buyOrderId
     */
    void liquidationNotify(Long buyOrderId);

    /**
     * 更高求购价提醒：当求购订单产品有更高的求购价时，推送提醒
     * @param buyOrderId
     */
    void maxPurchasePriceNotify(Long buyOrderId);

}

