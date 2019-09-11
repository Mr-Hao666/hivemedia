package cn.hivemedia.service.impl;

import cn.hivemedia.dao.GoodsRecBannerDao;
import cn.hivemedia.entity.GoodsRecBannerEntity;
import cn.hivemedia.service.GoodsRecBannerService;
import cn.hivemedia.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;


@Service("goodsRecBannerService")
public class GoodsRecBannerServiceImpl extends ServiceImpl<GoodsRecBannerDao, GoodsRecBannerEntity> implements GoodsRecBannerService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<GoodsRecBannerEntity> page = this.selectPage(
                new Query<GoodsRecBannerEntity>(params).getPage(),
                new EntityWrapper<GoodsRecBannerEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<GoodsRecBannerEntity> selectList(Integer size) {
        return baseMapper.getList(size);
    }

}
