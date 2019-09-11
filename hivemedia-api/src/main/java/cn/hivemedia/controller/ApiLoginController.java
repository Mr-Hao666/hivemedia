package cn.hivemedia.controller;

import cn.hivemedia.form.LoginForm;
import cn.hivemedia.msg.ICaptchaApi;
import cn.hivemedia.msg.model.MsgReq;
import cn.hivemedia.result.BaseResult;
import cn.hivemedia.service.UserService;
import cn.hivemedia.tencentsms.TencentSmsSender;
import cn.hivemedia.annotation.Login;
import cn.hivemedia.common.utils.R;
import cn.hivemedia.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZengXiong
 * @Description 登陆接口
 * @Date 2018/11/23 10:51
 */
@RestController
@RequestMapping("/api")
@Api(tags = "登录接口")
public class ApiLoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ICaptchaApi iCaptchaApi;
    @Autowired
    private TencentSmsSender tencentSmsSender;

    @PostMapping("/login")
    @ApiOperation("登录")
    public BaseResult login(@ApiParam("手机号码") @RequestParam("mobileNo") String mobileNo,
                            @ApiParam("验证码") @RequestParam("msgCode") String msgCode) {
        //表单校验
        LoginForm loginForm = new LoginForm();
        loginForm.setMobile(mobileNo);
        loginForm.setVerificationCode(msgCode);
        //用户登录
        Map<String, Object> map = userService.login(loginForm);

        //用户登录
        return BaseResult.success(map);
    }

    @PostMapping("/thirdLogin")
    @ApiOperation("第三方登录")
    public BaseResult thirdLogin(@ApiParam("微信openId") @RequestParam(value = "wxOpenId", required = false) String wxOpenId,
                                 @ApiParam("qq_openId") @RequestParam(value = "qqOpenId", required = false) String qqOpenId,
                                 @ApiParam("微博openId") @RequestParam(value = "weiboOpenId", required = false) String weiboOpenId) {
        //用户登录
        Map<String, Object> map = userService.thirdLogin(wxOpenId, qqOpenId, weiboOpenId);
        //用户登录
        return BaseResult.success(map);
    }

    @Login
    @PostMapping("/logout")
    @ApiOperation("退出")
    public BaseResult logout(@ApiIgnore @RequestParam("userId") long userId) {
        tokenService.expireToken(userId);
        return BaseResult.success();
    }

    @ApiOperation("发送验证码")
    @GetMapping("/sendMsgCode")
    public BaseResult sendMsgCode(@ApiParam("手机号码") @RequestParam("mobileNo") String mobileNo, @ApiParam("验证码业务类型：login(登录), bind(绑定支付宝), change(更换手机号码)") @RequestParam("msgType") String msgType) {
        //表单校验
        MsgReq msgReq = new MsgReq();
        msgReq.setMobileNo(mobileNo);
        msgReq.setMsgType(msgType);
        String result = iCaptchaApi.send(msgReq);
        if (StringUtils.isBlank(result)) {
            return BaseResult.failure("验证码发送失败");
        }
        return BaseResult.success();
    }

    @PostMapping("/test")
    public BaseResult test() {
        List<String> list = new ArrayList<>();
        list.add("15925681009");
        List<String> submit = tencentSmsSender.submit(list, "你的确认码是：123", null);
        Map<String, Object> map = new HashMap<>();
        map.put("result", submit);
        return BaseResult.success(map);
    }
}
