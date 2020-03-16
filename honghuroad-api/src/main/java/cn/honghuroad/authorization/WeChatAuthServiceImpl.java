package cn.honghuroad.authorization;

import cn.honghuroad.authorization.model.AuthorReq;
import cn.honghuroad.authorization.model.AuthorRsp;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author lxy
 * @date 2019/1/3
 */
public class WeChatAuthServiceImpl extends AbstractAuthorService implements AuthorService{

    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatAuthServiceImpl.class);

    @Autowired
    private WxMpService wxMpService;

    @Override
    public AuthorRsp author(AuthorReq req) {
        return null;
    }

    public WxMpOAuth2AccessToken authGetUserInfo(String authorizationCode) throws WxErrorException {
        // 通过授权code获取用户的access_token
        LOGGER.info("paramter: authorizationCode:{}", authorizationCode);
        System.err.println(wxMpService);
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = null;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(authorizationCode);
        } catch (WxErrorException e) {
            e.printStackTrace();
            return null;
        }
        LOGGER.info("authorizationCode get wxMpOAuth2AccessToken:{}", wxMpOAuth2AccessToken);

        // 通过用户的access_token获取用户详细信息
        WxMpUser wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
        LOGGER.info("authGetUserInfo get wxMpUser:{}", wxMpUser);
        if (wxMpUser.getOpenId() == null) {
           //"没有获取到用户信息");

        }
        return wxMpOAuth2AccessToken;
    }

}
