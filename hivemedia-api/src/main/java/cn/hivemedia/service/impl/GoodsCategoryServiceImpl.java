package cn.hivemedia.service.impl;

import cn.hivemedia.dao.GoodsCategoryDao;
import cn.hivemedia.entity.GoodsCategoryEntity;
import cn.hivemedia.entity.GoodsEntity;
import cn.hivemedia.service.GoodsService;
import cn.hivemedia.utils.Query;
import cn.hivemedia.service.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;


@Service("goodsCategoryService")
public class GoodsCategoryServiceImpl extends ServiceImpl<GoodsCategoryDao, GoodsCategoryEntity> implements GoodsCategoryService {

    @Autowired
    private GoodsService goodsService;
    @Override

    public PageUtils queryPage(Map<String, Object> params) {
        Page<GoodsCategoryEntity> page = this.selectPage(
                new Query<GoodsCategoryEntity>(params).getPage(),
                new EntityWrapper<GoodsCategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Page<GoodsEntity> queryGoodsCategoryList(Map<String, Object> params) {
        Integer categoryId = (Integer) params.get("categoryId");
        List<GoodsCategoryEntity> goodsCategoryEntityList =
                this.selectList(new EntityWrapper<GoodsCategoryEntity>()
                .eq("category_id", categoryId));
        List<Long> goodsIds = new ArrayList<>();
        goodsCategoryEntityList.forEach(x -> goodsIds.add(x.getGoodsId()));
        params.put("ids", goodsIds);
        return goodsService.queryIndexGoodsListByPage(params);
    }

}
