package cn.hivemedia.modules.order.service;

import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.modules.order.entity.OrderEntity;

import java.util.Map;

/**
 * 订单信息
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-09-16 14:51:55
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

