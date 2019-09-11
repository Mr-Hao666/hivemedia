package cn.hivemedia.modules.goods.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import cn.hivemedia.modules.goods.entity.CategoryEntity;
import cn.hivemedia.modules.goods.vo.CategoryVo;

import java.util.List;
import java.util.Map;

/**
 * 商品分类表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
public interface CategoryDao extends BaseMapper<CategoryEntity> {

    CategoryVo queryObject(Integer id);

    List<CategoryVo> queryCateVoList(Map<String, Object> params);

    int queryTotal(Map<String, Object> params);
}
