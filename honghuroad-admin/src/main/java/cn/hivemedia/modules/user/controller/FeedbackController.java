package cn.honghuroad.modules.user.controller;

import cn.honghuroad.common.utils.PageUtils;
import cn.honghuroad.common.utils.R;
import cn.honghuroad.common.validator.ValidatorUtils;
import cn.honghuroad.modules.user.entity.FeedbackEntity;
import cn.honghuroad.modules.user.service.FeedbackService;
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
 * 意见反馈表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
@RestController
@RequestMapping("user/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("user:feedback:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = feedbackService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("user:feedback:info")
    public R info(@PathVariable("id") Long id) {
        FeedbackEntity feedback = feedbackService.selectById(id);

        return R.ok().put("feedback", feedback);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("user:feedback:save")
    public R save(@RequestBody FeedbackEntity feedback) {
        feedbackService.insert(feedback);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("user:feedback:update")
    public R update(@RequestBody FeedbackEntity feedback) {
        ValidatorUtils.validateEntity(feedback);
        feedbackService.updateAllColumnById(feedback);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("user:feedback:delete")
    public R delete(@RequestBody Long[] ids) {
        feedbackService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }
}
