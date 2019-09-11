package cn.hivemedia.modules.goods.dao;

import cn.hivemedia.modules.goods.entity.GoodsCategoryEntity;
import cn.hivemedia.modules.goods.vo.GoodsCateVo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * 商品分类表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
public interface GoodsCategoryDao extends BaseMapper<GoodsCategoryEntity> {
    List<GoodsCateVo> queryGoodsCateVoList(Map<String, Object> params);
    int queryTotal(Map<String, Object> params);

    GoodsCateVo queryGoodsCateVo(Long id);
}
