package cn.hivemedia.modules.goods.controller;

import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.Query;
import cn.hivemedia.common.utils.R;
import cn.hivemedia.common.validator.ValidatorUtils;
import cn.hivemedia.modules.goods.entity.GoodsRecBannerEntity;
import cn.hivemedia.modules.goods.service.GoodsRecBannerService;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cn.hivemedia.modules.goods.vo.GoodsRecBannerVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品推荐banner表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
@RestController
@RequestMapping("goods/goodsrecbanner")
public class GoodsRecBannerController {
    @Autowired
    private GoodsRecBannerService goodsRecBannerService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("goods:goodsrecbanner:list")
    public R list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List list = goodsRecBannerService.queryGoodsRecBannerVoList(query);
        int total = goodsRecBannerService.queryTotal(query);
        PageUtils page = new PageUtils(list, total, query.getLimit(), query.getCurrPage());
        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("goods:goodsrecbanner:info")
    public R info(@PathVariable("id") Integer id) {
        GoodsRecBannerVo goodsRecBanner = goodsRecBannerService.queryGoodsRecBannerVo(id);

        return R.ok().put("goodsRecBanner", goodsRecBanner);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("goods:goodsrecbanner:save")
    public R save(@RequestBody GoodsRecBannerEntity goodsRecBanner) {
        goodsRecBannerService.insert(goodsRecBanner);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("goods:goodsrecbanner:update")
    public R update(@RequestBody GoodsRecBannerEntity goodsRecBanner) {
        ValidatorUtils.validateEntity(goodsRecBanner);
        goodsRecBannerService.updateById(goodsRecBanner);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("goods:goodsrecbanner:delete")
    public R delete(@RequestBody Integer[] ids) {
        goodsRecBannerService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }
}
