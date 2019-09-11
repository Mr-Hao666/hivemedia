package cn.hivemedia.service;

import cn.hivemedia.entity.OrderLogisticsEntity;
import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;
import io.swagger.models.auth.In;

import java.util.Map;

/**
 * 订单物流信息表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:24:48
 */
public interface OrderLogisticsService extends IService<OrderLogisticsEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 修改订单物流信息
     */
    void updateOrderLogistics(OrderLogisticsEntity orderLogisticsEntity);

    OrderLogisticsEntity selectByBuyOrderId(Integer buyOrderId);

    OrderLogisticsEntity selectBySaleOrderId(Integer saleOrderId);
}

