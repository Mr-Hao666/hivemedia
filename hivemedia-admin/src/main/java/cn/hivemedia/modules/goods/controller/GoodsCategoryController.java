package cn.hivemedia.modules.goods.controller;

import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.Query;
import cn.hivemedia.common.utils.R;
import cn.hivemedia.common.validator.ValidatorUtils;
import cn.hivemedia.modules.goods.entity.GoodsCategoryEntity;
import cn.hivemedia.modules.goods.service.GoodsCategoryService;

import java.util.List;
import java.util.Map;

import cn.hivemedia.modules.goods.vo.GoodsCateVo;
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
@RequestMapping("goods/goodscategory")
public class GoodsCategoryController {
    @Autowired
    private GoodsCategoryService goodsCategoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("goods:goodscategory:list")
    public R list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List list = goodsCategoryService.queryGoodsCateVoList(query);
        int total = goodsCategoryService.queryTotal(query);
        PageUtils page = new PageUtils(list, total, query.getLimit(), query.getCurrPage());
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("goods:goodscategory:info")
    public R info(@PathVariable("id") Long id) {
        GoodsCateVo goodsCategory = goodsCategoryService.queryGoodsCateVo(id);

        return R.ok().put("goodsCategory", goodsCategory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("goods:goodscategory:save")
    public R save(@RequestBody GoodsCategoryEntity goodsCategory) {
        goodsCategoryService.insert(goodsCategory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("goods:goodscategory:update")
    public R update(@RequestBody GoodsCategoryEntity goodsCategory) {
        ValidatorUtils.validateEntity(goodsCategory);
        goodsCategoryService.updateById(goodsCategory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete/{id}")
    @RequiresPermissions("goods:goodscategory:delete")
    public R delete(@PathVariable("id") Long id) {
        if(id < 0){
            return R.error("删除分类名称请去商品分类管理!");
        }
        goodsCategoryService.deleteById(id);

        return R.ok();
    }

    @RequestMapping("/queryAll")
    public List queryAll(@RequestParam Map<String, Object> params) {
        List list = goodsCategoryService.queryAll2Tree(params);
        return list;
    }
}
