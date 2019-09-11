package cn.hivemedia.controller;

import cn.hivemedia.entity.BuyOrderEntity;
import cn.hivemedia.entity.SaleOrderEntity;
import cn.hivemedia.entity.model.PayOrder;
import cn.hivemedia.result.BaseResult;
import cn.hivemedia.service.AlipaymentOrderService;
import cn.hivemedia.service.BuyOrderService;
import cn.hivemedia.service.SaleOrderService;
import cn.hivemedia.service.WxPostDataInfoService;
import cn.hivemedia.utils.wx.IdUtils;
import cn.hivemedia.utils.wx.WxUtil;
import cn.hivemedia.common.enums.BuyOrderStatus;
import cn.hivemedia.common.enums.SaleOrderStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author 杨浩
 * @create 2018-12-30 14:59
 **/
@RestController
@RequestMapping("/api/pay")
@Api(tags = "支付")
@Slf4j
public class ApiPayController {
    
    @Autowired
    protected AlipaymentOrderService alipaymentOrderService;

    @Autowired
    protected WxPostDataInfoService wxPostDataInfoService;

    @Autowired
    protected BuyOrderService buyOrderService;

    @Autowired
    protected SaleOrderService saleOrderService;


    /**
     * 点击“立即购买”时调用，同时生成订单数据
     *
     * */
    @ApiOperation("获取微信支付最终调用参数")
    @PostMapping("/getWeixinPayParams")
    @ResponseBody
    public BaseResult getWxPayParams(@ApiParam("商品金额") @RequestParam("amount") String amount,
                                     @ApiParam("购买订单Id") @RequestParam("buyOrderId") Integer buyOrderId,
                                     @ApiParam("openId") @RequestParam(name="openId",required = false) String openId,
                                     @ApiParam("商品名称") @RequestParam("proName") String proName) {
        BuyOrderEntity buyOrder = buyOrderService.selectById(buyOrderId);
        int payType;
        if (buyOrder == null) {
            return BaseResult.failure("订单不存在");
        } else if (buyOrder.getOrderStatus().equals(BuyOrderStatus.PENDING_PAYMENT.getCode())) {
            payType = 1;
        }else if(buyOrder.getOrderStatus().equals(BuyOrderStatus.UNPAID_EPOSIT.getCode())){
            payType = 2;
        }else {
            return BaseResult.failure("订单不需要支付");
        }
        // 获取回调参数
        PayOrder payOrder = new PayOrder();
        // 订单贸易号
        String outTradeNo = IdUtils.uuid();
        payOrder.setBody(openId);
        payOrder.setOutTradeNo(outTradeNo);
        payOrder.setPayType(payType);
        payOrder.setOrderType(0);
        payOrder.setId(buyOrderId);
        payOrder.setSubjecy(proName);
        payOrder.setTotalAmount(amount);

        return BaseResult.success(wxPostDataInfoService.getWxPayParams(payOrder));
    }

    /**
     * 点击“立即购买”时调用，同时生成订单数据
     *
     * */
    @ApiOperation("出售订单微信支付订金")
    @PostMapping("/getWeixinPaySaleOrderStr")
    public BaseResult getWeixinPaySaleOrderStr(@ApiParam("商品金额") @RequestParam("amount") String amount,
                                            @ApiParam("出售订单Id") @RequestParam("saleOrderId") Integer saleOrderId,
                                            @ApiParam("商品名称") @RequestParam("proName") String proName,
                                            @ApiParam("商品描述") @RequestParam("proDesc") String proDesc) {

        SaleOrderEntity saleOrder = saleOrderService.selectById(saleOrderId);
        int payType;
        if (saleOrder == null) {
            return BaseResult.failure("订单不存在");
        } else if (saleOrder.getOrderStatus().equals(SaleOrderStatus.PENDING_PAYMENT.getCode())) {
            payType = 1;
        }else if(saleOrder.getOrderStatus().equals(SaleOrderStatus.UNPAID_EPOSIT.getCode())){
            payType = 2;
        }else {
            return BaseResult.failure("订单不需要支付");
        }
        // 获取回调参数
        PayOrder payOrder = new PayOrder();
        // 订单贸易号
        String outTradeNo = IdUtils.uuid();
        payOrder.setBody(proDesc);
        payOrder.setOutTradeNo(outTradeNo);
        payOrder.setPayType(payType);
        payOrder.setId(saleOrderId);
        payOrder.setOrderType(1);
        payOrder.setSubjecy(proName);
        payOrder.setTotalAmount(amount);

        return BaseResult.success(wxPostDataInfoService.getWxPayParams(payOrder));
    }

    /**
     * 支付完成后的异步回调接口
     *
     * */
    @ApiOperation("微信支付回调接口")
    @PostMapping("/weixinPayNotify")
    @ResponseBody
    public String weixinPayNotify(HttpServletRequest request) {

        // 微信回调回来的是xml格式的文档。
        // 通过parseXml直接将request请求转换成xml再转换成map，然后就可以键值对取值了
        Map<String, String> map = new HashMap<>();
        try {
            map = WxUtil.parseXml(request);
        } catch (Exception e) {
            log.info("解析xml文档异常", e);
            map = null;
        }

        return wxPostDataInfoService.nofify(map);

    }

    /**
     * 点击“立即购买”时调用，同时生成订单数据
     *
     * */
    @ApiOperation("获取支付宝加签后的订单信息字符串")
    @PostMapping("/getAliPayOrderStr")
    public BaseResult getAliPayOrderStr(@ApiParam("商品金额") @RequestParam("amount") String amount,
                                        @ApiParam("购买订单Id") @RequestParam("buyOrderId") Integer buyOrderId,
                                        @ApiParam("商品名称") @RequestParam("proName") String proName,
                                        @ApiParam("商品描述") @RequestParam("proDesc") String proDesc) {

        BuyOrderEntity buyOrder = buyOrderService.selectById(buyOrderId);
        int payType;
        if (buyOrder == null) {
            return BaseResult.failure("订单不存在");
        } else if (buyOrder.getOrderStatus().equals(BuyOrderStatus.UNPAID_EPOSIT.getCode())) {
            payType = 1;
        }else if(buyOrder.getOrderStatus().equals(BuyOrderStatus.PENDING_PAYMENT.getCode())){
            payType = 2;
        }else {
            return BaseResult.failure("订单不需要支付");
        }
        // 获取回调参数
        PayOrder payOrder = new PayOrder();
        // 订单贸易号
        String outTradeNo = IdUtils.uuid();
        payOrder.setBody(proDesc);
        payOrder.setOutTradeNo(outTradeNo);
        payOrder.setPayType(payType);
        payOrder.setOrderType(0);
        payOrder.setId(buyOrderId);
        payOrder.setSubjecy(proName);
        payOrder.setTotalAmount(amount);
        Map<String, Object> map = new HashMap<>();
        map.put("orderString", alipaymentOrderService.getAliPayOrderStr(payOrder));
        return BaseResult.success(map);
    }

    /**
     * 点击“立即购买”时调用，同时生成订单数据
     *
     * */
    @ApiOperation("出售订单支付订金")
    @PostMapping("/getAliPaySaleOrderStr")
    public BaseResult getAliPaySaleOrderStr(@ApiParam("商品金额") @RequestParam("amount") String amount,
                                        @ApiParam("出售订单Id") @RequestParam("saleOrderId") Integer saleOrderId,
                                        @ApiParam("商品名称") @RequestParam("proName") String proName,
                                        @ApiParam("商品描述") @RequestParam("proDesc") String proDesc) {

        SaleOrderEntity saleOrder = saleOrderService.selectById(saleOrderId);
        int payType;
        if (saleOrder == null) {
            return BaseResult.failure("订单不存在");
        } else if (saleOrder.getOrderStatus().equals(SaleOrderStatus.PENDING_PAYMENT.getCode())) {
            payType = 1;
        }else if(saleOrder.getOrderStatus().equals(SaleOrderStatus.UNPAID_EPOSIT.getCode())){
            payType = 2;
        }else {
            return BaseResult.failure("订单不需要支付");
        }
        // 获取回调参数
        PayOrder payOrder = new PayOrder();
        // 订单贸易号
        String outTradeNo = IdUtils.uuid();
        payOrder.setBody(proDesc);
        payOrder.setOutTradeNo(outTradeNo);
        payOrder.setPayType(payType);
        payOrder.setId(saleOrderId);
        payOrder.setOrderType(1);
        payOrder.setSubjecy(proName);
        payOrder.setTotalAmount(amount);
        Map<String, Object> map = new HashMap<>();
        map.put("orderString", alipaymentOrderService.getAliPayOrderStr(payOrder));
        return BaseResult.success(map);
    }

    /**
     * 支付完成后的异步回调接口
     *
     * */
    @ApiOperation("支付宝支付回调接口")
    @PostMapping("/aliPayNotify")
    @ResponseBody
    public String aliPayNotify(HttpServletRequest request) throws UnsupportedEncodingException {

        // 获取回调参数
        //获取支付宝返回的参数集合
        Map<String, String[]> aliParams = request.getParameterMap();
        //用以存放转化后的参数集合
        Map<String, String> conversionParams = new HashMap<String, String>();
        for (Iterator<String> iter = aliParams.keySet().iterator(); iter.hasNext();) {
            String key = iter.next();
            String[] values = aliParams.get(key);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "UTF-8");
            conversionParams.put(key, valueStr);
        }
        log.info("==================返回参数集合："+conversionParams);
        return alipaymentOrderService.notify(conversionParams);
    }

    /**
     * 支付完成后的校验支付状态
     *
     * */
    @ApiOperation("支付宝支付交易支付状态")
    @PostMapping("aliPayCheck")
    public BaseResult aliPayCheck(@ApiParam("商户订单号") @RequestParam("outTradeNo") String outTradeNo) {
        int status = alipaymentOrderService.checkAlipay(outTradeNo);
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        return BaseResult.success(map);
    }


}
