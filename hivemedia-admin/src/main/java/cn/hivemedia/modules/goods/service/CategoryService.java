package cn.hivemedia.modules.goods.service;

import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.R;
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
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
    List<CategoryEntity> queryList(Map<String, Object> params);

    CategoryVo queryObject(Integer id);
    R delete(Integer id);

    List queryCateVoList(Map<String, Object> params);

    int queryTotal(Map<String, Object> params);
}

