package cn.hivemedia.service.impl;

import cn.hivemedia.dao.UserCouponDao;
import cn.hivemedia.entity.UserCouponEntity;
import cn.hivemedia.service.UserCouponService;
import cn.hivemedia.utils.Query;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("userCouponService")
public class UserCouponServiceImpl extends ServiceImpl<UserCouponDao, UserCouponEntity> implements UserCouponService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<UserCouponEntity> page = this.selectPage(
                new Query<UserCouponEntity>(params).getPage(),
                new EntityWrapper<UserCouponEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Page<UserCouponEntity> userCouponList(String pageNo, String pageSize, Long userId, Integer status) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNo", pageNo);
        params.put("pageSize", pageSize);
        return this.selectPage(new Query<UserCouponEntity>(params).getPage(),
                new EntityWrapper<UserCouponEntity>()
                        .eq("user_id", userId)
                        .eq("status", status));
    }
}
