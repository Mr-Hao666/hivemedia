package cn.hivemedia.modules.user.controller;

import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.R;
import cn.hivemedia.common.validator.ValidatorUtils;
import cn.hivemedia.modules.user.entity.TokenEntity;
import cn.hivemedia.modules.user.service.TokenService;
import java.util.Arrays;
import java.util.Map;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户Token
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
@RestController
@RequestMapping("user/token")
public class TokenController {
    @Autowired
    private TokenService tokenService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("user:token:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = tokenService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{userId}")
    @RequiresPermissions("user:token:info")
    public R info(@PathVariable("userId") Long userId) {
        TokenEntity token = tokenService.selectById(userId);

        return R.ok().put("token", token);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("user:token:save")
    public R save(@RequestBody TokenEntity token) {
        tokenService.insert(token);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("user:token:update")
    public R update(@RequestBody TokenEntity token) {
        ValidatorUtils.validateEntity(token);
        tokenService.updateAllColumnById(token);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("user:token:delete")
    public R delete(@RequestBody Long[] userIds) {
        tokenService.deleteBatchIds(Arrays.asList(userIds));

        return R.ok();
    }
}
