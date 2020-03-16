package cn.honghuroad.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import cn.honghuroad.common.utils.PageUtils;
import cn.honghuroad.modules.sys.entity.CouponEntity;
import java.util.Map;

/**
 * 优惠券表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
public interface CouponService extends IService<CouponEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

