package cn.hivemedia.tencentsms;

import cn.hivemedia.config.TencentConfig;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import cn.hivemedia.common.exception.RRException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p> 腾讯云短信接口 </p>
 *
 * @author lxy
 */
@Slf4j
@Component
public class TencentSmsSender {

    private static final Integer SIZE = 200;

    @Resource
    private TencentConfig tencentConfig;

    /**
     * 默認發送驗證碼短信
     *
     * @param mobile
     * @return
     */
    public String submit(String mobile, Map<String, Object> map) {
        List<String> list = new ArrayList<>();
        list.add(mobile);
        List<String> resultList = submit(list, null, map);
        if (!resultList.isEmpty()) {
            return resultList.get(0);
        }
        return null;
    }

    public List<String> submit(List<String> mobiles, String content, Map<String, Object> templateParas) throws RRException {

        if (mobiles == null || mobiles.isEmpty()) {
            throw new RRException("腾讯云短信通道参数：mobiles 不能为空");
        }

        if (mobiles.size() > SIZE) {
            throw new RRException("腾讯云短信通道最大群发上线200个");
        }

        List<String> smsIds = new ArrayList<>();
        String result;
        if (content != null && content.length() > 0) {
            result = sendSmsByContent(mobiles, content);
        } else {
            result = sendSmsByTemplate(mobiles, templateParas);
        }
        smsIds.add(result);
        return smsIds;
    }

    private String sendSmsByTemplate(List<String> mobiles, Map<String, Object> templateParas) {

        if (templateParas == null || templateParas.isEmpty()) {
            throw new RRException("腾讯云短信通道参数：templateParas 不能为空");
        }

        Integer templateId = Integer.parseInt((String) templateParas.get("templateId"));

        String signName = (String) templateParas.get("signName");

        if (templateId == 0) {
            throw new RRException("腾讯云短信通道 templateId 不能为空");
        }

        templateParas.remove("templateId");
        templateParas.remove("signName");
        Collection<Object> values = templateParas.values();
        ArrayList<String> objects = new ArrayList<>();
        if (!values.isEmpty()) {
            for (Object obj : values) {
                objects.add((String) obj);
            }
        }

        try {
            SmsMultiSender msender = new SmsMultiSender(tencentConfig.getAppid(), tencentConfig.getAppkey());
            // 签名参数未提供或者为空时，会使用默认签名发送短信
            SmsMultiSenderResult result = msender.sendWithParam("86", (ArrayList<String>) mobiles,
                    templateId, objects, signName, "", "");
            log.info("[腾讯云] 短信发送成功，手机号码为{},模板为{}", mobiles, templateParas);
            return JSON.toJSONString(result.getResponse());
        } catch (HTTPException | JSONException | IOException e) {
            log.error("[腾讯云] 短信发送异常", e);
            log.error("[腾讯云] 短信发送失败, req={}|{}", mobiles, templateParas);
        }
        return "";
    }

    private String sendSmsByContent(List<String> mobiles, String content) {

        SmsMultiSenderResult result = null;
        try {
            SmsMultiSender msender = new SmsMultiSender(tencentConfig.getAppid(), tencentConfig.getAppkey());

            result = msender.send(0, "86", (ArrayList<String>) mobiles, content, "", "");
            log.info("[腾讯云] 短信发送成功，手机号码为{},内容为{}", mobiles, content);

        } catch (HTTPException | JSONException | IOException e) {
            log.error("[腾讯云] 短信发送异常", e);
            log.error("[腾讯云] 短信发送失败, req={}|{}", mobiles, content);
        }
        return result != null ? JSON.toJSONString(result) : "";
    }

}
