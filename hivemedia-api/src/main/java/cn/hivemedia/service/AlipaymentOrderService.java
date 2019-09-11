package cn.hivemedia.service;

import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.entity.AlipaymentOrderEntity;
import cn.hivemedia.entity.BuyOrderEntity;
import cn.hivemedia.entity.model.PayOrder;

import java.util.Map;

/**
 * 
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-08 15:57:04
 */
public interface AlipaymentOrderService extends IService<AlipaymentOrderEntity> {
    String getAliPayOrderStr(PayOrder payOrder);

    String notify(Map<String, String> conversionParams);

    int checkAlipay(String outTradeNo);

    boolean refund(BuyOrderEntity orderEntity);

    AlipaymentOrderEntity selectByOutTradeNo(String outTradeNo);

    AlipaymentOrderEntity selectByBuyOrder(Long orderId, Integer payType, Integer orderType);
}

