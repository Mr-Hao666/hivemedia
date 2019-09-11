package cn.hivemedia.modules.goods.controller;

import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.Query;
import cn.hivemedia.common.utils.R;
import cn.hivemedia.common.validator.ValidatorUtils;
import cn.hivemedia.modules.goods.entity.CategoryEntity;
import cn.hivemedia.modules.goods.service.CategoryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hivemedia.modules.goods.vo.CategoryVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品分类表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
@RestController
@RequestMapping("goods/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("goods:category:list")
    public R list(@RequestParam Map<String, Object> params) {

        List<CategoryEntity> list = categoryService.queryList(params);
        return R.ok().put("list", list);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("goods:category:info")
    public R info(@PathVariable("id") Integer id) {
        CategoryVo category = categoryService.queryObject(id);

        return R.ok().put("category", category);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("goods:category:save")
    public R save(@RequestBody CategoryEntity category) {
        categoryService.insert(category);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("goods:category:update")
    public R update(@RequestBody CategoryEntity category) {
        ValidatorUtils.validateEntity(category);
        categoryService.updateById(category);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete/{id}")
    @RequiresPermissions("goods:category:delete")
    public R delete(@PathVariable("id") Integer id) {
        return categoryService.delete(id);
    }

    @RequestMapping("/queryAll")
    public List<CategoryEntity> queryAll() {
        Map<String, Object> params = new HashMap<>();
        params.put("exclude", "-1");
        //List<CategoryEntity> list = categoryService.queryList(params);
        Query query = new Query(params);
        List list = categoryService.queryCateVoList(query);
        return list;
    }

}
