package cn.hivemedia.controller;

import cn.hivemedia.entity.CategoryEntity;
import cn.hivemedia.entity.GoodsEntity;
import cn.hivemedia.result.BaseResult;
import cn.hivemedia.service.CategoryService;
import cn.hivemedia.service.GoodsCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 杨浩
 * @create 2019-01-12 15:06
 **/
@RestController
@RequestMapping("/api/category")
@Api(tags = "分类")
public class ApiCategoryContrller {
    @Autowired
    private GoodsCategoryService goodsCategoryService;
    @Autowired
    private CategoryService categoryService;

    @ApiOperation("查询分类信息")
    @GetMapping("/queryCategoryList")
    public BaseResult<List<CategoryEntity>> queryCategoryList() {
        return BaseResult.success(categoryService.getAllCategoryList());
    }

    @ApiOperation("查询指定分类信息")
    @GetMapping("/queryCategoryById")
    public BaseResult<List<GoodsEntity>> queryCategoryByType(
            @ApiParam("分类Id") @RequestParam("categoryId") Integer categoryId,
            @ApiParam("排序类型，0：热门；1：最新；2：价格由高到低；3：价格由低到高") @RequestParam(value = "orderByType", required = false) Integer orderByType,
            @ApiParam("当前页码") @RequestParam("pageNo") Integer pageNo,
            @ApiParam("每页显示条数") @RequestParam("pageSize") Integer pageSize) {
        //表单校验
        Map<String, Object> params = new HashMap<>();
        params.put("categoryId", categoryId);
        params.put("orderByType", orderByType);
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        return BaseResult.success(goodsCategoryService.queryGoodsCategoryList(params));
    }

}
