package cn.hivemedia.controller;

import cn.hivemedia.result.BaseResult;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import cn.hivemedia.common.utils.R;
import cn.hivemedia.entity.*;
import cn.hivemedia.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 杨浩
 * @create 2018-12-30 15:03
 **/
@RestController
@RequestMapping("/api/personalCenter")
@Api(tags = "个人中心")
public class ApiPersonalCenterController {

    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private UserAddressService userAddressService;

    @Autowired
    private UserService userService;

    @Autowired
    private SaleOrderService saleOrderService;

    @Autowired
    private UserAccountDatailLogService userAccountDatailLogService;

    @ApiOperation("修改个人信息")
    @GetMapping("/updateUserInfo")
    public BaseResult<UserEntity> updateUserInfo(
            @ApiParam("用户ID") @RequestParam(value = "userId", required = false) Long userId,
            @ApiParam("头像") @RequestParam(value = "ossId", required = false) Long avatarId,
            @ApiParam("昵称") @RequestParam(value = "nickName", required = false) String nickName,
            @ApiParam("个人签名") @RequestParam(value = "personalSign", required = false) String personalSign,
            @ApiParam("微信openId") @RequestParam(value = "wxOpenId", required = false) String wxOpenId,
            @ApiParam("qq_openId") @RequestParam(value = "qqOpenId", required = false) String qqOpenId,
            @ApiParam("微博openId") @RequestParam(value = "weiboOpenId", required = false) String weiboOpenId,
            @ApiParam("性别 0:未知 1:男 2:女") @RequestParam(value = "gender", required = false) Integer gender) {
        userService.updateUserInfo(userId, avatarId, nickName, personalSign, wxOpenId, qqOpenId, weiboOpenId, gender);
        return BaseResult.success();
    }

    @ApiOperation("账户信息查询")
    @GetMapping("/queryUserAccountInfo")
    public BaseResult<UserAccountEntity> queryUserAccountInfo(
            @ApiParam("用户ID") @RequestParam("userId") Long userId) {
        return BaseResult.success(userAccountService.queryDetail(userId));
    }
    @ApiOperation("用户信息查询")
    @GetMapping("/info")
    public BaseResult<UserEntity> info(
            @ApiParam("用户ID") @RequestParam("userId") Long userId) {
        return BaseResult.success(userService.info(userId));
    }

    @ApiOperation("申请提现")
    @PostMapping("/submitCashWithdrawal")
    public BaseResult<?> submitCashWithdrawal(@ApiParam("用户ID") @RequestParam("userId") Long userId,
                                              @ApiParam("账户ID") @RequestParam("accountId") Long accountId) {
        if (userAccountService.submitCashWithdrawal(userId, accountId)) {
            return BaseResult.success();
        }
        return BaseResult.failure("申请提现失败");
    }

    @ApiOperation("获取收货地址")
    @GetMapping("/getReceivingAddressById")
    public BaseResult<UserAddressEntity> getReceivingAddress(
            @ApiParam("收货地址ID") @RequestParam("addressId") Long addressId) {
        UserAddressEntity entity = userAddressService.getById(addressId);
        return BaseResult.success(entity);
    }

    @ApiOperation("新增及编辑收货地址")
    @PostMapping("/saveReceivingAddress")
    public BaseResult saveReceivingAddress(@ApiParam("用户ID") @RequestParam("userId") Long userId,
                                           @ApiParam("收货地址ID,编辑时传,新增不用传") @RequestParam(value = "addressId", required = false) Long addressId,
                                           @ApiParam("姓名") @RequestParam("realName") String realName,
                                           @ApiParam("手机号码") @RequestParam("mobileNo") String mobileNo,
                                           @ApiParam("省市区") @RequestParam("location") String location,
                                           @ApiParam("详细地址") @RequestParam("detailAddress") String detailAddress) {
        userAddressService.saveReceivingAddress(userId, addressId, realName, mobileNo, location, detailAddress);
        return BaseResult.success();
    }

    @ApiOperation("提交意见反馈接口")
    @PostMapping("/submitFeedback")
    public BaseResult<Integer> submitFeedback(@ApiParam("用户ID") @RequestParam("userId") Long userId,
                                              @ApiParam("反馈内容") @RequestParam(value = "content", required = false) String content) {
        feedbackService.submitFeedback(userId, content);
        return BaseResult.success();
    }

    @ApiOperation("删除个人地址")
    @GetMapping("/deleteReceivingAddress")
    public BaseResult deleteReceivingAddress(@ApiParam("收货地址ID") @RequestParam("addressId") Long addressId) {
        userAddressService.deleteReceivingAddress(addressId);
        return BaseResult.success();
    }

    @ApiOperation("设置默认地址")
    @PostMapping("/setDefaultAddress")
    public BaseResult setDefaultAddress(@ApiParam("用户ID") @RequestParam("userId") Long userId,
                                           @ApiParam("收货地址ID") @RequestParam("addressId") Long addressId) {
        userAddressService.setDefaultAddress(userId, addressId);
        return BaseResult.success();
    }

    @ApiOperation("查询我的收货地址")
    @GetMapping("/receivingAddressList")
    public BaseResult<List<UserAddressEntity>> receivingAddressList(
            @ApiParam("当前页码") @RequestParam("pageNo") String pageNo,
            @ApiParam("每页显示条数") @RequestParam("pageSize") String pageSize,
            @ApiParam("用户ID") @RequestParam("userId") Long userId) {
        Page<UserAddressEntity> page = userAddressService.receivingAddressList(pageNo, pageSize, userId);
        return BaseResult.success(page);
    }

    @ApiOperation("账号与安全")
    @PostMapping("/bindAccountWithPhone")
    public BaseResult bindAccountWithPhone(@ApiParam("用户ID") @RequestParam("userId") Long userId,
                                           @ApiParam("验证码") @RequestParam("msgCode") String  msgCode,
                                              @ApiParam("新手机号码") @RequestParam("mobileNo") String phone) {

        return BaseResult.success(userService.bindAccountWithPhone(userId, phone,msgCode));
    }

    @ApiOperation("查询账户明细")
    @GetMapping("/queryUserAccountDetail")
    public BaseResult<UserAccountDatailLogEntity> queryUserAccountDetail(
            @ApiParam("当前页码") @RequestParam("pageNo") String pageNo,
            @ApiParam("每页显示条数") @RequestParam("pageSize") String pageSize,
            @ApiParam("用户ID") @RequestParam("userId") Long userId,
            @ApiParam("账户ID") @RequestParam("accountId") Long accountId) {

        return BaseResult.success(userAccountDatailLogService.queryUserAccountDetail(pageNo, pageSize, accountId));
    }

    @ApiOperation("绑定支付宝")
    @PostMapping("/bindAlipay")
    public BaseResult bindAlipay(@ApiParam("用户ID") @RequestParam("userId") Long userId,
                                 @ApiParam("手机号码") @RequestParam("mobileNo") String  mobileNo,
                                 @ApiParam("用户姓名") @RequestParam("name") String  name,
                                 @ApiParam("验证码") @RequestParam("msgCode") String  msgCode,
                                 @ApiParam("支付宝账户code") @RequestParam("authCode") String authCode) {

        return BaseResult.success(userService.bindAlipay(userId,mobileNo,name,
                msgCode, authCode));
    }
}
