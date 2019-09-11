package cn.hivemedia.service;

import cn.hivemedia.entity.GoodsCategoryEntity;
import cn.hivemedia.entity.GoodsEntity;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;

import java.util.Map;

/**
 * 商品分类表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:26:16
 */
public interface GoodsCategoryService extends IService<GoodsCategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 根据分类ID查询商品列表
     * @param params
     * @return
     */
    Page<GoodsEntity> queryGoodsCategoryList(Map<String, Object> params);
}

