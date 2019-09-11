package cn.hivemedia.auto;

import cn.hivemedia.advice.EncryptRequestBodyAdvice;
import cn.hivemedia.advice.EncryptResponseBodyAdvice;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author ZengXiong
 * @Description 加解密自动配置
 * @Date 2018/11/23 15:13
 */
@Configuration
@Component
@EnableAutoConfiguration
@EnableConfigurationProperties(EncryptProperties.class)
public class EncryptAutoConfiguration {

    /**
     * 配置请求加密
     */
    @Bean
    public EncryptResponseBodyAdvice encryptResponseBodyAdvice() {
        return new EncryptResponseBodyAdvice();
    }

    /**
     * 配置请求解密
     */
    @Bean
    public EncryptRequestBodyAdvice encryptRequestBodyAdvice() {
        return new EncryptRequestBodyAdvice();
    }
}
