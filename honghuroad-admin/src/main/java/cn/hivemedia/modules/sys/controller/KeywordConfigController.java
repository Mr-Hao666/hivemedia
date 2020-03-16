package cn.honghuroad.modules.sys.controller;

import cn.honghuroad.common.utils.PageUtils;
import cn.honghuroad.common.utils.R;
import cn.honghuroad.common.validator.ValidatorUtils;
import cn.honghuroad.modules.sys.entity.KeywordConfigEntity;
import cn.honghuroad.modules.sys.service.KeywordConfigService;
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
 * 关键字设置表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
@RestController
@RequestMapping("sys/keywordconfig")
public class KeywordConfigController {
    @Autowired
    private KeywordConfigService keywordConfigService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:keywordconfig:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = keywordConfigService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:keywordconfig:info")
    public R info(@PathVariable("id") Integer id) {
        KeywordConfigEntity keywordConfig = keywordConfigService.selectById(id);

        return R.ok().put("keywordConfig", keywordConfig);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:keywordconfig:save")
    public R save(@RequestBody KeywordConfigEntity keywordConfig) {
        keywordConfigService.insert(keywordConfig);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:keywordconfig:update")
    public R update(@RequestBody KeywordConfigEntity keywordConfig) {
        ValidatorUtils.validateEntity(keywordConfig);
        keywordConfigService.updateAllColumnById(keywordConfig);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:keywordconfig:delete")
    public R delete(@RequestBody Integer[] ids) {
        keywordConfigService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }
}
