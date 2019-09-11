package cn.hivemedia.modules.user.controller;

import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.R;
import cn.hivemedia.common.validator.ValidatorUtils;
import cn.hivemedia.modules.user.entity.UserGoodsEntity;
import cn.hivemedia.modules.user.service.UserGoodsService;
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
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
@RestController
@RequestMapping("user/usergoods")
public class UserGoodsController {
    @Autowired
    private UserGoodsService userGoodsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("user:usergoods:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = userGoodsService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("user:usergoods:info")
    public R info(@PathVariable("id") Integer id) {
        UserGoodsEntity userGoods = userGoodsService.selectById(id);

        return R.ok().put("userGoods", userGoods);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("user:usergoods:save")
    public R save(@RequestBody UserGoodsEntity userGoods) {
        userGoodsService.insert(userGoods);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("user:usergoods:update")
    public R update(@RequestBody UserGoodsEntity userGoods) {
        ValidatorUtils.validateEntity(userGoods);
        userGoodsService.updateAllColumnById(userGoods);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("user:usergoods:delete")
    public R delete(@RequestBody Integer[] ids) {
        userGoodsService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }
}
