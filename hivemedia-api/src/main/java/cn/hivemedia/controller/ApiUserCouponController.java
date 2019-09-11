package cn.hivemedia.controller;

import cn.hivemedia.entity.UserCouponEntity;
import cn.hivemedia.result.BaseResult;
import cn.hivemedia.service.UserCouponService;
import com.baomidou.mybatisplus.plugins.Page;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.R;
import cn.hivemedia.common.validator.ValidatorUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户—优惠券关联表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
@RestController
@RequestMapping("api/usercoupon")
public class ApiUserCouponController {
    @Autowired
    private UserCouponService userCouponService;

    /**
     * 列表
     */
    @ApiOperation("查询我的优惠券")
    @GetMapping("/list")
    public BaseResult<List<UserCouponEntity>> userCouponList(
            @ApiParam("当前页码") @RequestParam("pageNo") String pageNo,
            @ApiParam("状态：0，待发放；1，未使用 ；2，已使用；3，已过期；9，已作废；") @RequestParam("status") Integer status,
            @ApiParam("每页显示条数") @RequestParam("pageSize") String pageSize,
            @ApiParam("用户ID") @RequestParam("userId") Long userId) {
        Page page = userCouponService.userCouponList(pageNo, pageSize, userId, status);
        return BaseResult.success(page);
    }
}
