package cn.hivemedia.modules.goods.controller;

import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.Query;
import cn.hivemedia.common.utils.R;
import cn.hivemedia.common.validator.ValidatorUtils;
import cn.hivemedia.modules.goods.entity.SizeEntity;
import cn.hivemedia.modules.goods.service.SizeService;

import java.util.List;
import java.util.Map;

import cn.hivemedia.modules.goods.vo.SizeVo;
import cn.hivemedia.modules.sys.service.SysDictService;
import io.swagger.models.auth.In;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 尺码信息表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
@RestController
@RequestMapping("goods/size")
public class SizeController {
    @Autowired
    private SizeService sizeService;
    @Autowired
    private SysDictService sysDictService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("goods:size:list")
    public R list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List list = sizeService.querySizeVoList(query);
        int total = sizeService.queryTotal(query);
        PageUtils page = new PageUtils(list, total, query.getLimit(), query.getCurrPage());

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("goods:size:info")
    public R info(@PathVariable("id") Integer id) {
        SizeVo size = sizeService.querySizeVo(id);

        return R.ok().put("size", size);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("goods:size:save")
    public R save(@RequestBody SizeEntity size) {
        sizeService.insert(size);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("goods:size:update")
    public R update(@RequestBody SizeEntity size) {
        ValidatorUtils.validateEntity(size);
        sizeService.updateById(size);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete/{id}")
    @RequiresPermissions("goods:size:delete")
    public R delete(@PathVariable("id") Integer id) {
        if(id < 0){
            return R.error("商品Tab请去字典管理处删除!");
        }
        sizeService.deleteById(id);
        return R.ok();
    }

    @GetMapping("/select")
    public R selectBy(@RequestParam Map<String, Object> params) {
        List list = sizeService.querySizeVoList(params);
        return R.ok().put("list", list);
    }
    @RequestMapping("/queryAll")
    public List<SizeVo> queryAll(@RequestParam Map<String, Object> params) {
        List<SizeVo> list = sizeService.queryAll2Tree(params);
        return list;
    }

}
