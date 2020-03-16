package cn.honghuroad.utils.wx;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public class PayCommonUtil {
    //随机字符串生成
    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }


    public static String getFirstCodeUrl(String productId) throws Exception {
        String currTime = PayCommonUtil.getCurrTime();
        String strTime = currTime.substring(8, currTime.length());
        String strRandom = PayCommonUtil.buildRandom(4) + "";
        String key = PayConfigUtil.API_KEY; // 证书号

        String appid = PayConfigUtil.APP_ID; // 公众账号ID
        String mch_id = PayConfigUtil.MCH_ID; // 商业号
        String time_stamp = getTime();// 时间戳
        String nonce_str = strTime + strRandom;// 随机字符串
        String product_id = productId;// 商品ID
        String sign = productId;// 签名

        SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
        packageParams.put("appid", appid);
        packageParams.put("mch_id", mch_id);
        packageParams.put("time_stamp", time_stamp);
        packageParams.put("product_id", product_id);

        sign = PayCommonUtil.createSign("UTF-8", packageParams, key);
        packageParams.put("sign", sign);

        String urlStr = "weixin：//wxpay/bizpayurl?";
        urlStr += "appid=" + appid;
        urlStr += "&mch_id=" + mch_id;
        urlStr += "&nonce_str=" + nonce_str;
        urlStr += "&product_id=" + product_id;
        urlStr += "&time_stamp=" + time_stamp;
        urlStr += "&sign=" + sign;
        return urlStr;

    }

    public static Map<String, String> wxUnifiedorder(String money, String userId, String outTradeNo) throws Exception {
        return wxUnifiedorder(money, userId, "NATIVE", null, outTradeNo);
    }

    public static Map<String, String> wxUnifiedorder(String money, String userId, String tradeType, String openid, String outTradeNo) throws Exception {
        // 账号信息
        String appid = PayConfigUtil.APP_ID; // appid
        // String appsecret = PayConfigUtil.APP_SECRET; // appsecret
        String mchId = PayConfigUtil.MCH_ID; // 商业号
        String key = PayConfigUtil.API_KEY; // key

        String currTime = PayCommonUtil.getCurrTime();
        String strTime = currTime.substring(8, currTime.length());
        String strRandom = PayCommonUtil.buildRandom(4) + "";
        String nonceStr = strTime + strRandom;

        int orderPrice = (int) (Double.parseDouble(money) * 100); // 价格 注意：价格的单位是分
        if (orderPrice == 0) {
            orderPrice = 1;
        }

        //测试数据：
        //TODO
        // orderPrice =  1;

        String body = "商品描述"; // 商品描述


        // 获取发起电脑 ip
//        String spbillCreateIp = PayConfigUtil.CREATE_IP;
        String spbillCreateIp = localIp();
        // 回调接口
        String notifyUrl = PayConfigUtil.NOTIFY_URL;
//        String tradeType = "NATIVE";

        SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
        packageParams.put("appid", appid);
        packageParams.put("attach", userId);
        packageParams.put("body", body);
        packageParams.put("mch_id", mchId);
        packageParams.put("product_id", "0");
        packageParams.put("nonce_str", nonceStr);
        packageParams.put("notify_url", notifyUrl);
        packageParams.put("out_trade_no", outTradeNo);
        packageParams.put("spbill_create_ip", spbillCreateIp);
        packageParams.put("total_fee", orderPrice);
        packageParams.put("trade_type", tradeType);
        if (StringUtils.isNotEmpty(openid)) {
            packageParams.put("openid", openid);
        }

        String sign = PayCommonUtil.createSign("UTF-8", packageParams, key);
        packageParams.put("sign", sign);

        String requestXML = PayCommonUtil.getRequestXml(packageParams);
        System.out.println(requestXML);
        log.info("requestXML--->" + requestXML);

        String resXml = HttpUtil.postData(PayConfigUtil.UFDODER_URL, requestXML);

        Map<String, String> map = XMLUtil.doXMLParse(resXml);
        map.put("timestamp", getTime());
        map.put("nonceStr", nonceStr);
        map.put("paySign", sign);
        map.put("partnerid", mchId);

        return map;
    }


    public static String getCodeUrl(String money, String userId, String outTradeNo) throws Exception {
        Map<String, String> map = wxUnifiedorder(money, userId, outTradeNo);
        // String return_code = (String) map.get("return_code");
        // String prepay_id = (String) map.get("prepay_id");
        String urlCode = map.get("code_url");

        return urlCode;
    }

    /**
     * 获取本机Ip
     * <p>
     * 通过 获取系统所有的networkInterface网络接口 然后遍历 每个网络下的InterfaceAddress组。 获得符合
     * <code>InetAddress instanceof Inet4Address</code> 条件的一个IpV4地址
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String localIp() {
        String ip = null;
        Enumeration allNetInterfaces;
        try {
            allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                List<InterfaceAddress> InterfaceAddress = netInterface.getInterfaceAddresses();
                for (InterfaceAddress add : InterfaceAddress) {
                    InetAddress Ip = add.getAddress();
                    if (Ip != null && Ip instanceof Inet4Address) {
                        ip = Ip.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            log.warn("获取本机Ip失败:异常信息:" + e.getMessage());
        }
        return ip;
    }

    /**
     * 是否签名正确,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
     *
     * @return boolean
     */
    public static boolean isTenpaySign(String characterEncoding, SortedMap<Object, Object> packageParams, String API_KEY) {
        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (!"sign".equals(k) && null != v && !"".equals(v)) {
                sb.append(k + "=" + v + "&");
            }
        }

        sb.append("key=" + API_KEY);

        //算出摘要
        String mysign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toLowerCase();
//        String mysign = "123";
        String tenpaySign = ((String) packageParams.get("sign")).toLowerCase();

        //System.out.println(tenpaySign + "    " + mysign);
        return tenpaySign.equals(mysign);
    }

    /**
     * @param characterEncoding 编码格式
     * @param packageParams     请求参数
     * @return
     * @author
     * @date 2016-4-22
     * @Description：sign签名
     */
    public static String createSign(String characterEncoding, SortedMap<Object, Object> packageParams, String API_KEY) {
        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = entry.getKey().toString();
            String v = entry.getValue().toString();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        String finalStr = null;
        if (StringUtils.isNotEmpty(API_KEY)) {
            sb.append("key=" + API_KEY);
            finalStr = sb.toString();
        } else {
            finalStr = sb.toString();
            finalStr = finalStr.substring(0, finalStr.length() - 1);
        }
        log.info("sign md5 --->" + finalStr);
        String sign = MD5Util.MD5Encode(finalStr, characterEncoding).toUpperCase();
//        String sign = "123";
        return sign;
    }

    /**
     * @param parameters 请求参数
     * @return
     * @author
     * @date 2016-4-22
     * @Description：将请求参数转换为xml格式的string
     */
    public static String getRequestXml(SortedMap<Object, Object> parameters) {
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = entry.getKey().toString();
            String v = entry.getValue().toString();
//            if ("detail".equalsIgnoreCase(k)) {
            if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
            } else {
                sb.append("<" + k + ">" + v + "</" + k + ">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }

    /**
     * 取出一个指定长度大小的随机正整数.
     *
     * @param length int 设定所取出随机数的长度。length小于11
     * @return int 返回生成的随机数。
     */
    public static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }

    /**
     * 获取当前时间 yyyyMMddHHmmss
     *
     * @return String
     */
    public static String getCurrTime() {
        Date now = new Date();
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String s = outFormat.format(now);
        return s;
    }

    /**
     * 获取时间戳
     */
    private static String getTime() {
        Date time = new Date();
        long longTime = time.getTime();
        return String.valueOf(longTime / 1000);
    }

    public static void main(String[] args) {
        System.out.println(getTime());
    }

}