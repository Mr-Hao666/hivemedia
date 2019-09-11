package cn.hivemedia.modules.goods.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.Query;
import cn.hivemedia.modules.goods.dao.GoodsCategoryDao;
import cn.hivemedia.modules.goods.entity.GoodsCategoryEntity;
import cn.hivemedia.modules.goods.service.CategoryService;
import cn.hivemedia.modules.goods.service.GoodsCategoryService;

import java.util.List;
import java.util.Map;

import cn.hivemedia.modules.goods.vo.CategoryVo;
import cn.hivemedia.modules.goods.vo.GoodsCateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("goodsCategoryService")
public class GoodsCategoryServiceImpl extends ServiceImpl<GoodsCategoryDao, GoodsCategoryEntity> implements GoodsCategoryService {
    private static final int BRAND_ID = 3;

    @Autowired
    private CategoryService categoryService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<GoodsCategoryEntity> page = this.selectPage(
                new Query<GoodsCategoryEntity>(params).getPage(),
                new EntityWrapper<GoodsCategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List queryGoodsCateVoList(Map<String, Object> params) {
        return baseMapper.queryGoodsCateVoList(params);
    }

    @Override
    public int queryTotal(Map<String, Object> params) {
        return baseMapper.queryTotal(params);
    }
    public GoodsCateVo queryGoodsCateVo(Long id) {
        return baseMapper.queryGoodsCateVo(id);
    }

    @Override
    public List queryAll2Tree(Map<String, Object> params) {
        Map<String, Object> p = Maps.newHashMap();
        p.put("pid", BRAND_ID);
        List<CategoryVo> cateVoList = categoryService.queryCateVoList(p);
        List<GoodsCateVo> goodsCateVoList = queryGoodsCateVoList(params);
        List<GoodsCateVo> pGoodsCateVoList = Lists.newArrayList();
        int i = 1;
        for(CategoryVo categoryVo : cateVoList){
            int pid = i*-1;
            GoodsCateVo goodsCateVo = new GoodsCateVo();
            goodsCateVo.setCateName(categoryVo.getName());
            goodsCateVo.setId((long) pid);
            goodsCateVo.setCategoryId(categoryVo.getId());
            pGoodsCateVoList.add(goodsCateVo);
            for(GoodsCateVo sonGoodsCate : goodsCateVoList){
                if(sonGoodsCate.getCategoryId() == goodsCateVo.getCategoryId()){
                    sonGoodsCate.setPid(pid);
                }
            }
            i++;
        }
        goodsCateVoList.addAll(pGoodsCateVoList);
        return goodsCateVoList;
    }

}
