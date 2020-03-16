package cn.honghuroad.modules.user.service;

import com.baomidou.mybatisplus.service.IService;
import cn.honghuroad.common.utils.PageUtils;
import cn.honghuroad.common.utils.R;
import cn.honghuroad.modules.user.entity.UserAccountDatailLogEntity;
import java.util.Map;

/**
 * 账户明细日志表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
public interface UserAccountDatailLogService extends IService<UserAccountDatailLogEntity> {

    PageUtils queryPage(Map<String, Object> params);

    R changeCashStatus(Integer id);
}

