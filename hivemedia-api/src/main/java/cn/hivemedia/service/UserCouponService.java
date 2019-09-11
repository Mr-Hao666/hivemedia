package cn.hivemedia.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.entity.UserCouponEntity;

import java.util.Map;

/**
 * 用户—优惠券关联表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
public interface UserCouponService extends IService<UserCouponEntity> {

    PageUtils queryPage(Map<String, Object> params);

    Page<UserCouponEntity> userCouponList(String pageNo, String pageSize, Long userId, Integer status);
}

