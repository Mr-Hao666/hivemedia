package cn.honghuroad.modules.user.controller;

import cn.honghuroad.common.utils.PageUtils;
import cn.honghuroad.common.utils.R;
import cn.honghuroad.common.validator.ValidatorUtils;
import cn.honghuroad.modules.user.entity.UserCouponEntity;
import cn.honghuroad.modules.user.service.UserCouponService;
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
 * 用户—优惠券关联表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
@RestController
@RequestMapping("user/usercoupon")
public class UserCouponController {
    @Autowired
    private UserCouponService userCouponService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("user:usercoupon:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = userCouponService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("user:usercoupon:info")
    public R info(@PathVariable("id") Long id) {
        UserCouponEntity userCoupon = userCouponService.selectById(id);

        return R.ok().put("userCoupon", userCoupon);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("user:usercoupon:save")
    public R save(@RequestBody UserCouponEntity userCoupon) {
        userCouponService.insert(userCoupon);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("user:usercoupon:update")
    public R update(@RequestBody UserCouponEntity userCoupon) {
        ValidatorUtils.validateEntity(userCoupon);
        userCouponService.updateAllColumnById(userCoupon);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("user:usercoupon:delete")
    public R delete(@RequestBody Long[] ids) {
        userCouponService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }
}
