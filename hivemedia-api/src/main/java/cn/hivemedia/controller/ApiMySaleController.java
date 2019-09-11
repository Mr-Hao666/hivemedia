package cn.hivemedia.controller;

import cn.hivemedia.entity.SaleOrderEntity;
import cn.hivemedia.result.BaseResult;
import cn.hivemedia.service.BuyOrderService;
import cn.hivemedia.service.SaleOrderService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import cn.hivemedia.common.enums.SaleOrderStatus;
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
@RequestMapping("/api/mySale")
@Api(tags = "我的-出售")
public class ApiMySaleController {

    @Autowired
    private SaleOrderService saleOrderService;

    @Autowired
    private BuyOrderService buyOrderService;

    @ApiOperation("出售-出售中")
    @GetMapping("/myShipment")
    public BaseResult<List<SaleOrderEntity>> myShipment(@ApiParam("当前页码") @RequestParam("pageNo") String pageNo,
                                                        @ApiParam("每页显示条数") @RequestParam("pageSize") String pageSize,
                                                        @ApiParam("用户id") @RequestParam("userId") Long userId) {

        List<Integer> orderStatusList = new ArrayList<>();
        orderStatusList.add(SaleOrderStatus.UNPAID_EPOSIT.getCode());
        orderStatusList.add(SaleOrderStatus.IN_SALE.getCode());
        return BaseResult.success(saleOrderService.queryMySaleOrder(pageNo, pageSize, userId, orderStatusList));
    }

    @ApiOperation("出售-未发货")
    @GetMapping("/myUnshipped")
    public BaseResult<List<SaleOrderEntity>> myUnshipped(@ApiParam("当前页码") @RequestParam("pageNo") String pageNo,
                                                         @ApiParam("每页显示条数") @RequestParam("pageSize") String pageSize,
                                                         @ApiParam("用户id") @RequestParam("userId") Long userId) {
        List<Integer> orderStatusList = new ArrayList<>();
        orderStatusList.add(SaleOrderStatus.PAID.getCode());
        return BaseResult.success(saleOrderService.queryMySaleOrder(pageNo, pageSize, userId, orderStatusList));
    }

    @ApiOperation("出售-已发货")
    @GetMapping("/myShipped")
    public BaseResult<List<SaleOrderEntity>> myShipped(@ApiParam("当前页码") @RequestParam("pageNo") String pageNo,
                                                       @ApiParam("每页显示条数") @RequestParam("pageSize") String pageSize,
                                                       @ApiParam("用户id") @RequestParam("userId") Long userId) {
        List<Integer> orderStatusList = new ArrayList<>();
        orderStatusList.add(SaleOrderStatus.SHIPPED.getCode());
        return BaseResult.success(saleOrderService.queryMySaleOrder(pageNo, pageSize, userId, orderStatusList));
    }

    @ApiOperation("出售-历史订单")
    @GetMapping("/mySaleHistorical")
    public BaseResult<List<SaleOrderEntity>> mySaleHistorical(@ApiParam("当前页码") @RequestParam("pageNo") String pageNo,
                                                              @ApiParam("每页显示条数") @RequestParam("pageSize") String pageSize,
                                                              @ApiParam("用户id") @RequestParam("userId") Long userId) {
        List<Integer> orderStatusList = new ArrayList<>();
        orderStatusList.add(SaleOrderStatus.ALREADY_SIGNED.getCode());
        orderStatusList.add(SaleOrderStatus.CANCEL_TRANSACTION.getCode());
        return BaseResult.success(saleOrderService.queryMySaleOrder(pageNo, pageSize, userId, orderStatusList));
    }

    @ApiOperation("出售-订单详情")
    @GetMapping("/detail")
    public BaseResult<SaleOrderEntity> detail(@ApiParam("订单Id") @RequestParam("saleOrderId") Integer saleOrderId) {
        return BaseResult.success(saleOrderService.detail(saleOrderId));
    }

    @ApiOperation("卖家发货")
    @GetMapping("/sellerDelivery")
    public BaseResult sellerDelivery(@ApiParam("订单ID") @RequestParam("orderId") Integer orderId,
                                     @ApiParam("退货地址") @RequestParam("returnConsigneeAddress") String returnConsigneeAddress,
                                     @ApiParam("退货收货手机号") @RequestParam("returnConsigneePhone") String returnConsigneePhone,
                                     @ApiParam("退货收货姓名") @RequestParam("returnConsigneeName") String returnConsigneeName,
                                     @ApiParam("快递单号") @RequestParam("expressNo") String expressNo) {
        BaseResult baseResult = new BaseResult();
        baseResult.setSuccessful(saleOrderService.deliverGoods(orderId, returnConsigneeAddress, returnConsigneePhone, returnConsigneeName, expressNo));
        return baseResult;
    }

    @ApiOperation("关闭订单")
    @GetMapping("/closeOrder")
    public BaseResult closeOrder(@ApiParam("订单ID") @RequestParam("orderId") Integer orderId) {
        BaseResult baseResult = new BaseResult();
        baseResult.setSuccessful(saleOrderService.cancelSaleOrder(orderId));
        return baseResult;
    }

    @ApiOperation("删除订单")
    @GetMapping("/delOrder")
    public BaseResult delOrder(@ApiParam("订单ID") @RequestParam("orderId") Integer orderId) {
        BaseResult baseResult = new BaseResult();
        baseResult.setSuccessful(saleOrderService.delSaleOrder(orderId));
        return baseResult;
    }
}
