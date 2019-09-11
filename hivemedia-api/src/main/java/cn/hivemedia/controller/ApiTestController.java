package cn.hivemedia.controller;

import cn.hivemedia.entity.UserEntity;
import cn.hivemedia.result.BaseResult;
import cn.hivemedia.annotation.Encrypt;
import cn.hivemedia.annotation.Login;
import cn.hivemedia.annotation.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author ZengXiong
 * @Description 测试接口
 * @Date 2018/11/23 11:23
 */
@RestController
@RequestMapping("/api")
@Api(tags = "测试接口")
public class ApiTestController {

    @Login
    @GetMapping("/userInfo")
    @ApiOperation(value = "获取用户信息", response = UserEntity.class)
    public BaseResult<UserEntity> userInfo(@ApiIgnore @LoginUser UserEntity user) {
        return BaseResult.success(user);
    }

    @Login
    @GetMapping("/userId")
    @ApiOperation("获取用户ID")
    public BaseResult<Integer> userInfo(@ApiIgnore @RequestParam("userId") Integer userId) {
        return BaseResult.success(userId);
    }

    @Encrypt
    @GetMapping("/notToken")
    @ApiOperation("忽略Token验证测试")
    public BaseResult notToken() {
        return BaseResult.success("无需token也能访问。。。");
    }
}
