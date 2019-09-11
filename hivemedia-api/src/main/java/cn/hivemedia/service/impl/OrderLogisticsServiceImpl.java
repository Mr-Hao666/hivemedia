package cn.hivemedia.service.impl;

import cn.hivemedia.dao.OrderLogisticsDao;
import cn.hivemedia.entity.OrderLogisticsEntity;
import cn.hivemedia.utils.Query;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.service.OrderLogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("orderLogisticsService")
public class OrderLogisticsServiceImpl extends ServiceImpl<OrderLogisticsDao, OrderLogisticsEntity> implements OrderLogisticsService {

    @Autowired
    private OrderLogisticsDao orderLogisticsDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<OrderLogisticsEntity> page = this.selectPage(
                new Query<OrderLogisticsEntity>(params).getPage(),
                new EntityWrapper<OrderLogisticsEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void updateOrderLogistics(OrderLogisticsEntity orderLogisticsEntity) {
        orderLogisticsDao.updateOrderLogistics(orderLogisticsEntity);
    }

    @Override
    public OrderLogisticsEntity selectByBuyOrderId(Integer buyOrderId) {
        OrderLogisticsEntity orderLogisticsEntity = new OrderLogisticsEntity();
        orderLogisticsEntity.setOrderType(2);
        orderLogisticsEntity.setOrderId(buyOrderId.longValue());
        return orderLogisticsDao.selectOne(orderLogisticsEntity);
    }

    @Override
    public OrderLogisticsEntity selectBySaleOrderId(Integer saleOrderId) {
        OrderLogisticsEntity orderLogisticsEntity = new OrderLogisticsEntity();
        orderLogisticsEntity.setOrderType(1);
        orderLogisticsEntity.setOrderId(saleOrderId.longValue());
        return orderLogisticsDao.selectOne(orderLogisticsEntity);
    }

}
