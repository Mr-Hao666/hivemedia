package cn.hivemedia.dao;

import cn.hivemedia.entity.UserAddressEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 用户地址信息表
 * 
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:26:16
 */
public interface UserAddressDao extends BaseMapper<UserAddressEntity> {
	boolean deleteReceivingAddress(Long addressId);

	UserAddressEntity getById(Long addressId);

	UserAddressEntity getDefaultAddressByUserId(Long userId);

}
