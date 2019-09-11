package cn.hivemedia.service;

import java.util.Map;

import cn.hivemedia.entity.UserAddressEntity;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import cn.hivemedia.common.utils.PageUtils;

/**
 * 用户地址信息表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:26:16
 */
public interface UserAddressService extends IService<UserAddressEntity> {

	PageUtils queryPage(Map<String, Object> params);

	/**
	 * 删除个人地址
	 *
	 * @param addressId
	 * @return
	 */
	boolean deleteReceivingAddress(Long addressId);

	/**
	 * 新增/修改个人收货地址
	 *
	 * @param userId
	 * @param addressId
	 * @param realName
	 * @param mobileNo
	 * @param detailAddress
	 * @return
	 */
	Integer saveReceivingAddress(Long userId, Long addressId, String realName, String mobileNo, String location,
			String detailAddress);

	/**
	 * 设置默认地址
	 *
	 * @param userId
	 * @return
	 */
	Integer setDefaultAddress(Long userId, Long addressId);

	/**
	 * 获取收货地址
	 *
	 * @param addressId
	 * @return
	 */
	UserAddressEntity getById(Long addressId);

	/**
	 * 获取个人收货地址列表
	 *
	 * @param pageSize
	 * @param pageNo
	 *
	 * @param userId
	 * @return
	 */
	Page<UserAddressEntity> receivingAddressList(String pageNo, String pageSize, Long userId);
}
