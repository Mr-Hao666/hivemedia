package cn.hivemedia.service;

import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.entity.UserAccountEntity;

/**
 * 用户账户信息
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:27:17
 */
public interface UserAccountService extends IService<UserAccountEntity> {

	PageUtils queryPage(Map<String, Object> params);

	/**
	 * 查询账户详情
	 * 
	 * @param userId
	 * @return
	 */
	UserAccountEntity queryDetail(Long userId);

	/**
	 * 申请提现
	 * 
	 * @param userId
	 * @param accountId
	 * @return
	 */
	boolean submitCashWithdrawal(Long userId, Long accountId);
}
