package cn.hivemedia.modules.goods.controller;

import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.Query;
import cn.hivemedia.common.utils.R;
import cn.hivemedia.common.validator.ValidatorUtils;
import cn.hivemedia.modules.goods.entity.GoodsSizeEntity;
import cn.hivemedia.modules.goods.service.GoodsSizeService;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cn.hivemedia.modules.goods.vo.GoodsSizeVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品尺码关联表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
@RestController
@RequestMapping("goods/goodssize")
public class GoodsSizeController {
    @Autowired
    private GoodsSizeService goodsSizeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("goods:goodssize:list")
    public R list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List list = goodsSizeService.queryGoodsSizeVoList(query);
        int total = goodsSizeService.queryTotal(query);
        PageUtils page = new PageUtils(list, total, query.getLimit(), query.getCurrPage());
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("goods:goodssize:info")
    public R info(@PathVariable("id") Long id) {
        GoodsSizeVo goodsSize = goodsSizeService.queryGoodsSizeVo(id);
        return R.ok().put("goodsSize", goodsSize);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("goods:goodssize:save")
    public R save(@RequestBody GoodsSizeEntity goodsSize) {
        goodsSizeService.insert(goodsSize);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("goods:goodssize:update")
    public R update(@RequestBody GoodsSizeEntity goodsSize) {
        ValidatorUtils.validateEntity(goodsSize);
        goodsSizeService.updateById(goodsSize);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("goods:goodssize:delete")
    public R delete(@RequestBody Long[] ids) {
        goodsSizeService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }
}
