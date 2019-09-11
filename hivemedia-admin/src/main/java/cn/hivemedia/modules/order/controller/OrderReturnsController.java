package cn.hivemedia.modules.order.controller;

import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.R;
import cn.hivemedia.common.validator.ValidatorUtils;
import cn.hivemedia.modules.order.entity.OrderReturnsEntity;
import cn.hivemedia.modules.order.service.OrderReturnsService;
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
 * 订单退货表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
@RestController
@RequestMapping("order/orderreturns")
public class OrderReturnsController {
    @Autowired
    private OrderReturnsService orderReturnsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("order:orderreturns:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = orderReturnsService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("order:orderreturns:info")
    public R info(@PathVariable("id") Long id) {
        OrderReturnsEntity orderReturns = orderReturnsService.selectById(id);

        return R.ok().put("orderReturns", orderReturns);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("order:orderreturns:save")
    public R save(@RequestBody OrderReturnsEntity orderReturns) {
        orderReturnsService.insert(orderReturns);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("order:orderreturns:update")
    public R update(@RequestBody OrderReturnsEntity orderReturns) {
        ValidatorUtils.validateEntity(orderReturns);
        orderReturnsService.updateAllColumnById(orderReturns);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("order:orderreturns:delete")
    public R delete(@RequestBody Long[] ids) {
        orderReturnsService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }
}
