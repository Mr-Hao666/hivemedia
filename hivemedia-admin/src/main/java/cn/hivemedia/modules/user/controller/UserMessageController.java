package cn.hivemedia.modules.user.controller;

import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.R;
import cn.hivemedia.common.validator.ValidatorUtils;
import cn.hivemedia.modules.user.entity.UserMessageEntity;
import cn.hivemedia.modules.user.service.UserMessageService;
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
 * 用户消息信息表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
@RestController
@RequestMapping("user/usermessage")
public class UserMessageController {
    @Autowired
    private UserMessageService userMessageService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("user:usermessage:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = userMessageService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("user:usermessage:info")
    public R info(@PathVariable("id") Long id) {
        UserMessageEntity userMessage = userMessageService.selectById(id);

        return R.ok().put("userMessage", userMessage);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("user:usermessage:save")
    public R save(@RequestBody UserMessageEntity userMessage) {
        userMessageService.insert(userMessage);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("user:usermessage:update")
    public R update(@RequestBody UserMessageEntity userMessage) {
        ValidatorUtils.validateEntity(userMessage);
        userMessageService.updateAllColumnById(userMessage);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("user:usermessage:delete")
    public R delete(@RequestBody Long[] ids) {
        userMessageService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }
}
