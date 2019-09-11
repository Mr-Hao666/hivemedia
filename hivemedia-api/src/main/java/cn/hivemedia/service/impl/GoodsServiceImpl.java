package cn.hivemedia.service.impl;

import cn.hivemedia.dao.GoodsDao;
import cn.hivemedia.entity.GoodsEntity;
import cn.hivemedia.entity.SysOssEntity;
import cn.hivemedia.service.GoodsService;
import cn.hivemedia.service.SysOssService;
import cn.hivemedia.utils.CollectionUtil;
import cn.hivemedia.utils.Query;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service("goodsService")
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, GoodsEntity> implements GoodsService {

    @Autowired
    private SysOssService sysOssService;

    @Override
    public Page queryPage(Map<String, Object> params) {
        Page<GoodsEntity> page = this.selectPage(
                new Query<GoodsEntity>(params).getPage(),
                new EntityWrapper<GoodsEntity>()
        );
        this.setGoodsEntity(page);
        return page;
    }

    @Override
    public Page queryPageByIds(Map<String, Object> params,Integer pageNo,Integer pageSize) {
        List<Long> ids = (List<Long>) params.get("ids");
        if (CollectionUtils.isEmpty(ids)){
            return new Page(pageNo,pageSize);
        }
        Page<GoodsEntity> page = this.selectPage(
                new Query<GoodsEntity>(params).getPage(),
                new EntityWrapper<GoodsEntity>()
                        .in("id",ids)
                        .eq("del_flag",0)
                        .eq("is_on_sale",1)

        );
        this.setGoodsEntity(page);
        return page;
    }

    @Override
    public Page queryIndexGoodsListByPage(Map<String, Object> params) {
        List<Long> ids = (List<Long>) params.get("ids");
        EntityWrapper<GoodsEntity> entityWrapper = new EntityWrapper<GoodsEntity>();
        entityWrapper.in("id",ids)
                .eq("del_flag",0)
                .eq("is_on_sale",1);
        Integer orderByType = (Integer) params.get("orderByType");
        Integer goodsTab = (Integer) params.get("goodsTab");
        String key = (String) params.get("key");
        //根据商品名或者货号模糊搜索
        if (StringUtils.isNotEmpty(key)){
            entityWrapper.like("name",key).or().like("art_no",key);
        }
        if (goodsTab!=null && goodsTab>0){
            entityWrapper.eq("goods_tab",goodsTab);
        }
        if (orderByType!=null){
            switch (orderByType){
                case 0:
                    //热卖
                    entityWrapper.eq("label",2);
                    break;
                case 1:
                    //最新
                    entityWrapper.orderBy("created_time",false);
                    break;
                case 2:
                    //价格由高到低
                    entityWrapper.orderBy("release_price",false);
                    break;
                case 3:
                    //价格由低到高
                    entityWrapper.orderBy("release_price",true);
                    break;
            }
        }
        Page<GoodsEntity> page = this.selectPage(
                new Query<GoodsEntity>(params).getPage(),entityWrapper
        );
        this.setGoodsEntity(page);
        return page;
    }

    public void setGoodsEntity(Page<GoodsEntity> page){
        List<GoodsEntity> goodsEntities = page.getRecords();
        if (!CollectionUtils.isEmpty(goodsEntities)){
            List<SysOssEntity> detailDescImgList = sysOssService.selectBatchIds(goodsEntities.stream().map(GoodsEntity::getDetailDescImg).collect(Collectors.toList()));
            List<SysOssEntity> picShowImgList = sysOssService.selectBatchIds(goodsEntities.stream().map(GoodsEntity::getPicShow).collect(Collectors.toList()));
            if (!CollectionUtils.isEmpty(detailDescImgList)){
                Map<Long,SysOssEntity> sysOssEntityMap = CollectionUtil.listToMap(detailDescImgList,SysOssEntity::getId);
                goodsEntities.forEach(goodsEntity -> {
                    goodsEntity.setDetailDescImgUrl(sysOssEntityMap.get(goodsEntity.getDetailDescImg()).getUrl());

                });
            }
            if (!CollectionUtils.isEmpty(picShowImgList)){
                Map<Long,SysOssEntity> picShowImgMap = CollectionUtil.listToMap(picShowImgList,SysOssEntity::getId);
                goodsEntities.forEach(goodsEntity -> {
                    goodsEntity.setPicShowUrl(picShowImgMap.get(goodsEntity.getPicShow()).getUrl());
                });
            }
            page.setRecords(goodsEntities);
        }
    }

}
