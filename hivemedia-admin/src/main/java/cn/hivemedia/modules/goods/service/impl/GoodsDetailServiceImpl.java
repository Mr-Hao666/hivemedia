package cn.hivemedia.modules.goods.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.Query;
import cn.hivemedia.modules.goods.dao.GoodsDetailDao;
import cn.hivemedia.modules.goods.entity.GoodsDetailEntity;
import cn.hivemedia.modules.goods.service.GoodsDetailService;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service("goodsDetailService")
public class GoodsDetailServiceImpl extends ServiceImpl<GoodsDetailDao, GoodsDetailEntity> implements GoodsDetailService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<GoodsDetailEntity> page = this.selectPage(
                new Query<GoodsDetailEntity>(params).getPage(),
                new EntityWrapper<GoodsDetailEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List queryGoodsDetailVoList(Map<String, Object> params) {
        return baseMapper.queryGoodsDetailVoList(params);
    }

    @Override
    public int queryTotal(Map<String, Object> params) {
        return baseMapper.queryTotal(params);
    }
}
