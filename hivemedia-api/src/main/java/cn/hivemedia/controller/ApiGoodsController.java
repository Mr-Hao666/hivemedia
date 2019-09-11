package cn.hivemedia.controller;

import cn.hivemedia.result.BaseResult;
import com.baomidou.mybatisplus.plugins.Page;
import cn.hivemedia.entity.*;
import cn.hivemedia.entity.model.*;
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
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 杨浩
 * @create 2018-12-30 14:58
 **/
@RestController
@RequestMapping("/api/goods")
@Api(tags = "商品详情接口")
public class ApiGoodsController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private FeeConfigService feeConfigService;
    @Autowired
    private BuyOrderService buyOrderService;
    @Autowired
    private SysOssService sysOssService;
    @Autowired
    private SaleOrderService saleOrderService;
    @Autowired
    private SizeService sizeService;

    @GetMapping("/goodsInfo")
    @ApiOperation("查询商品详情")
    public BaseResult<GoodsEntity> goodsInfo(@ApiParam("商品ID") @RequestParam("goodsId") Integer goodsId) {

        GoodsEntity goodsEntity = goodsService.selectById(goodsId);
        if (goodsEntity == null) {
            return BaseResult.failure("商品不存在或已被删除");
        }
        List<Long> ids = goodsEntity.getpicDetailList();
        List<SysOssEntity> sysOssEntities = sysOssService.selectBatchIds(ids);
        String detailDescImgUrl = "";
        if (goodsEntity.getDetailDescImg() != null) {
            SysOssEntity sysOssEntity = sysOssService.selectById(goodsEntity.getDetailDescImg());
            if (sysOssEntity != null) {
                detailDescImgUrl = sysOssEntity.getUrl();
            }
        }
        SysOssEntity sysOssEntity = sysOssService.selectById(goodsEntity.getPicShow());
        if (sysOssEntity == null) {
            goodsEntity.setPicShowUrl("");
        } else {
            goodsEntity.setPicShowUrl(sysOssEntity.getUrl());
        }
        goodsEntity.setGoodsImgUrl(detailDescImgUrl, sysOssEntities.stream().map(SysOssEntity::getUrl).collect(Collectors.toList()));

        //最新成订单
        BuyOrderEntity buyOrderEntity = buyOrderService.selectLatestBuyOrder(goodsId);
        if (buyOrderEntity != null) {
            goodsEntity.setLatestDealPrice(buyOrderEntity.getAmountTotal());
            goodsEntity.setSizeDesc(buyOrderEntity.getSizeDesc());
        } else {
            goodsEntity.setSizeDesc("");
        }

        // 查询商品成交记录，前5条
        List<OrderGoods> orderGoods = buyOrderService.queryLastFiveOrderByGoodsId(goodsId, null, null);
        if (CollectionUtils.isEmpty(orderGoods)) {
            goodsEntity.setGoodsOrder(Collections.EMPTY_LIST);
        } else {
            goodsEntity.setGoodsOrder(orderGoods);
        }

        return BaseResult.success(goodsEntity);
    }

    @GetMapping("/marginLevel")
    @ApiOperation("查询费用配置")
    public BaseResult<FeeConfigEntity> marginLevel() {
        FeeConfigEntity feeConfigEntity = feeConfigService.selectOne(null);
        return BaseResult.success(feeConfigEntity);
    }

    @GetMapping("/sellerCreateOrder")
    @ApiOperation("创建出售订单")
    public BaseResult<SaleOrderEntity> sellerCreateOrder(@ApiParam("出售订单对象") SellOrder sellOrder) {
        return BaseResult.success(saleOrderService.createSaleOrder(sellOrder));
    }

    @ApiOperation("购买-立即购买列表（现货上架和预售两种类型）")
    @GetMapping("/purchase/list")
    public BaseResult<List<SaleOrderQueryDto>> purchaseList(@ApiParam("商品ID") @RequestParam("goodsId") Integer goodsId) {
        return BaseResult.success(saleOrderService.purchaseList(goodsId));
    }

    @ApiOperation("购买-出价求购列表")
    @GetMapping("/offerPurchase/list")
    public BaseResult<List<OfferPurchaseDto>> offerPurchaseList(@ApiParam("商品ID") @RequestParam("goodsId") Integer goodsId) {
        return BaseResult.success(buyOrderService.offerPurchaseList(goodsId));
    }


    @ApiOperation("立即购买-提交订单")
    @GetMapping("/buyerCreateOrder")
    public BaseResult<BuyOrderEntity> buyerCreateOrder(@ApiParam("商品ID") BuyOrder buyOrder) {

        return BaseResult.success(buyOrderService.buyerCreateOrder(buyOrder));
    }

    @ApiOperation("出价求购-提交订单")
    @GetMapping("/offerPurchaseCreateOrder")
    public BaseResult<BuyOrderEntity> offerPurchaseCreateOrder(@ApiParam("商品ID") BuyOrder buyOrder) {
        return BaseResult.success(buyOrderService.offerPurchaseCreateOrder(buyOrder));
    }

    @ApiOperation("商品最低成交价格")
    @GetMapping("/buyerGoodsMinAmount")
    public BaseResult<SaleGoods> buyerCreateOrder(@ApiParam("商品ID") @RequestParam("goodsId") Integer goodsId,
                                                  @ApiParam("商品尺码ID") @RequestParam("sizeId") Integer sizeId) {
        //表单校验
        SaleGoods saleGoods = buyOrderService.queryBuyOrderAmountByGoodsSize(goodsId, sizeId);

        //用户登录
        return BaseResult.success(saleGoods);
    }


    @ApiOperation("出售-商品规格（尺码）列表")
    @GetMapping("/goodsId/size/list")
    public BaseResult<List<SaleGoods>> goodsSizeList(@ApiParam("商品Id") @RequestParam("goodsId") Integer goodsId) {
        List<SaleGoods> saleGoods = buyOrderService.querySaleGoodsAmountOrderBySize(goodsId);

        return BaseResult.success(saleGoods);
    }

    @ApiOperation("全部交易")
    @GetMapping("/goodsId/buy/list")
    public BaseResult<Page<OrderGoods>> goodsBuyList(@ApiParam("商品Id") @RequestParam("goodsId") Integer goodsId,
                                                     @ApiParam("1:当前出售 2:当前求购 （可空，為空查全部）") @RequestParam(value = "buyType", required = false) Integer buyType,
                                                     @ApiParam("尺碼Id（可空）") @RequestParam(value = "sizeId", required = false) Integer sizeId,
                                                     @ApiParam("当前页") @RequestParam("pageNo") Integer pageNo,
                                                     @ApiParam("每页大小") @RequestParam("pageSize") Integer pageSize) {
        Page<OrderGoods> page;
        if (buyType == null || buyType == 0) {
            // 全部交易
            page = saleOrderService.queryListAllByGoodsId(goodsId, sizeId, pageNo, pageSize);
        } else if (2 == buyType) {
            page = buyOrderService.queryListByGoodsId(goodsId, buyType, sizeId, pageNo, pageSize,0);

        } else if (1 == buyType) {
            // 当前出售
            page = saleOrderService.queryListByGoodsId(goodsId, sizeId, pageNo, pageSize,0);
        } else {
            page = new Page<>();
        }

        return BaseResult.success(page);

    }

    @GetMapping("/sizeInfo/list")
    @ApiOperation("查询商品的尺码列表")
    public BaseResult<List<SizeEntity>> getGoodsSizeInfoList(@ApiParam("商品Id") @RequestParam("goodsId") Integer goodsId){

        List<SizeEntity> sizeEntities = sizeService.getGoodsSizeInfoList(goodsId);
        if (CollectionUtils.isEmpty(sizeEntities)){
            return BaseResult.success(Collections.EMPTY_LIST);
        }
        return BaseResult.success(sizeEntities);
    }

    @GetMapping("/goodsOfferPurchase")
    @ApiOperation("查询商品尺码的最高求购订单")
    public BaseResult<GoodsOfferPurchase> getGoodsOfferPurchase(@ApiParam("商品Id") @RequestParam("goodsId") Integer goodsId,@ApiParam("尺码Id") @RequestParam("sizeId") Integer sizeId){
        GoodsOfferPurchase goodsOfferPurchase = buyOrderService.getGoodsOfferPurchase(goodsId,sizeId);
        return BaseResult.success(goodsOfferPurchase);
    }




}
