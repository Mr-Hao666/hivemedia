package cn.hivemedia.service.impl;

import cn.hivemedia.dao.CategoryDao;
import cn.hivemedia.dao.SysOssDao;
import cn.hivemedia.entity.CategoryEntity;
import cn.hivemedia.entity.SysOssEntity;
import cn.hivemedia.service.CategoryService;
import cn.hivemedia.utils.Query;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    private SysOssDao ossDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CategoryEntity> page = this.selectPage(
                new Query<CategoryEntity>(params).getPage(),
                new EntityWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> queryListPid(Integer pid) {
        return baseMapper.queryListPid(pid);
    }

    /**
     * 获取所有菜单列表
     */
    @Override
    public List<CategoryEntity> getAllCategoryList() {
        //查询根菜单列表
        List<CategoryEntity> categoryList = queryListPid(-1);
        //递归获取子菜单
        return getCategoryTreeList(categoryList);
    }

    /**
     * 递归
     */
    private List<CategoryEntity> getCategoryTreeList(List<CategoryEntity> categoryList) {
        List<CategoryEntity> subCategoryList = new ArrayList<CategoryEntity>();

        for (CategoryEntity entity : categoryList) {
            entity.setPicUrl(Optional.ofNullable(ossDao.selectById(entity.getPicId()))
                    .orElse(new SysOssEntity()).getUrl());
            entity.setChildren(getCategoryTreeList(queryListPid(entity.getId())));
            subCategoryList.add(entity);
        }

        return subCategoryList;
    }

}
