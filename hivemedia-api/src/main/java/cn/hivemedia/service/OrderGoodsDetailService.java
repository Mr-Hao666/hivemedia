package cn.hivemedia.service;

import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.entity.OrderGoodsDetailEntity;

import java.util.Map;

/**
 * 订单详情表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:24:48
 */
public interface OrderGoodsDetailService extends IService<OrderGoodsDetailEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

