package cn.hivemedia.modules.goods.service;

import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.modules.goods.entity.GoodsCategoryEntity;
import cn.hivemedia.modules.goods.vo.GoodsCateVo;

import java.util.List;
import java.util.Map;

/**
 * 商品分类表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
public interface GoodsCategoryService extends IService<GoodsCategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List queryGoodsCateVoList(Map<String, Object> params);

    int queryTotal(Map<String, Object> params);
    GoodsCateVo queryGoodsCateVo(Long id);

    List queryAll2Tree(Map<String, Object> params);
}

