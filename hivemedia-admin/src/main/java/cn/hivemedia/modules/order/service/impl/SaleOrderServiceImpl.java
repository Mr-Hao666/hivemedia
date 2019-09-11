package cn.hivemedia.modules.order.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.Query;

import cn.hivemedia.modules.order.dao.SaleOrderDao;
import cn.hivemedia.modules.order.entity.SaleOrderEntity;
import cn.hivemedia.modules.order.service.SaleOrderService;


@Service("saleOrderService")
public class SaleOrderServiceImpl extends ServiceImpl<SaleOrderDao, SaleOrderEntity> implements SaleOrderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SaleOrderEntity> page = this.selectPage(
                new Query<SaleOrderEntity>(params).getPage(),
                new EntityWrapper<SaleOrderEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Boolean deleteIds(List<Long> ids) {
        for (Long id : ids) {
            SaleOrderEntity saleOrderEntity = this.selectById(id);
            saleOrderEntity.setCurState(2);
            this.baseMapper.updateById(saleOrderEntity);
        }
        return true;
    }


}
