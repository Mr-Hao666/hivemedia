package cn.hivemedia.modules.order.service.impl;

import cn.hivemedia.modules.order.service.OrderLogisticsService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.Query;
import cn.hivemedia.modules.order.dao.OrderLogisticsDao;
import cn.hivemedia.modules.order.entity.OrderLogisticsEntity;

import java.util.Map;
import org.springframework.stereotype.Service;

@Service("orderLogisticsService")
public class OrderLogisticsServiceImpl extends ServiceImpl<OrderLogisticsDao, OrderLogisticsEntity> implements OrderLogisticsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<OrderLogisticsEntity> page = this.selectPage(
                new Query<OrderLogisticsEntity>(params).getPage(),
                new EntityWrapper<OrderLogisticsEntity>()
        );

        return new PageUtils(page);
    }
}
