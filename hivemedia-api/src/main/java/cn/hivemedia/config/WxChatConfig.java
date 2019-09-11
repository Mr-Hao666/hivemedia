package cn.hivemedia.config;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceHttpClientImpl;
import org.springframework.context.annotation.Bean;

/**
 * @author lxy
 * @date 2018/5/21
 */
//@Configuration
public class WxChatConfig {

    //    @NotNull
    //    @Value("${wx.appId}")
    private String appId;

    //    @NotNull
    //    @Value("${wx.appSecret}")
    private String appSecret;

    @Bean(name = "wxMpService")
    public WxMpService getWxMpService() {
        WxMpService wxMpServiceHttp = new WxMpServiceHttpClientImpl();
        WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpInMemoryConfigStorage.setAppId(appId);
        wxMpInMemoryConfigStorage.setSecret(appSecret);
        wxMpServiceHttp.setWxMpConfigStorage(wxMpInMemoryConfigStorage);
        return wxMpServiceHttp;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
