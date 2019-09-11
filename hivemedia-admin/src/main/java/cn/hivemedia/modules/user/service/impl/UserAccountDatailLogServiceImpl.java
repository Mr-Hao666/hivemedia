package cn.hivemedia.modules.user.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.utils.Query;
import cn.hivemedia.common.utils.R;
import cn.hivemedia.modules.user.dao.UserAccountDatailLogDao;
import cn.hivemedia.modules.user.entity.UserAccountDatailLogEntity;
import cn.hivemedia.modules.user.entity.UserAccountEntity;
import cn.hivemedia.modules.user.entity.UserEntity;
import cn.hivemedia.modules.user.service.UserAccountDatailLogService;
import java.util.Map;

import cn.hivemedia.modules.user.service.UserAccountService;
import cn.hivemedia.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userAccountDatailLogService")
public class UserAccountDatailLogServiceImpl extends ServiceImpl<UserAccountDatailLogDao, UserAccountDatailLogEntity>
        implements UserAccountDatailLogService {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private UserService userService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<UserAccountDatailLogEntity> page = this.selectPage(
                new Query<UserAccountDatailLogEntity>(params).getPage(),
                new EntityWrapper<UserAccountDatailLogEntity>()
                        .eq("change_type",5)

        );
        for (UserAccountDatailLogEntity record : page.getRecords()) {
            UserAccountEntity userAccountEntity =
                    userAccountService.selectById(record.getAccountId());
            UserEntity userEntity = userService.selectById(userAccountEntity.getUserId());
            record.setUserAccount(userEntity.getAlipayAccount());
            record.setNickname(userEntity.getNickname());
            record.setUserName(userEntity.getAlipayRealName());
        }
        return new PageUtils(page);
    }

    @Override
    public R changeCashStatus(Integer id) {
        UserAccountDatailLogEntity userAccountDatailLogEntity = this.selectById(id);
        if(userAccountDatailLogEntity.getCashStatus() == 1){
            return R.error("已操作过提现了");
        }
        userAccountDatailLogEntity.setCashStatus(1);
        this.updateById(userAccountDatailLogEntity);
        return R.ok();
    }

}
