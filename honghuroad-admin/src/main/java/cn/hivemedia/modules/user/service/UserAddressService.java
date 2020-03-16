package cn.honghuroad.modules.user.service;

import com.baomidou.mybatisplus.service.IService;
import cn.honghuroad.common.utils.PageUtils;
import cn.honghuroad.modules.user.entity.UserAddressEntity;
import java.util.Map;

/**
 * 用户地址信息表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
public interface UserAddressService extends IService<UserAddressEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

