package cn.hivemedia.modules.user.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.Query;
import cn.hivemedia.modules.user.dao.UserAccountDao;
import cn.hivemedia.modules.user.entity.UserAccountEntity;
import cn.hivemedia.modules.user.service.UserAccountService;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service("userAccountService")
public class UserAccountServiceImpl extends ServiceImpl<UserAccountDao, UserAccountEntity> implements UserAccountService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<UserAccountEntity> page = this.selectPage(
                new Query<UserAccountEntity>(params).getPage(),
                new EntityWrapper<UserAccountEntity>()
        );
        return new PageUtils(page);
    }
}
