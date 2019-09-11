package cn.hivemedia.dao;

import cn.hivemedia.entity.OrderGoodsDetailEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 订单详情表
 * 
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:24:48
 */
public interface OrderGoodsDetailDao extends BaseMapper<OrderGoodsDetailEntity> {

    OrderGoodsDetailEntity selectByOrderId(@Param("orderId") Long orderId, @Param("orderType") Integer orderType);


}
