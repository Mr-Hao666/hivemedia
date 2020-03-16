package cn.honghuroad.modules.sys.controller;

import cn.honghuroad.common.utils.PageUtils;
import cn.honghuroad.common.utils.R;
import cn.honghuroad.common.validator.ValidatorUtils;
import cn.honghuroad.modules.sys.entity.DataDictionaryEntity;
import cn.honghuroad.modules.sys.service.DataDictionaryService;
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
 * 数据字典表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
@RestController
@RequestMapping("sys/datadictionary")
public class DataDictionaryController {
    @Autowired
    private DataDictionaryService dataDictionaryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:datadictionary:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = dataDictionaryService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:datadictionary:info")
    public R info(@PathVariable("id") Integer id) {
        DataDictionaryEntity dataDictionary = dataDictionaryService.selectById(id);

        return R.ok().put("dataDictionary", dataDictionary);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:datadictionary:save")
    public R save(@RequestBody DataDictionaryEntity dataDictionary) {
        dataDictionaryService.insert(dataDictionary);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:datadictionary:update")
    public R update(@RequestBody DataDictionaryEntity dataDictionary) {
        ValidatorUtils.validateEntity(dataDictionary);
        dataDictionaryService.updateAllColumnById(dataDictionary);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:datadictionary:delete")
    public R delete(@RequestBody Integer[] ids) {
        dataDictionaryService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }
}
