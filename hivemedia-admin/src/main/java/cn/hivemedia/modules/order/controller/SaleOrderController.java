package cn.hivemedia.modules.order.controller;

import java.util.Arrays;
import java.util.Map;

import cn.hivemedia.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.hivemedia.modules.order.entity.SaleOrderEntity;
import cn.hivemedia.modules.order.service.SaleOrderService;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.R;



/**
 * 出售订单信息表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-02-21 13:45:27
 */
@RestController
@RequestMapping("order/saleorder")
public class SaleOrderController {
    @Autowired
    private SaleOrderService saleOrderService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("order:saleorder:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = saleOrderService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("order:saleorder:info")
    public R info(@PathVariable("id") Long id){
        SaleOrderEntity saleOrder = saleOrderService.selectById(id);

        return R.ok().put("saleOrder", saleOrder);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("order:saleorder:save")
    public R save(@RequestBody SaleOrderEntity saleOrder){
        saleOrderService.insert(saleOrder);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("order:saleorder:update")
    public R update(@RequestBody SaleOrderEntity saleOrder){
        ValidatorUtils.validateEntity(saleOrder);
        saleOrderService.updateAllColumnById(saleOrder);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("order:saleorder:delete")
    public R delete(@RequestBody Long[] ids){
        saleOrderService.deleteIds(Arrays.asList(ids));

        return R.ok();
    }

}
