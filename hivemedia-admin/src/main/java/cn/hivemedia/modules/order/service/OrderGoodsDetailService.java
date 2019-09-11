package cn.hivemedia.modules.order.service;

import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.modules.order.entity.OrderGoodsDetailEntity;
import java.util.Map;

/**
 * 订单详情表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
public interface OrderGoodsDetailService extends IService<OrderGoodsDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

