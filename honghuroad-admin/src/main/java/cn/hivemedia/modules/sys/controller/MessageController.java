package cn.honghuroad.modules.sys.controller;

import cn.honghuroad.common.utils.PageUtils;
import cn.honghuroad.common.utils.R;
import cn.honghuroad.common.validator.ValidatorUtils;
import cn.honghuroad.modules.sys.entity.MessageEntity;
import cn.honghuroad.modules.sys.service.MessageService;
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
 * 消息推送表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
@RestController
@RequestMapping("sys/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:message:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = messageService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:message:info")
    public R info(@PathVariable("id") Long id) {
        MessageEntity message = messageService.selectById(id);

        return R.ok().put("message", message);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:message:save")
    public R save(@RequestBody MessageEntity message) {
        messageService.insert(message);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:message:update")
    public R update(@RequestBody MessageEntity message) {
        ValidatorUtils.validateEntity(message);
        messageService.updateAllColumnById(message);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:message:delete")
    public R delete(@RequestBody Long[] ids) {
        messageService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }
}
