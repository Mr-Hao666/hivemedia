package cn.hivemedia.modules.goods.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.Query;
import cn.hivemedia.common.utils.R;
import cn.hivemedia.modules.goods.dao.CategoryDao;
import cn.hivemedia.modules.goods.entity.CategoryEntity;
import cn.hivemedia.modules.goods.service.CategoryService;

import java.util.List;
import java.util.Map;

import cn.hivemedia.modules.goods.vo.CategoryVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CategoryEntity> page = this.selectPage(
                new Query<CategoryEntity>(params).getPage(),
                new EntityWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }
    @Override
    public List<CategoryEntity> queryList(Map<String, Object> params) {
        String select = params.get("select") + "";
        String pid = params.get("pid") == null? "" : params.get("pid") + "";
        EntityWrapper<CategoryEntity> wrapper = new EntityWrapper<>();
        if(StringUtils.equals(select, "select")){
            wrapper.addFilter("pid != -1");
        }
        if(StringUtils.isNotBlank(pid)){
            wrapper.addFilter("pid = {0}", pid);
        }

        List<CategoryEntity> cateList =this.selectList(wrapper);
        return cateList;
    }

    @Override
    public CategoryVo queryObject(Integer id) {
        return baseMapper.queryObject(id);
    }
    public R delete(Integer id) {
        CategoryEntity category = selectById(id);
        if(category == null) {
            return R.error("未找到要删除的分类!");
        }
        Map<String, Object> params = Maps.newHashMap();
        params.put("pid", id);
        List<CategoryEntity> categoryEntities = queryList(params);
        if(CollectionUtils.isNotEmpty(categoryEntities)){
            return R.error("请先删除子分类");
        }
        deleteById(id);
        return R.ok();
    }

    @Override
    public List queryCateVoList(Map<String, Object> params) {


        return baseMapper.queryCateVoList(params);
    }

    @Override
    public int queryTotal(Map<String, Object> params) {
        return baseMapper.queryTotal(params);
    }

}
