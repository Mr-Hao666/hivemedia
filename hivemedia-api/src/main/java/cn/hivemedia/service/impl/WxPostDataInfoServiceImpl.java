package cn.hivemedia.service.impl;

import cn.hivemedia.dao.WxPostDataInfoDao;
import cn.hivemedia.entity.WxPostDataInfoEntity;
import cn.hivemedia.entity.model.PayOrder;
import cn.hivemedia.service.BuyOrderService;
import cn.hivemedia.service.SaleOrderService;
import cn.hivemedia.service.WxPostDataInfoService;
import cn.hivemedia.utils.wx.IdUtils;
import cn.hivemedia.utils.wx.PayCommonUtil;
import cn.hivemedia.utils.wx.PayConfigUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.exception.RRException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

@Slf4j
@Service("wxPostDataInfoService")
public class WxPostDataInfoServiceImpl extends ServiceImpl<WxPostDataInfoDao, WxPostDataInfoEntity> implements WxPostDataInfoService {

    @Autowired
    protected BuyOrderService buyOrderService;

    @Autowired
    protected SaleOrderService saleOrderService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String nofify(Map<String, String> map) {
        if ("SUCCESS".equals(map.get("return_code"))) {
            String outTradeNo = map.get("out_trade_no");
            if (!StringUtils.isEmpty(outTradeNo)) {
                // 判断对应out_trade_no的数据是否存在，存在直接返回
                String returnCode = map.get("return_code");// 获取交易状态
                WxPostDataInfoEntity wxPostDataInfoEntity = this.baseMapper.selectByOutTradeNo(outTradeNo);
                if (wxPostDataInfoEntity == null) {
                    return "fail";
                } else {
                    wxPostDataInfoEntity.setAppid(map.get("appid"));
                    wxPostDataInfoEntity.setBanktype(map.get("bank_type"));
                    wxPostDataInfoEntity.setCashfee(map.get("cash_fee"));
                    wxPostDataInfoEntity.setIssubscribe(map.get("is_subscribe"));
                    wxPostDataInfoEntity.setNoncestr(map.get("nonce_str"));
                    wxPostDataInfoEntity.setOpenid(map.get("openid"));
                    wxPostDataInfoEntity.setOuttradeno(map.get("out_trade_no"));
                    wxPostDataInfoEntity.setResultcode(map.get("result_code"));
                    wxPostDataInfoEntity.setReturncode(returnCode);
                    wxPostDataInfoEntity.setTimeend(map.get("time_end"));
                    wxPostDataInfoEntity.setTotalfee(map.get("total_fee"));
                    wxPostDataInfoEntity.setTradetype(map.get("trade_type"));
                    wxPostDataInfoEntity.setTransactionid(map.get("transaction_id"));
                    int returnResult = this.baseMapper.updateById(wxPostDataInfoEntity);

                    if ("SUCCESS".equals(returnCode)) {
                        //只处理支付成功的订单: 修改交易表状态,支付成功
                        if (returnResult > 0) {
                            //0：支付购买订单相关费用
                            if (wxPostDataInfoEntity.getOrderType() == 0) {
                                buyOrderService.buyOrderPayRedirect(wxPostDataInfoEntity.getClubOrderId(), wxPostDataInfoEntity.getPayType(), 2);
                            } else {//0：支付出售订单相关费用
                                saleOrderService.saleOrderPayRedirect(wxPostDataInfoEntity.getClubOrderId(),2);
                            }
                            return "success";
                        }
                    }
                }
                return "fail";
            }
        }
        return "fail";
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> getWxPayParams(PayOrder payOrder) {
        Map<String, Object> configMap = new HashMap<>();
        log.info("获取微信支付最终调用参数");

        try {
            String outTradeNo = IdUtils.uuid(); // 订单号(自定义)
            String money = payOrder.getTotalAmount();
            String userId = payOrder.getSubjecy();
            String tradeType = "APP";
            String openid = payOrder.getBody();
            Map<String, String> payMap = PayCommonUtil.wxUnifiedorder(money, userId, tradeType, openid, outTradeNo);
            log.info("payMap--->" + payMap);

            String prepayId = Objects.toString(payMap.get("prepay_id"), "");
            String partnerid = Objects.toString(payMap.get("partnerid"), "");
            long time = System.currentTimeMillis() / 1000;
            String appid = PayConfigUtil.APP_ID;
            String key = PayConfigUtil.API_KEY; // key
            String nonceStr = PayCommonUtil.getRandomString(32);
            SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
            packageParams.put("appId", appid);
            packageParams.put("nonceStr", nonceStr);
            packageParams.put("package", "Sign=WXPay");
            packageParams.put("prepay_id", prepayId);
            packageParams.put("signType", "MD5");
            packageParams.put("timeStamp", time + "");
            WxPostDataInfoEntity newWxPostDataInfoEntity = new WxPostDataInfoEntity();
            newWxPostDataInfoEntity.setAppid(appid);
            newWxPostDataInfoEntity.setClubOrderId(payOrder.getId());
            newWxPostDataInfoEntity.setOrderType(payOrder.getOrderType());
            newWxPostDataInfoEntity.setPayType(payOrder.getPayType());
            newWxPostDataInfoEntity.setNoncestr(nonceStr);
            newWxPostDataInfoEntity.setOpenid(openid);
            newWxPostDataInfoEntity.setOuttradeno(outTradeNo);
            newWxPostDataInfoEntity.setTotalfee(money);
            newWxPostDataInfoEntity.setTradetype(tradeType);
            this.baseMapper.insert(newWxPostDataInfoEntity);
            String sign = PayCommonUtil.createSign("UTF-8", packageParams, key);

            configMap.put("appId", appid);
            configMap.put("timeStamp", time + "");
            configMap.put("nonceStr", nonceStr);
            configMap.put("signType", "MD5");
            configMap.put("paySign", payMap.get("sign"));
            configMap.put("paySign1", payMap.get("paySign"));
            configMap.put("sign", sign);
            configMap.put("partnerid", partnerid);
            configMap.put("prepayid", prepayId);
            configMap.put("package", "Sign=WXPay");
            log.info("支付参数---->" + configMap);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RRException("获取微信支付参数失败");
        }
        return configMap;
    }
}
