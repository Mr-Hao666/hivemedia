package cn.hivemedia.controller;

import cn.hivemedia.entity.BuyOrderEntity;
import cn.hivemedia.result.BaseResult;
import cn.hivemedia.service.BuyOrderService;
import cn.hivemedia.common.enums.BuyOrderStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 杨浩
 * @create 2018-12-30 14:58
 **/
@RestController
@RequestMapping("/api/myBuy")
@Api(tags = "我的-购买")
public class ApiMyBuyController {

    @Autowired
    private BuyOrderService buyOrderService;

    @ApiOperation("购买-待付款")
    @GetMapping("/myPendingPayment")
    public BaseResult<List<BuyOrderEntity>> myPendingPayment(@ApiParam("当前页码") @RequestParam("pageNo") String pageNo,
                                                             @ApiParam("每页显示条数") @RequestParam("pageSize") String pageSize,
                                                             @ApiParam("用户id") @RequestParam("userId") Long userId) {
        List<Integer> orderStatusList = new ArrayList<>();
        orderStatusList.add(BuyOrderStatus.PENDING_PAYMENT.getCode());
        orderStatusList.add(BuyOrderStatus.UNPAID_EPOSIT.getCode());
        return BaseResult.success(buyOrderService.
                queryMyBuyOrder(pageNo, pageSize, userId, orderStatusList));
    }

    @ApiOperation("购买-求购中")
    @GetMapping("/myBuyMedium")
    public BaseResult<List<BuyOrderEntity>> myBuyMedium(@ApiParam("当前页码") @RequestParam("pageNo") String pageNo,
                                                        @ApiParam("每页显示条数") @RequestParam("pageSize") String pageSize,
                                                        @ApiParam("用户id") @RequestParam("userId") Long userId) {

        List<Integer> orderStatusList = new ArrayList<>();
        orderStatusList.add(BuyOrderStatus.IN_BUY.getCode());
        return BaseResult.success(buyOrderService.queryMyBuyOrder(pageNo, pageSize, userId, orderStatusList));
    }

    @ApiOperation("购买-待发货")
    @GetMapping("/myToBeShipped")
    public BaseResult<List<BuyOrderEntity>> myToBeShipped(@ApiParam("当前页码") @RequestParam("pageNo") String pageNo,
                                                          @ApiParam("每页显示条数") @RequestParam("pageSize") String pageSize,
                                                          @ApiParam("用户id") @RequestParam("userId") Long userId) {
        List<Integer> orderStatusList = new ArrayList<>();
        orderStatusList.add(BuyOrderStatus.PAID.getCode());
        return BaseResult.success(buyOrderService.queryMyBuyOrder(pageNo, pageSize, userId, orderStatusList));
    }

    @ApiOperation("购买-待收货")
    @GetMapping("/myGoodsToBeReceived")
    public BaseResult<List<BuyOrderEntity>> myGoodsToBeReceived(@ApiParam("当前页码") @RequestParam("pageNo") String pageNo,
                                                                @ApiParam("每页显示条数") @RequestParam("pageSize") String pageSize,
                                                                @ApiParam("用户id") @RequestParam("userId") Long userId) {
        List<Integer> orderStatusList = new ArrayList<>();
        orderStatusList.add(BuyOrderStatus.SHIPPED.getCode());
        orderStatusList.add(BuyOrderStatus.SHIPPED_PLATFORM.getCode());
        return BaseResult.success(buyOrderService.queryMyBuyOrder(pageNo, pageSize, userId, orderStatusList));
    }

    @ApiOperation("购买-历史订单")
    @GetMapping("/myBuyHistorical")
    public BaseResult<List<BuyOrderEntity>> myBuyHistorical(@ApiParam("当前页码") @RequestParam("pageNo") String pageNo,
                                                            @ApiParam("每页显示条数") @RequestParam("pageSize") String pageSize,
                                                            @ApiParam("用户id") @RequestParam("userId") Long userId) {
        List<Integer> orderStatusList = new ArrayList<>();
        orderStatusList.add(BuyOrderStatus.CANCEL_TRANSACTION.getCode());
        orderStatusList.add(BuyOrderStatus.ALREADY_SIGNED.getCode());
        return BaseResult.success(buyOrderService.queryMyBuyOrder(pageNo, pageSize, userId, orderStatusList));
    }

    @ApiOperation("购买-订单详情")
    @GetMapping("/detail")
    public BaseResult<BuyOrderEntity> detail(@ApiParam("订单Id") @RequestParam("buyOrderId") Integer buyOrderId) {
        return BaseResult.success(buyOrderService.detail(buyOrderId));
    }

    @ApiOperation("关闭订单")
    @GetMapping("/closeOrder")
    public BaseResult closeOrder(@ApiParam("订单ID") @RequestParam("orderId") Integer orderId) {
        BaseResult baseResult = new BaseResult();
        baseResult.setSuccessful(buyOrderService.cancelBuyOrder(orderId));
        return baseResult;
    }

    @ApiOperation("删除订单")
    @GetMapping("/delOrder")
    public BaseResult delOrder(@ApiParam("订单ID") @RequestParam("orderId") Integer orderId) {
        BaseResult baseResult = new BaseResult();
        baseResult.setSuccessful(buyOrderService.delBuyOrder(orderId));
        return baseResult;
    }

    @ApiOperation("签收订单")
    @GetMapping("/signOrder")
    public BaseResult signOrder(@ApiParam("订单ID") @RequestParam("orderId") Integer orderId) {
        BaseResult baseResult = new BaseResult();
        baseResult.setSuccessful(buyOrderService.signOrder(orderId));
        return baseResult;
    }


//    @ApiOperation("买家删除订单记录")
//    @GetMapping("/buyerDeleteOrderInfo")
//    public R buyerDeleteOrderInfo(@ApiParam("订单ID") @RequestParam("orderId") Integer orderId) {
//        //表单校验
//
//
//        //用户登录
//        return R.ok();
//    }
}
