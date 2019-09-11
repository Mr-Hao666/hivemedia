package cn.hivemedia.modules.order.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.Query;
import cn.hivemedia.modules.order.dao.OrderReturnsDao;
import cn.hivemedia.modules.order.entity.OrderReturnsEntity;
import cn.hivemedia.modules.order.service.OrderReturnsService;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service("orderReturnsService")
public class OrderReturnsServiceImpl extends ServiceImpl<OrderReturnsDao, OrderReturnsEntity> implements OrderReturnsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<OrderReturnsEntity> page = this.selectPage(
                new Query<OrderReturnsEntity>(params).getPage(),
                new EntityWrapper<OrderReturnsEntity>()
        );

        return new PageUtils(page);
    }
}
