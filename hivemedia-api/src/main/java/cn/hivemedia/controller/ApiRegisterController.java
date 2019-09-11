package cn.hivemedia.controller;

import cn.hivemedia.entity.UserEntity;
import cn.hivemedia.form.RegisterForm;
import cn.hivemedia.service.UserService;
import cn.hivemedia.common.utils.R;
import cn.hivemedia.common.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Date;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZengXiong
 * @Description 注册接口
 * @Date 2018/11/23 11:02
 */
@RestController
@RequestMapping("/api")
@Api(tags = "注册接口")
public class ApiRegisterController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ApiOperation("注册")
    public R register(@RequestBody RegisterForm form) {
        //表单校验


        UserEntity user = new UserEntity();

        user.setCreateTime(new Date());
        userService.insert(user);

        return R.ok();
    }
}
