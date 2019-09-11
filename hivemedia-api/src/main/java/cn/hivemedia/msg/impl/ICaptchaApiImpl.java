package cn.hivemedia.msg.impl;

import cn.hivemedia.msg.model.MsgReq;
import cn.hivemedia.common.utils.RedisUtils;
import cn.hivemedia.common.enums.VerificationEnum;
import cn.hivemedia.msg.ICaptchaApi;
import cn.hivemedia.tencentsms.TencentSmsSender;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lxy
 */
@Service
public class ICaptchaApiImpl implements ICaptchaApi {

    @Autowired
    private TencentSmsSender tencentSmsSender;

    @Autowired
    private RedisUtils redisUtils;

    private static int TIME = 2;

    @Override
    public String send(MsgReq req) {
        String key = getKey(req);

        String code = generatorCaptchaVal(6);
        redisUtils.set(key, code, 60 * TIME);
        Map<String, Object> map = new HashMap<>();
        map.put("templateId", "259975");
        map.put("signName", "小楷科技");
        // 验证码数字需要保存到缓存
        map.put("code", code);
        map.put("time", TIME+"");

        return tencentSmsSender.submit(req.getMobileNo(), map);
    }

    @Override
    public boolean checkCode(MsgReq req) {
        String key = getKey(req);

        String value = redisUtils.get(key);
//        String value = "123456";

        if (value == null || value.length() == 0) {
            return false;
        }
        if (value.equals(req.getCode())) {
            return true;
        }

        return false;
    }


    private String getKey(MsgReq req) {
        return VerificationEnum.getType(req.getMsgType()).getType()+ ":" + req.getMobileNo();
    }


    private String generatorCaptchaVal(int length) {

        // 生成验证码明文
        return RandomStringUtils.random(length, false, true);
    }


}
