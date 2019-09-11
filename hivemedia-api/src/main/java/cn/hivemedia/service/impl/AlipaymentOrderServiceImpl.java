package cn.hivemedia.service.impl;

import cn.hivemedia.config.AlipayConfig;
import cn.hivemedia.dao.AlipaymentOrderDao;
import cn.hivemedia.dao.BuyOrderDao;
import cn.hivemedia.entity.AlipaymentOrderEntity;
import cn.hivemedia.entity.BuyOrderEntity;
import cn.hivemedia.entity.model.PayOrder;
import cn.hivemedia.service.AlipaymentOrderService;
import cn.hivemedia.service.BuyOrderService;
import cn.hivemedia.service.SaleOrderService;
import cn.hivemedia.utils.CommonUtils;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.exception.RRException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service("alipaymentOrderService")
@Slf4j
public class AlipaymentOrderServiceImpl extends ServiceImpl<AlipaymentOrderDao, AlipaymentOrderEntity> implements AlipaymentOrderService {

    @Autowired
    protected AlipaymentOrderDao alipaymentOrderDao;

    @Autowired
    protected BuyOrderDao buyOrderDao;

    @Autowired
    protected BuyOrderService buyOrderService;

    @Autowired
    protected SaleOrderService saleOrderService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String getAliPayOrderStr(PayOrder payOrder) {
        //最终返回加签之后的，app需要传给支付宝app的订单信息字符串
        String orderString = "";
        log.info("==================支付宝下单,商户订单号为：" + payOrder.getOutTradeNo());

        //创建商户支付宝订单(因为需要记录每次支付宝支付的记录信息，单独存一个表跟商户订单表关联，以便以后查证)
        BigDecimal totalAmount = new BigDecimal(payOrder.getTotalAmount());
        AlipaymentOrderEntity alipaymentOrder = new AlipaymentOrderEntity();
        alipaymentOrder.setClubOrderId(payOrder.getId());//商家订单主键
        alipaymentOrder.setPayType(payOrder.getPayType());//支付类型
        alipaymentOrder.setOutTradeNo(payOrder.getOutTradeNo());//商户订单号
        alipaymentOrder.setOrderType(payOrder.getOrderType());//订单类型
        alipaymentOrder.setTradeStatus(0);//交易状态
        alipaymentOrder.setTotalAmount(new BigDecimal(payOrder.getTotalAmount()));//订单金额
        alipaymentOrder.setReceiptAmount(totalAmount);//实收金额
        alipaymentOrder.setInvoiceAmount(totalAmount);//开票金额
        alipaymentOrder.setBuyerPayAmount(totalAmount);//付款金额
        alipaymentOrder.setRefundFee(BigDecimal.ZERO);    //总退款金额

        try {
            //实例化客户端（参数：网关地址、商户appid、商户私钥、格式、编码、支付宝公钥、加密类型），为了取得预付订单信息
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID,
                    AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET,
                    AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);

            //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
            AlipayTradeAppPayRequest ali_request = new AlipayTradeAppPayRequest();

            //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式
            AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();

            //业务参数传入,可以传很多，参考API
            //model.setPassbackParams(URLEncoder.encode(request.getBody().toString())); //公用参数（附加数据）
            model.setBody(payOrder.getBody());                       //对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。
            model.setSubject(payOrder.getSubjecy());                 //商品名称
            model.setOutTradeNo(payOrder.getOutTradeNo());           //商户订单号(自动生成)
            // model.setTimeoutExpress("30m");     			  //交易超时时间
            model.setTotalAmount(payOrder.getTotalAmount());         //支付金额
            model.setProductCode("QUICK_MSECURITY_PAY");              //销售产品码（固定值）
            ali_request.setBizModel(model);
            log.info("====================异步通知的地址为：" + AlipayConfig.notify_url);
            ali_request.setNotifyUrl(AlipayConfig.notify_url);        //异步回调地址（后台）
            // ali_request.setReturnUrl(AlipayConfig.return_url);	    //同步回调地址（APP）

            // 这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse alipayTradeAppPayResponse = alipayClient.sdkExecute(ali_request); //返回支付宝订单信息(预处理)
            orderString = alipayTradeAppPayResponse.getBody();//就是orderString 可以直接给APP请求，无需再做处理。
            alipaymentOrderDao.insert(alipaymentOrder);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            log.info("与支付宝交互出错，未能生成订单，请检查代码！");
        }

        return orderString;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String notify(Map<String, String> conversionParams) {
        log.info("==================支付宝回调异步请求逻辑处理");
        //签名验证(对支付宝返回的数据验证，确定是支付宝返回的)
        boolean signVerified = false;
        try {
            //调用SDK验证签名
            signVerified = AlipaySignature.rsaCheckV1(conversionParams, AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.CHARSET, AlipayConfig.SIGNTYPE);
        } catch (AlipayApiException e) {
            log.error("==================验签失败 ！");
            e.printStackTrace();
        }

        //对验签进行处理
        if (signVerified) {
            //验签通过
            //获取需要保存的数据
            String appId = conversionParams.get("app_id");//支付宝分配给开发者的应用Id
            String notifyTime = conversionParams.get("notify_time");//通知时间:yyyy-MM-dd HH:mm:ss
            String gmtCreate = conversionParams.get("gmt_create");//交易创建时间:yyyy-MM-dd HH:mm:ss
            String gmtPayment = conversionParams.get("gmt_payment");//交易付款时间
            String gmtRefund = conversionParams.get("gmt_refund");//交易退款时间
            String gmtClose = conversionParams.get("gmt_close");//交易结束时间
            String tradeNo = conversionParams.get("trade_no");//支付宝的交易号
            String outTradeNo = conversionParams.get("out_trade_no");//获取商户之前传给支付宝的订单号（商户系统的唯一订单号）
            String outBizNo = conversionParams.get("out_biz_no");//商户业务号(商户业务ID，主要是退款通知中返回退款申请的流水号)
            String buyerLogonId = conversionParams.get("buyer_logon_id");//买家支付宝账号
            String sellerId = conversionParams.get("seller_id");//卖家支付宝用户号
            String sellerEmail = conversionParams.get("seller_email");//卖家支付宝账号
            BigDecimal totalAmount = new BigDecimal(conversionParams.get("total_amount"));//订单金额:本次交易支付的订单金额，单位为人民币（元）
            String receiptAmount = conversionParams.get("receipt_amount");//实收金额:商家在交易中实际收到的款项，单位为元
            String invoiceAmount = conversionParams.get("invoice_amount");//开票金额:用户在交易中支付的可开发票的金额
            String buyerPayAmount = conversionParams.get("buyer_pay_amount");//付款金额:用户在交易中支付的金额
            String tradeStatus = conversionParams.get("trade_status");// 获取交易状态

            //支付宝官方建议校验的值（out_trade_no、total_amount、sellerId、app_id）
            AlipaymentOrderEntity alipaymentOrder = this.selectByOutTradeNo(outTradeNo);

            if (alipaymentOrder != null && totalAmount.compareTo(alipaymentOrder.getTotalAmount()) == 0 && AlipayConfig.APPID.equals(appId)) {
                //修改数据库支付宝订单表(因为要保存每次支付宝返回的信息到数据库里，以便以后查证)
                alipaymentOrder.setNotifyTime(CommonUtils.dateFormat(notifyTime));
                alipaymentOrder.setGmtCreate(CommonUtils.dateFormat(gmtCreate));
                alipaymentOrder.setGmtPayment(CommonUtils.dateFormat(gmtPayment));
                alipaymentOrder.setGmtRefund(CommonUtils.dateFormat(gmtRefund));
                alipaymentOrder.setGmtClose(CommonUtils.dateFormat(gmtClose));
                alipaymentOrder.setTradeNo(tradeNo);
                alipaymentOrder.setOutBizNo(outBizNo);
                alipaymentOrder.setBuyerLogonId(buyerLogonId);
                alipaymentOrder.setSellerId(sellerId);
                alipaymentOrder.setSellerEmail(sellerEmail);
                alipaymentOrder.setTotalAmount(totalAmount);
                alipaymentOrder.setReceiptAmount(new BigDecimal(StringUtils.isNotEmpty(receiptAmount) ? receiptAmount : "0.00"));
                alipaymentOrder.setInvoiceAmount(new BigDecimal(StringUtils.isNotEmpty(invoiceAmount) ? receiptAmount : "0.00"));
                alipaymentOrder.setBuyerPayAmount(new BigDecimal(StringUtils.isNotEmpty(buyerPayAmount) ? receiptAmount : "0.00"));
                // 判断交易结果
                switch (tradeStatus) {
                    // 交易结束并不可退款
                    case "TRADE_FINISHED":
                        alipaymentOrder.setTradeStatus(3);
                        break;
                    // 交易支付成功
                    case "TRADE_SUCCESS":
                        alipaymentOrder.setTradeStatus(2);
                        break;
                    // 未付款交易超时关闭或支付完成后全额退款
                    case "TRADE_CLOSED":
                        alipaymentOrder.setTradeStatus(1);
                        break;
                    // 交易创建并等待买家付款
                    case "WAIT_BUYER_PAY":
                        alipaymentOrder.setTradeStatus(0);
                        break;
                    default:
                        break;
                }
                //更新交易表中状态
                int returnResult = alipaymentOrderDao.updateById(alipaymentOrder);
                if ("TRADE_SUCCESS".equals(tradeStatus)) {
                    //只处理支付成功的订单: 修改交易表状态,支付成功
                    if (returnResult > 0) {
                        log.info("returnResult:" + returnResult);
                        //0：支付购买订单相关费用
                        log.info("alipaymentOrder:" + alipaymentOrder);
                        if (alipaymentOrder.getOrderType() == 0) {
                            buyOrderService.buyOrderPayRedirect(alipaymentOrder.getClubOrderId(), alipaymentOrder.getPayType(), 1);
                        } else {//0：支付出售订单相关费用
                            saleOrderService.saleOrderPayRedirect(alipaymentOrder.getClubOrderId(), 1);
                        }
                        return "success";
                    } else {
                        return "fail";
                    }
                } else {
                    return "fail";
                }
            } else {
                log.info("==================支付宝官方建议校验的值（out_trade_no、total_amount、sellerId、app_id）,不一致！返回fail");
                return "fail";
            }
        } else {  //验签不通过
            log.error("==================验签不通过 ！");
            return "fail";
        }
    }

    @Override
    public int checkAlipay(String outTradeNo) {
        log.info("==================向支付宝发起查询，查询商户订单号为：" + outTradeNo);
        try {
            //实例化客户端（参数：网关地址、商户appid、商户私钥、格式、编码、支付宝公钥、加密类型）
            AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID,
                    AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET,
                    AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
            AlipayTradeQueryRequest alipayTradeQueryRequest = new AlipayTradeQueryRequest();
            alipayTradeQueryRequest.setBizContent("{" +
                    "\"out_trade_no\":\"" + outTradeNo + "\"" +
                    "}");
            AlipayTradeQueryResponse alipayTradeQueryResponse = alipayClient.execute(alipayTradeQueryRequest);
            if (alipayTradeQueryResponse.isSuccess()) {

                AlipaymentOrderEntity alipaymentOrder = this.selectByOutTradeNo(outTradeNo);
                //修改数据库支付宝订单表
                alipaymentOrder.setTradeNo(alipayTradeQueryResponse.getTradeNo());
                alipaymentOrder.setBuyerLogonId(alipayTradeQueryResponse.getBuyerLogonId());
                alipaymentOrder.setTotalAmount(new BigDecimal(alipayTradeQueryResponse.getTotalAmount()));
                alipaymentOrder.setReceiptAmount(new BigDecimal(alipayTradeQueryResponse.getReceiptAmount()));
                alipaymentOrder.setInvoiceAmount(new BigDecimal(alipayTradeQueryResponse.getInvoiceAmount()));
                alipaymentOrder.setBuyerPayAmount(new BigDecimal(alipayTradeQueryResponse.getBuyerPayAmount()));
                switch (alipayTradeQueryResponse.getTradeStatus()) // 判断交易结果
                {
                    case "TRADE_FINISHED": // 交易结束并不可退款
                        alipaymentOrder.setTradeStatus(3);
                        break;
                    case "TRADE_SUCCESS": // 交易支付成功
                        alipaymentOrder.setTradeStatus(2);
                        break;
                    case "TRADE_CLOSED": // 未付款交易超时关闭或支付完成后全额退款
                        alipaymentOrder.setTradeStatus(1);
                        break;
                    case "WAIT_BUYER_PAY": // 交易创建并等待买家付款
                        alipaymentOrder.setTradeStatus(0);
                        break;
                    default:
                        break;
                }
                alipaymentOrderDao.updateById(alipaymentOrder); //更新表记录
                return alipaymentOrder.getTradeStatus();
            } else {
                log.info("==================调用支付宝查询接口失败！");
            }
        } catch (AlipayApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 订金退款
     *
     * @param orderEntity
     * @return
     */
    @Override
    public boolean refund(BuyOrderEntity orderEntity) {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET,
                AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
        AlipaymentOrderEntity alipayOrder = null;
        if (orderEntity.getBuyType() == 1) {
            alipayOrder = this.selectByBuyOrder(orderEntity.getSaleOrderId(), 2, 1);
        } else if (orderEntity.getBuyType() == 2) {
            alipayOrder = this.selectByBuyOrder(orderEntity.getId(), 1, 0);
        }
        if (alipayOrder == null) {
            throw new RRException("未找到支付记录001");
        }
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\"" + alipayOrder.getOutTradeNo() + "\"," +
                "\"trade_no\":\"" + alipayOrder.getTradeNo() + "\"," +
                "\"refund_amount\":" + alipayOrder.getBuyerPayAmount() + "," +
                "\"refund_reason\":\"订金退还\"" +
                "  }");
        AlipayTradeRefundResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if (response != null && response.isSuccess()) {
            alipayOrder.setTradeStatus(1);
            alipayOrder.setRefundFee(alipayOrder.getTotalAmount());
            alipaymentOrderDao.updateById(alipayOrder);
            log.info("支付宝订单{}退款成功，金额为", alipayOrder.getOutTradeNo(), alipayOrder.getTotalAmount());
            return true;
        } else {
            throw new RRException("退款失败002");
        }
    }

    @Override
    public AlipaymentOrderEntity selectByOutTradeNo(String outTradeNo) {
        Wrapper<AlipaymentOrderEntity> wrapper =
                new EntityWrapper<AlipaymentOrderEntity>().eq("out_trade_no", outTradeNo);
        List<AlipaymentOrderEntity> list = alipaymentOrderDao.selectList(wrapper);
        return list != null && list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public AlipaymentOrderEntity selectByBuyOrder(Long orderId, Integer payType, Integer orderType) {
        Wrapper<AlipaymentOrderEntity> wrapper =
                new EntityWrapper<AlipaymentOrderEntity>().eq("club_order_id", orderId)
                        .and().eq("pay_type", payType)
                        .and().eq("trade_status", 2)
                        .and().eq("order_type", orderType);
        List<AlipaymentOrderEntity> list = alipaymentOrderDao.selectList(wrapper);
        return list != null && list.size() > 0 ? list.get(0) : null;
    }
}
