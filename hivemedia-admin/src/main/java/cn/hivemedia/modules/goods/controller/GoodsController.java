package cn.hivemedia.modules.goods.controller;

import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.Query;
import cn.hivemedia.common.utils.R;
import cn.hivemedia.common.validator.ValidatorUtils;
import cn.hivemedia.modules.goods.entity.GoodsEntity;
import cn.hivemedia.modules.goods.service.GoodsRecBannerService;
import cn.hivemedia.modules.goods.service.GoodsService;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cn.hivemedia.modules.goods.vo.GoodsVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 商品表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
@RestController
@RequestMapping("goods/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsRecBannerService goodsRecBannerService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("goods:goods:list")
    public R list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List list = goodsService.queryGoodsVoList(query);
        int total = goodsService.queryTotal(query);
        PageUtils page = new PageUtils(list, total, query.getLimit(), query.getCurrPage());
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("goods:goods:info")
    public R info(@PathVariable("id") Long id) {
        GoodsVo goods = goodsService.queryGoodsVo(id);

        return R.ok().put("goods", goods);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("goods:goods:save")
    public R save(@RequestBody GoodsEntity goods) {
        goodsService.insertGoods(goods);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("goods:goods:update")
    public R update(@RequestBody GoodsEntity goods) {
        ValidatorUtils.validateEntity(goods);
        goodsService.updateGoods(goods);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("goods:goods:delete")
    public R delete(@RequestBody Long[] ids) {
        goodsService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 推荐
     */
    @PostMapping("/recommend/{goodsId}")
    @RequiresPermissions("goods:goods:recommend")
    public R recommend(@PathVariable("goodsId") Long goodsId) {
        if(goodsId == null) {
            return R.error("商品Id不能为空!");
        }
        return goodsRecBannerService.recommend(goodsId);
    }
    @GetMapping("/select")
    public R selectList(@RequestParam Map<String, Object> params) {
        params.put("offset", 0);
        params.put("limit", 10);
        List list = goodsService.queryGoodsVoList(params);
        return R.ok().put("goodsList", list);
    }
}
