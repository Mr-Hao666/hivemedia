package cn.honghuroad.modules.user.controller;

import cn.honghuroad.common.utils.PageUtils;
import cn.honghuroad.common.utils.R;
import cn.honghuroad.common.validator.ValidatorUtils;
import cn.honghuroad.modules.user.entity.UserAccountDatailLogEntity;
import cn.honghuroad.modules.user.service.UserAccountDatailLogService;
import java.util.Arrays;
import java.util.Map;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 账户明细日志表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
@RestController
@RequestMapping("user/useraccountdataillog")
public class UserAccountDatailLogController {
    @Autowired
    private UserAccountDatailLogService userAccountDatailLogService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("user:useraccountdataillog:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = userAccountDatailLogService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("user:useraccountdataillog:info")
    public R info(@PathVariable("id") Long id) {
        UserAccountDatailLogEntity userAccountDatailLog = userAccountDatailLogService.selectById(id);

        return R.ok().put("userAccountDatailLog", userAccountDatailLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("user:useraccountdataillog:save")
    public R save(@RequestBody UserAccountDatailLogEntity userAccountDatailLog) {
        userAccountDatailLogService.insert(userAccountDatailLog);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("user:useraccountdataillog:update")
    public R update(@RequestBody UserAccountDatailLogEntity userAccountDatailLog) {
        ValidatorUtils.validateEntity(userAccountDatailLog);
        userAccountDatailLogService.updateAllColumnById(userAccountDatailLog);//全部更新

        return R.ok();
    }
    /**
     * 推荐
     */
    @PostMapping("/cash/{useraccountdataillogId}")
    @RequiresPermissions("user:useraccountdataillog:cash")
    public R recommend(@PathVariable("useraccountdataillogId") Integer useraccountdataillogId) {
        if(useraccountdataillogId == null) {
            return R.error("请选择提现申请!");
        }
        return userAccountDatailLogService.changeCashStatus(useraccountdataillogId);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("user:useraccountdataillog:delete")
    public R delete(@RequestBody Long[] ids) {
        userAccountDatailLogService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }
}
