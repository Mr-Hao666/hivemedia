package cn.hivemedia.service.impl;

import cn.hivemedia.dao.SysOssDao;
import cn.hivemedia.dao.UserAccountDao;
import cn.hivemedia.dao.UserDao;
import cn.hivemedia.entity.SysOssEntity;
import cn.hivemedia.entity.TokenEntity;
import cn.hivemedia.entity.UserAccountEntity;
import cn.hivemedia.entity.UserEntity;
import cn.hivemedia.form.LoginForm;
import cn.hivemedia.msg.ICaptchaApi;
import cn.hivemedia.msg.model.MsgReq;
import cn.hivemedia.service.UserService;
import cn.hivemedia.utils.Query;
import cn.hivemedia.utils.VerifyIdcardUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.enums.VerificationEnum;
import cn.hivemedia.common.exception.RRException;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

//import com.alipay.api.AlipayClient;
//import com.alipay.api.DefaultAlipayClient;
//import com.alipay.api.request.AlipaySystemOauthTokenRequest;
//import com.alipay.api.request.AlipayUserInfoShareRequest;
//import com.alipay.api.response.AlipaySystemOauthTokenResponse;
//import com.alipay.api.response.AlipayUserInfoShareResponse;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ICaptchaApi iCaptchaApi;

    @Autowired
    private UserAccountDao userAccountDao;

    @Autowired
    private SysOssDao ossDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<UserEntity> page = this.selectPage(new Query<UserEntity>(params).getPage(),
                new EntityWrapper<UserEntity>());

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> login(LoginForm form) {

        Map<String, Object> map = new HashMap<>(2);
        MsgReq msgReq = new MsgReq();
        msgReq.setMsgType(VerificationEnum.LOGIN.getType());
        msgReq.setMobileNo(form.getMobile());
        msgReq.setCode(form.getVerificationCode());

        if (!iCaptchaApi.checkCode(msgReq)) {
            throw new RRException("验证码错误");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setPhone(form.getMobile());
        Wrapper<UserEntity> wrapper =
                new EntityWrapper<UserEntity>().eq("phone", form.getMobile());

        if (CollectionUtils.isEmpty(baseMapper.selectList(wrapper))) {
            this.insert(userEntity);
            UserAccountEntity userAccountEntity = new UserAccountEntity();
            userAccountEntity.setUserId(userEntity.getId());
            userAccountEntity.setRemainingMoney(BigDecimal.ZERO);
            userAccountDao.insert(userAccountEntity);
        } else {
            userEntity = this.selectOne(wrapper);
        }
        return getLoginMap(map, userEntity);
    }

    /**
     * 封装登录信息
     */
    private Map<String, Object> getLoginMap(Map<String, Object> map, UserEntity userEntity) {
        SysOssEntity ossEntity = ossDao.selectById(userEntity.getAvatarId());
        if (ossEntity == null) {
            userEntity.setAvatarUrl("");
        } else {
            userEntity.setAvatarUrl(Optional.ofNullable(ossEntity.getUrl()).orElse(""));
        }
        TokenEntity token = tokenService.createToken(userEntity.getId());
        map.put("userInfo", userEntity);
        map.put("token", token.getToken());

        return map;
    }

    @Override
    public Map<String, Object> thirdLogin(String wxOpenId, String qqOpenId, String weiboOpenId) {
        Map<String, Object> map = new HashMap<>(2);
        UserEntity userEntity = new UserEntity();
        if (StringUtils.isNotBlank(wxOpenId)) {
            userEntity.setWxOpenId(wxOpenId);
        }
        if (StringUtils.isNotBlank(qqOpenId)) {
            userEntity.setQqOpenId(qqOpenId);
        }
        if (StringUtils.isNotBlank(wxOpenId)) {
            userEntity.setWeiboOpenId(weiboOpenId);
        }
        Wrapper<UserEntity> wrapper = new EntityWrapper<UserEntity>()
                .eq("wx_open_id", wxOpenId).or()
                .eq("qq_open_id", qqOpenId).or()
                .eq("weibo_open_id", weiboOpenId)
                .and().isNotNull("phone");
        userEntity = this.selectOne(wrapper);
        if (userEntity == null) {
            throw new RRException("请绑定手机号登录！");
        }
        return getLoginMap(map, userEntity);
    }

    @Override
    public UserEntity info(Long userId) {
        UserEntity userEntity = this.baseMapper.selectById(userId);
        SysOssEntity ossEntity = ossDao.selectById(userEntity.getAvatarId());
        if (ossEntity == null) {
            userEntity.setAvatarUrl("");
        } else {
            userEntity.setAvatarUrl(Optional.ofNullable(ossEntity.getUrl()).orElse(""));
        }
        return userEntity;
    }

    /**
     * 实名验证方法
     *
     * @param userId   用户ID
     * @param realName 真实姓名
     * @param idCardNo 身份证号码
     * @return 是否通过验证
     */
    @Override
    public boolean verify(Long userId, String realName, String idCardNo) {
        UserEntity userEntity = this.baseMapper.selectById(userId);
        if (userEntity != null) {
            if (userEntity.getRealNameAuthorized() == 1) {
                return true;
            }
            if (VerifyIdcardUtil.getInstance().verifyIdcard(idCardNo, realName)) {
                userEntity.setRealNameAuthorized(1);
                userEntity.setName(realName);
                userEntity.setIdCardNo(idCardNo);
                return this.updateById(userEntity);
            }
        }
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see UserService#bindAlipay(java.lang.String)
     */
    @Override
    public boolean bindAlipay(Long userId, String mobileNo, String name, String msgCode, String authCode) {
        MsgReq msgReq = new MsgReq();
        msgReq.setMsgType(VerificationEnum.BINDING.getType());
        msgReq.setMobileNo(mobileNo);
        msgReq.setCode(msgCode);


        Wrapper<UserEntity> wrapper =
                new EntityWrapper<UserEntity>().eq("phone", mobileNo)
                        .eq("id", userId);
        if (CollectionUtils.isEmpty(baseMapper.selectList(wrapper))) {
            throw new RRException("用户不存在");
        }
        if (!iCaptchaApi.checkCode(msgReq)) {
            throw new RRException("验证码错误");
        }
        UserEntity userEntity = this.selectById(userId);
        userEntity.setName(name);
        userEntity.setAlipayAccount(authCode);
        return this.updateById(userEntity);
    }

    @Override
    public void updateUserInfo(Long userId, Long ossId, String nickName, String personalSign, String wxOpenId, String qqOpenId, String weiboOpenId, Integer sex) {
        UserEntity userEntity = this.selectById(userId);
        if (userEntity == null) {
            throw new RRException("用户不存在!");
        }
        if (ossId != null && ossId > 0) {
            userEntity.setAvatarId(ossId);
        }
        if (sex != null && sex >= 0) {
            userEntity.setGender(sex);
        }
        if (StringUtils.isNotBlank(nickName)) {
            userEntity.setNickname(nickName);
        }
        if (StringUtils.isNotBlank(personalSign)) {
            userEntity.setPersonalSign(personalSign);
        }
        if (StringUtils.isNotBlank(wxOpenId)) {
            userEntity.setWxOpenId(wxOpenId);
        }
        if (StringUtils.isNotBlank(qqOpenId)) {
            userEntity.setQqOpenId(qqOpenId);
        }
        if (StringUtils.isNotBlank(wxOpenId)) {
            userEntity.setWeiboOpenId(weiboOpenId);
        }
        this.updateAllColumnById(userEntity);
    }

    @Override
    public Integer bindAccountWithPhone(Long userId, String phone, String msgCode) {
        MsgReq msgReq = new MsgReq();
        msgReq.setMsgType(VerificationEnum.CHANGE.getType());
        msgReq.setMobileNo(phone);
        msgReq.setCode(msgCode);

        if (!iCaptchaApi.checkCode(msgReq)) {
            throw new RRException("验证码错误");
        }

        UserEntity userEntity = baseMapper.selectById(userId);
        userEntity.setPhone(phone);
        return baseMapper.updateById(userEntity);
    }

}
