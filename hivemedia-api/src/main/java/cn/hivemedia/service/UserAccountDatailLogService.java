package cn.hivemedia.service;

import java.util.Map;

import cn.hivemedia.entity.UserAccountDatailLogEntity;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;

/**
 * 账户明细日志表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:26:16
 */
public interface UserAccountDatailLogService extends IService<UserAccountDatailLogEntity> {

	PageUtils queryPage(Map<String, Object> params);

	Page<UserAccountDatailLogEntity> queryUserAccountDetail(String pageNo, String pageSize, Long accountId);
}
