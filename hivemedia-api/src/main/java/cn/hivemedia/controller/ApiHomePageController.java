package cn.hivemedia.controller;

import cn.hivemedia.result.BaseResult;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import cn.hivemedia.entity.*;
import cn.hivemedia.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 杨浩
 * @create 2018-11-30 14:55
 **/
@RestController
@RequestMapping("/api/homePage")
@Api(tags = "首页接口")
public class ApiHomePageController {

    @Autowired
    private GoodsRecBannerService goodsRecBannerService;
    @Autowired
    private RecGoodsService recGoodsService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserGoodsService userGoodsService;
    @Autowired
    private KeywordConfigService keywordConfigService;


    @ApiOperation("首页查询产品信息列表")
    @GetMapping("/queryGoodsList")
    public BaseResult<List<GoodsEntity>> queryGoodsList(
            @ApiParam("首页筛选tab类型，0:全部；1：球鞋；2：服饰；3：其他") @RequestParam("goodsTab") Integer goodsTab,
            @ApiParam("排序类型，0：热门；1：最新；2：价格由高到低；3：价格由低到高") @RequestParam(value = "orderByType", required = false) Integer orderByType,
            @ApiParam("当前页码") @RequestParam("pageNo") Integer pageNo,
            @ApiParam("每页显示条数") @RequestParam("pageSize") Integer pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        params.put("goodsTab", goodsTab);
        params.put("orderByType", orderByType);
        Page page = goodsService.queryIndexGoodsListByPage(params);
        return BaseResult.success(page);
    }

    @ApiOperation("商品搜索")
    @GetMapping("/queryListByKeyWord")
    public BaseResult<List<GoodsEntity>> queryListByKeyWord(
            @ApiParam("关键词") @RequestParam("key") String key,
            @ApiParam("排序类型，0：热门；1：最新；2：价格由高到低；3：价格由低到高") @RequestParam(value = "orderByType", required = false) Integer orderByType,
            @ApiParam("当前页码") @RequestParam("pageNo") Integer pageNo,
            @ApiParam("每页显示条数") @RequestParam("pageSize") Integer pageSize) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        params.put("key", key);
        params.put("orderByType", orderByType);
        Page page = goodsService.queryIndexGoodsListByPage(params);
        return BaseResult.success(page);
    }

    @ApiOperation("搜索关键字推荐")
    @GetMapping("/queryListKeyword")
    public BaseResult<List<KeywordConfigEntity>> queryListKeyword(
            @ApiParam("显示条数") @RequestParam("size") Integer size) {
        List<KeywordConfigEntity> keywordConfigEntityList = keywordConfigService.getList(size);
        return BaseResult.success(keywordConfigEntityList);
    }
    @ApiOperation("查询banner")
    @GetMapping("/queryBannerList")
    public BaseResult<List<GoodsRecBannerEntity>> queryBannerList() {
        List<GoodsRecBannerEntity> goodsRecBannerEntities = goodsRecBannerService.selectList(5);
        return BaseResult.success(goodsRecBannerEntities);
    }

    @ApiOperation("查询推荐列表")
    @GetMapping("/queryRecGoodsList")
    public BaseResult<List<GoodsEntity>> queryRecGoodsList(
            @ApiParam("当前页码") @RequestParam("pageNo") Integer pageNo,
            @ApiParam("每页显示条数") @RequestParam("pageSize") Integer pageSize) {
        List<RecGoodsEntity> recGoodsEntities = recGoodsService.selectList(new EntityWrapper<>());
        Map<String, Object> params = new HashMap<>();
        if (CollectionUtils.isEmpty(recGoodsEntities)) {
            params.put("ids", Collections.EMPTY_LIST);
        }
        List<Long> ids = recGoodsEntities.stream().map(RecGoodsEntity::getGoodsId).collect(Collectors.toList());
        params.put("ids", ids);
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        Page page = goodsService.queryPageByIds(params, pageNo, pageSize);
        return BaseResult.success(page);
    }

    @ApiOperation("用户上传商品信息")
    @GetMapping("/uploadGoods")
    public BaseResult uploadGoods(@ApiParam("当前登录用户ID") @RequestParam("userId") Integer userId,
                                  @ApiParam("品牌名称") @RequestParam("brandName") String brandName,
                                  @ApiParam("商品名") @RequestParam("goodsName") String goodsName,
                                  @ApiParam("发售时间") @RequestParam("deliveryTime") String deliveryTime,
                                  @ApiParam("适用性别：0男，1女，2未知") @RequestParam("sex") Integer sex,
                                  @ApiParam("货号") @RequestParam("itemNumber") String itemNumber,
                                  @ApiParam("商品图片ID,多个用逗号隔开") @RequestParam("goodsImages") String goodsImages) {
        UserGoodsEntity userGoodsEntity = new UserGoodsEntity();
        userGoodsEntity.setUserId(userId);
        userGoodsEntity.setBrand(brandName);
        userGoodsEntity.setName(goodsName);
        userGoodsEntity.setSaleTime(deliveryTime);
        userGoodsEntity.setSex(sex);
        userGoodsEntity.setNumber(itemNumber);
        userGoodsEntity.setPicture(goodsImages);
        userGoodsService.insert(userGoodsEntity);
        return BaseResult.success();
    }
}
