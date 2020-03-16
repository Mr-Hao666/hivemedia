package cn.honghuroad.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 杨浩
 * @create 2019-01-24 23:28
 **/
@Slf4j
public class VerifyIdcardUtil {
    private VerifyIdcardUtil() {
    }

    private static final VerifyIdcardUtil instance = new VerifyIdcardUtil();

    public static VerifyIdcardUtil getInstance() {
        return instance;
    }

    public boolean verifyIdcard(String cardNo, String realName) {
        String host = "http://aliyunverifyidcard.haoservice.com";
        String path = "/idcard/VerifyIdcardv2";
        String method = "GET";
        //AppKey：25684250     AppSecret：f5f73969b4bb521e53c13e803457d650
        //
        //AppCode：4e60c2f24cbd44d49c2e49f4629d1242
        String appcode = "4e60c2f24cbd44d49c2e49f4629d1242";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("cardNo", cardNo);
        querys.put("realName", realName);
        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            System.out.println(response.toString());
            //获取response的body
            String responseStr = EntityUtils.toString(response.getEntity());
            JSONObject jsonObject = JSONObject.parseObject(responseStr);
            log.info(responseStr);
            if (jsonObject.getInteger("error_code") == 0) {
                return jsonObject.getJSONObject("result").getBoolean("isok");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
        //成功示例
        // {
        //    "error_code": 0,
        //    "reason": "Success",
        //    "result": {
        //        "realname": "张三",
        //        /*真实姓名*/
        //        "idcard": "330329199001020022",
        //        /*身份证号码*/
        //        "isok": false
        //        /*true：匹配 false：不匹配*/
        //        ,
        //        "IdCardInfor": {
        //            "area": "山西省太原市清徐县",
        //            "sex": "男",
        //            "birthday": "1985-4-10"
        //        }
        //    }
        //}
        //失败示例
        //{
        //    "error_code": 206501,
        //    "reason": "NoExistERROR",
        //    "result": {
        //        "realname": "张三",
        //        "idcard": "110115198004232418",
        //        "isok": false
        //    }
        //}

    }

    public static void main(String[] args) {
        String idCardNo = "421083199305046415";
        String realName = "杨浩";
        VerifyIdcardUtil.getInstance().verifyIdcard(idCardNo, realName);

    }
}
