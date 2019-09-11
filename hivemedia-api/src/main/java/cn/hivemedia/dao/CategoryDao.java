package cn.hivemedia.dao;

import cn.hivemedia.entity.CategoryEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * 商品分类表
 * 
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:26:15
 */
public interface CategoryDao extends BaseMapper<CategoryEntity> {

    List<CategoryEntity> queryListPid(Integer pId);

}