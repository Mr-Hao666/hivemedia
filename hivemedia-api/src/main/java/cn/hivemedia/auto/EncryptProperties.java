package cn.hivemedia.auto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ZengXiong
 * @Description api请求加密配置
 * @Date 2018/11/23 09:20
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.encrypt")
public class EncryptProperties {
    /**
     * AES加密KEY
     */
    private String key;

    private String charset = "UTF-8";

    /**
     * 开启调试模式，调试模式下不进行加解密操作，用于像Swagger这种在线API测试场景
     */
    private boolean debug = false;

    /**
     * 签名过期时间（分钟）
     */
    private Long signExpireTime = 10L;
}
