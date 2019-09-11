package cn.hivemedia.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * <p> 腾讯云配置参数 </p>
 *
 * @author lxy
 */
@Data
@Component
@Validated
public class TencentConfig {
    /**
     * appid
     */
    @NotNull
    @Value("${cloud.msg.sms.tencent.appid}")
    private Integer appid = 1400115062;
    /**
     * appkey
     */
    @NotNull
    @Value("${cloud.msg.sms.tencent.appkey}")
    private String appkey = "bca1db72359a1114e284e811391333bc";

}
