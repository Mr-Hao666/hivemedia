package cn.hivemedia.modules.order.service;

import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.modules.order.entity.OrderLogisticsEntity;
import java.util.Map;

/**
 * 订单物流信息表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
public interface OrderLogisticsService extends IService<OrderLogisticsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

