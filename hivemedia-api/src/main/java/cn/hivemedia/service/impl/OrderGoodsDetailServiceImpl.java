package cn.hivemedia.service.impl;

import cn.hivemedia.dao.OrderGoodsDetailDao;
import cn.hivemedia.entity.OrderGoodsDetailEntity;
import cn.hivemedia.service.OrderGoodsDetailService;
import cn.hivemedia.utils.Query;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("orderGoodsDetailService")
public class OrderGoodsDetailServiceImpl extends ServiceImpl<OrderGoodsDetailDao, OrderGoodsDetailEntity> implements OrderGoodsDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<OrderGoodsDetailEntity> page = this.selectPage(
                new Query<OrderGoodsDetailEntity>(params).getPage(),
                new EntityWrapper<OrderGoodsDetailEntity>()
        );

        return new PageUtils(page);
    }

}
