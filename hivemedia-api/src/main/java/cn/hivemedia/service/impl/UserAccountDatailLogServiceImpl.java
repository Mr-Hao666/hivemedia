package cn.hivemedia.service.impl;

import java.util.HashMap;
import java.util.Map;

import cn.hivemedia.dao.UserAccountDatailLogDao;
import cn.hivemedia.entity.UserAccountDatailLogEntity;
import cn.hivemedia.utils.Query;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.service.UserAccountDatailLogService;

@Service("userAccountDatailLogService")
public class UserAccountDatailLogServiceImpl extends ServiceImpl<UserAccountDatailLogDao, UserAccountDatailLogEntity>
		implements UserAccountDatailLogService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<UserAccountDatailLogEntity> page = this.selectPage(new Query<UserAccountDatailLogEntity>(params).getPage(),
				new EntityWrapper<UserAccountDatailLogEntity>());

		return new PageUtils(page);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * UserAccountDatailLogService#queryUserAccountList(java.
	 * lang.String, java.lang.String, java.lang.Integer)
	 */
	@Override
	public Page<UserAccountDatailLogEntity> queryUserAccountDetail(String pageNo, String pageSize, Long accountId) {

		Map<String, Object> params = new HashMap<>();
		params.put("pageNo", pageNo);
		params.put("pageSize", pageSize);
		return this.selectPage(new Query<UserAccountDatailLogEntity>(params).getPage(),
				new EntityWrapper<UserAccountDatailLogEntity>().eq("account_id", accountId));
	}

}
