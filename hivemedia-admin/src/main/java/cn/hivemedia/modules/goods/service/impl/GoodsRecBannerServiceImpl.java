package cn.hivemedia.modules.goods.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.Query;
import cn.hivemedia.common.utils.R;
import cn.hivemedia.modules.goods.dao.GoodsRecBannerDao;
import cn.hivemedia.modules.goods.entity.GoodsEntity;
import cn.hivemedia.modules.goods.entity.GoodsRecBannerEntity;
import cn.hivemedia.modules.goods.service.GoodsRecBannerService;

import java.util.List;
import java.util.Map;

import cn.hivemedia.modules.goods.service.GoodsService;
import cn.hivemedia.modules.goods.vo.GoodsRecBannerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("goodsRecBannerService")
public class GoodsRecBannerServiceImpl extends ServiceImpl<GoodsRecBannerDao, GoodsRecBannerEntity> implements GoodsRecBannerService {
    private static final Integer TYPE_OF_GOODSID = 1;
    @Autowired
    private GoodsService goodsService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<GoodsRecBannerEntity> page = this.selectPage(
                new Query<GoodsRecBannerEntity>(params).getPage(),
                new EntityWrapper<GoodsRecBannerEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public R recommend(Long goodsId) {
        GoodsEntity goodsEntity = goodsService.selectById(goodsId);
        if(goodsEntity == null) {
            return R.error("未找到要推荐的商品");
        }
        GoodsRecBannerEntity goodsRecBanner = new GoodsRecBannerEntity();
        goodsRecBanner.setTabId(goodsEntity.getGoodsTab());
        goodsRecBanner.setPicId(goodsEntity.getPicShow());
        goodsRecBanner.setUrl(goodsEntity.getId()+"");
        goodsRecBanner.setType(TYPE_OF_GOODSID);
        goodsRecBanner.setDesc(goodsEntity.getDesc());
        goodsRecBanner.setSortNo(goodsEntity.getSortNo());
        baseMapper.insert(goodsRecBanner);
        return R.ok();
    }

    @Override
    public List queryGoodsRecBannerVoList(Map<String, Object> params) {
        return baseMapper.queryGoodsRecBannerVoList(params);
    }

    @Override
    public int queryTotal(Map<String, Object> params) {
        return baseMapper.queryTotal(params);
    }
    @Override
    public GoodsRecBannerVo queryGoodsRecBannerVo(Integer id){
        return baseMapper.queryGoodsRecBannerVo(id);
    }
}
