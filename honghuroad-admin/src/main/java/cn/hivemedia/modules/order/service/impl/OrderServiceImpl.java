package cn.honghuroad.modules.order.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.honghuroad.common.utils.PageUtils;
import cn.honghuroad.common.utils.Query;

import cn.honghuroad.modules.order.dao.OrderDao;
import cn.honghuroad.modules.order.entity.OrderEntity;
import cn.honghuroad.modules.order.service.OrderService;


@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<OrderEntity> page = this.selectPage(
                new Query<OrderEntity>(params).getPage(),
                new EntityWrapper<OrderEntity>()
        );

        return new PageUtils(page);
    }

}
