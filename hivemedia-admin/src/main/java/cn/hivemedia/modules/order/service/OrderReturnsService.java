package cn.hivemedia.modules.order.service;

import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.modules.order.entity.OrderReturnsEntity;
import java.util.Map;

/**
 * 订单退货表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
public interface OrderReturnsService extends IService<OrderReturnsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

