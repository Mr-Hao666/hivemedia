package cn.honghuroad.utils;

import com.alibaba.fastjson.JSONException;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import java.io.IOException;
import java.util.Random;

/**
 * @author ZengXiong
 * @Description 腾讯云短信工具类
 * @Date 2019/01/09 16:10
 */
public class SendSMSUtils {
    public static void main(String[] args) {
        int appId = 1400115062;
        String appKey = "bca1db72359a1114e284e811391333bc";
        int templateId = 259975;
        String smsSign = "小楷科技";
        String str = "";
        try {
            //随机生成6位的验证码
            Random random = new Random();
            for (int i = 0; i < 6; i++) {
                str += random.nextInt(10);
            }
            String[] params = {str, "2"};
            SmsSingleSender ssender = new SmsSingleSender(appId, appKey);
            //签名参数未提供或者为空时会使用默认签名发送短信,这里是用户的手机号码
            SmsSingleSenderResult result = ssender.sendWithParam("86", "17621063686", templateId, params, smsSign, "", "");
            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
    }
}
