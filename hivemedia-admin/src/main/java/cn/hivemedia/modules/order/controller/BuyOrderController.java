package cn.hivemedia.modules.order.controller;

import java.util.Map;

import cn.hivemedia.common.annotation.SysLog;
import cn.hivemedia.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.hivemedia.modules.order.entity.BuyOrderEntity;
import cn.hivemedia.modules.order.service.BuyOrderService;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.R;



/**
 * 购买订单信息表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-02-21 13:45:27
 */
@RestController
@RequestMapping("order/buyorder")
public class BuyOrderController {
    @Autowired
    private BuyOrderService buyOrderService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("order:buyorder:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = buyOrderService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("order:buyorder:info")
    public R info(@PathVariable("id") Long id){
        BuyOrderEntity buyOrder = buyOrderService.selectById(id);

        return R.ok().put("buyOrder", buyOrder);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("order:buyorder:save")
    public R save(@RequestBody BuyOrderEntity buyOrder){
        buyOrderService.insert(buyOrder);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("order:buyorder:update")
    public R update(@RequestBody BuyOrderEntity buyOrder){
        ValidatorUtils.validateEntity(buyOrder);
        buyOrderService.updateAllColumnById(buyOrder);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("order:buyorder:delete")
    public R delete(@RequestBody Long[] ids){
        buyOrderService.deleteIds(ids);

        return R.ok();
    }

    /**
     * 平台发货
     */
    @SysLog("平台发货")
    @RequestMapping("/shipped")
    @RequiresPermissions("order:buyorder:shipped")
    public R pause(@RequestBody Long[] ids) {
        buyOrderService.shipped(ids);

        return R.ok();
    }/**
     * 平台验货未通过
     */
    @SysLog("平台验货未通过")
    @RequestMapping("/unpass")
    @RequiresPermissions("order:buyorder:unpass")
    public R unpass(@RequestBody Long[] ids) {
        buyOrderService.unpass(ids);

        return R.ok();
    }/**
     * 已签收
     */
    @SysLog("已签收")
    @RequestMapping("/inInspection")
    @RequiresPermissions("order:buyorder:inInspection")
    public R inInspection(@RequestBody Long[] ids) {
        buyOrderService.inInspection(ids);

        return R.ok();
    }

}
