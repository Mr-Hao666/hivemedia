package cn.honghuroad.modules.user.service;

import cn.honghuroad.modules.user.entity.UserAccountEntity;
import com.baomidou.mybatisplus.service.IService;
import cn.honghuroad.common.utils.PageUtils;

import java.util.Map;

/**
 * 用户账户信息
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
public interface UserAccountService extends IService<UserAccountEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

