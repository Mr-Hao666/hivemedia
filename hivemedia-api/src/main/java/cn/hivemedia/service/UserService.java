package cn.hivemedia.service;

import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.entity.UserEntity;
import cn.hivemedia.form.LoginForm;

import java.util.Map;

/**
 * 用户信息表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:24:48
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 手机号登录
     *
     * @param form from
     * @return map
     */
    Map<String, Object> login(LoginForm form);

    /**
     * 用户更换电话绑定
     *
     * @param userId
     * @param phone
     * @param msgCode
     * @return
     */
    Integer bindAccountWithPhone(Long userId, String phone, String msgCode);

    /**
     * 绑定支付宝
     *
     * @param authCode
     * @return
     */
    boolean bindAlipay(Long userId, String mobileNo,String name,
                      String msgCode, String authCode);

    /**
     * 更新用户信息
     *
     * @param userId      用户ID
     * @param avatarId    头像ID
     * @param nickName    昵称
     * @param wxOpenId    微信openId
     * @param qqOpenId    QQopenId
     * @param weiboOpenId 微博openId
     * @param gender      性别 0:未知 1:男 2:女
     */
    void updateUserInfo(Long userId, Long avatarId, String nickName, String personalSign, String wxOpenId, String qqOpenId, String weiboOpenId, Integer gender);

    /**
     * 第三方登录
     *
     * @param wxOpenId    微信openId
     * @param qqOpenId    QQopenId
     * @param weiboOpenId 微博openId
     * @return
     */
    Map<String, Object> thirdLogin(String wxOpenId, String qqOpenId, String weiboOpenId);

    UserEntity info(Long userId);

    boolean verify(Long userId, String realName, String idCardNo);
}

