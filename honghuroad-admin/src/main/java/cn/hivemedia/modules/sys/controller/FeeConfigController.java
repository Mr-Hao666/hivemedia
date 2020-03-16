package cn.honghuroad.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import cn.honghuroad.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.honghuroad.modules.sys.entity.FeeConfigEntity;
import cn.honghuroad.modules.sys.service.FeeConfigService;
import cn.honghuroad.common.utils.PageUtils;
import cn.honghuroad.common.utils.R;



/**
 * 费用配置表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-02-22 23:07:24
 */
@RestController
@RequestMapping("sys/feeconfig")
public class FeeConfigController {
    @Autowired
    private FeeConfigService feeConfigService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:feeconfig:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = feeConfigService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:feeconfig:info")
    public R info(@PathVariable("id") Integer id){
        FeeConfigEntity feeConfig = feeConfigService.selectById(id);

        return R.ok().put("feeConfig", feeConfig);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:feeconfig:save")
    public R save(@RequestBody FeeConfigEntity feeConfig){
        feeConfigService.insert(feeConfig);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:feeconfig:update")
    public R update(@RequestBody FeeConfigEntity feeConfig){
        ValidatorUtils.validateEntity(feeConfig);
        feeConfigService.updateAllColumnById(feeConfig);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:feeconfig:delete")
    public R delete(@RequestBody Integer[] ids){
        feeConfigService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
