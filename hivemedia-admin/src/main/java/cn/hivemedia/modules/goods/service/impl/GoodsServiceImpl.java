package cn.hivemedia.modules.goods.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.Query;
import cn.hivemedia.modules.goods.dao.GoodsDao;
import cn.hivemedia.modules.goods.entity.GoodsEntity;
import cn.hivemedia.modules.goods.entity.GoodsSizeEntity;
import cn.hivemedia.modules.goods.service.GoodsService;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.hivemedia.modules.goods.service.GoodsSizeService;
import cn.hivemedia.modules.goods.vo.GoodsSizeVo;
import cn.hivemedia.modules.goods.vo.GoodsVo;
import cn.hivemedia.modules.goods.vo.SizeVo;
import io.swagger.models.auth.In;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("goodsService")
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, GoodsEntity> implements GoodsService {

    @Autowired
    private GoodsSizeService goodsSizeService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<GoodsEntity> page = this.selectPage(
                new Query<GoodsEntity>(params).getPage(),
                new EntityWrapper<GoodsEntity>()
        );

        return new PageUtils(page);
    }
    @Override
    public GoodsVo queryGoodsVo(Long id){
        GoodsVo goodsVo = baseMapper.queryGoodsVo(id);
        Map<String, Object> params = Maps.newHashMap();
        params.put("goodsId", goodsVo.getId());
        List<GoodsSizeVo> sizeVoList = goodsSizeService.queryGoodsSizeVoList(params);
        String sizeStr = getSizeStr(sizeVoList);
        goodsVo.setSizeListStr(sizeStr);
        goodsVo.setGoodsSizeList(Splitter.on(",").splitToList(sizeStr));
        return goodsVo;
    }

    @Override
    public List<GoodsVo> queryGoodsVoList(Map<String, Object> params) {
        List<GoodsVo> goodsVoList = baseMapper.queryGoodsVoList(params);
        Map<String, Object> sizeParams = Maps.newHashMap();
        goodsVoList.forEach(goodsVo -> {
            sizeParams.put("goodsId", goodsVo.getId());
            goodsVo.setSizeListStr(getSizeStr(goodsSizeService.queryGoodsSizeVoList(sizeParams)));
        });
        return goodsVoList;
    }

    private String getSizeStr(List<GoodsSizeVo> sizeList){
        Set<String> sizeSet = Sets.newHashSet();
        sizeList.forEach(item -> {
            String sizeDesc = item.getSizeDesc();
            if(StringUtils.isNotBlank(sizeDesc)) {
                sizeSet.add(sizeDesc);
            }
         });
        if(CollectionUtils.isEmpty(sizeSet)){
            return "";
        }
        return Joiner.on(",").join(sizeSet);
    }
    @Override
    public int queryTotal(Map<String, Object> params) {
        return baseMapper.queryTotal(params);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertGoods(GoodsEntity goods) {
        insert(goods);
        insertBatchGoodsSize(goods);
    }
    public void insertBatchGoodsSize(GoodsEntity goods){
        List<SizeVo> sizeVos = goods.getGoodsSizeList();
        if(!CollectionUtils.isEmpty(sizeVos)){
            List<GoodsSizeEntity> goodsSizeEntities = Lists.newArrayList();
            for (SizeVo size : sizeVos) {
                GoodsSizeEntity goodsSize = new GoodsSizeEntity();
                goodsSize.setSizeId(Long.valueOf(size.getId()));
                goodsSize.setGoodsId(goods.getId());
                goodsSizeEntities.add(goodsSize);
            }
            goodsSizeService.insertBatch(goodsSizeEntities);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateGoods(GoodsEntity goods) {
        updateById(goods);
        insertBatchGoodsSize(goods);
    }
}
