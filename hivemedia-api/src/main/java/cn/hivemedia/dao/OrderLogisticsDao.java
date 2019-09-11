package cn.hivemedia.dao;

import cn.hivemedia.entity.OrderLogisticsEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 订单物流信息表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:24:48
 */
public interface OrderLogisticsDao extends BaseMapper<OrderLogisticsEntity> {

    /**
     * 修改订单物流信息
     *
     * @param orderLogisticsEntity
     * @return
     */
    int updateOrderLogistics(OrderLogisticsEntity orderLogisticsEntity);

}
