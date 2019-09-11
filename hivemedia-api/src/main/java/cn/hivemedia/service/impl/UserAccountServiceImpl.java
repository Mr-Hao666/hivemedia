package cn.hivemedia.service.impl;

import cn.hivemedia.dao.UserAccountDao;
import cn.hivemedia.dao.UserAccountDatailLogDao;
import cn.hivemedia.dao.UserDao;
import cn.hivemedia.entity.UserAccountDatailLogEntity;
import cn.hivemedia.entity.UserAccountEntity;
import cn.hivemedia.entity.UserEntity;
import cn.hivemedia.service.UserAccountService;
import cn.hivemedia.utils.Query;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.common.enums.UserAccountDetailLogChangeType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Map;

@Service("userAccountService")
public class UserAccountServiceImpl extends ServiceImpl<UserAccountDao, UserAccountEntity>
        implements UserAccountService {
    @Autowired
    private UserAccountDatailLogDao userAccountDatailLogDao;
    @Autowired
    private UserDao userDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<UserAccountEntity> page = this.selectPage(new Query<UserAccountEntity>(params).getPage(),
                new EntityWrapper<UserAccountEntity>());

        return new PageUtils(page);
    }

    /*
     * (non-Javadoc)
     *
     * @see UserAccountService#queryDetail(java.lang.Long)
     */
    @Override
    public UserAccountEntity queryDetail(Long userId) {
        UserAccountEntity userAccountEntity =
                this.selectOne(new EntityWrapper<UserAccountEntity>().eq("user_id", userId));
        UserEntity user  = userDao.selectById(userId);
        userAccountEntity.setHasBind(StringUtils.isNotBlank(user.getAlipayAccount()) ? 1 : 0);
        return userAccountEntity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean submitCashWithdrawal(Long userId, Long accountId) {
        UserAccountEntity userAccountEntity = new UserAccountEntity();
        userAccountEntity.setId(accountId);
        userAccountEntity.setUserId(userId);
        userAccountEntity = this.selectOne(new EntityWrapper<UserAccountEntity>()
                .eq("user_id", userId)
                .and().eq("id", accountId)
                .and().eq("state",0));
        //提取账户所有余额，新增一条提现详情记录
        if (userAccountEntity != null && BigDecimal.ZERO.compareTo(userAccountEntity.getRemainingMoney()) < 0) {
            BigDecimal remainingMoney = userAccountEntity.getRemainingMoney();
            UserAccountDatailLogEntity userAccountDatailLogEntity = new UserAccountDatailLogEntity();
            userAccountDatailLogEntity.setAccountId(userAccountEntity.getId());
            userAccountDatailLogEntity.setBeforeChangeMoney(remainingMoney);
            userAccountDatailLogEntity.setChangeMoney(remainingMoney);
            userAccountDatailLogEntity.setChangeType(UserAccountDetailLogChangeType.CASH_WITHDRAWAL.getCode());
            userAccountDatailLogDao.insert(userAccountDatailLogEntity);
            userAccountEntity.setRemainingMoney(remainingMoney.subtract(remainingMoney));
            return this.updateById(userAccountEntity);
        }
        return false;
    }

}
