package cn.hivemedia.modules.order.service.impl;

import cn.hivemedia.modules.order.service.OrderGoodsDetailService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.Query;
import cn.hivemedia.modules.order.dao.OrderGoodsDetailDao;
import cn.hivemedia.modules.order.entity.OrderGoodsDetailEntity;

import java.util.Map;
import org.springframework.stereotype.Service;

@Service("orderGoodsDetailService")
public class OrderGoodsDetailServiceImpl extends ServiceImpl<OrderGoodsDetailDao, OrderGoodsDetailEntity>
        implements OrderGoodsDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<OrderGoodsDetailEntity> page = this.selectPage(
                new Query<OrderGoodsDetailEntity>(params).getPage(),
                new EntityWrapper<OrderGoodsDetailEntity>()
        );

        return new PageUtils(page);
    }
}
