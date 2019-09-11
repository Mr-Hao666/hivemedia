package cn.hivemedia.modules.user.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.Query;
import cn.hivemedia.modules.user.dao.UserAddressDao;
import cn.hivemedia.modules.user.entity.UserAddressEntity;
import cn.hivemedia.modules.user.service.UserAddressService;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service("userAddressService")
public class UserAddressServiceImpl extends ServiceImpl<UserAddressDao, UserAddressEntity> implements UserAddressService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<UserAddressEntity> page = this.selectPage(
                new Query<UserAddressEntity>(params).getPage(),
                new EntityWrapper<UserAddressEntity>()
        );

        return new PageUtils(page);
    }
}
