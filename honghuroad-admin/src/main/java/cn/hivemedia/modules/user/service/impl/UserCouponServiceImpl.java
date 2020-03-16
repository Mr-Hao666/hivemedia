package cn.honghuroad.modules.user.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.honghuroad.common.utils.PageUtils;
import cn.honghuroad.common.utils.Query;
import cn.honghuroad.modules.user.dao.UserCouponDao;
import cn.honghuroad.modules.user.entity.UserCouponEntity;
import cn.honghuroad.modules.user.service.UserCouponService;
import java.util.Map;
import org.springframework.stereotype.Service;

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
}
