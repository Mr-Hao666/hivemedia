package cn.hivemedia.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.Query;
import cn.hivemedia.modules.sys.dao.CouponDao;
import cn.hivemedia.modules.sys.entity.CouponEntity;
import cn.hivemedia.modules.sys.service.CouponService;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service("couponService")
public class CouponServiceImpl extends ServiceImpl<CouponDao, CouponEntity> implements CouponService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CouponEntity> page = this.selectPage(
                new Query<CouponEntity>(params).getPage(),
                new EntityWrapper<CouponEntity>()
        );

        return new PageUtils(page);
    }
}
