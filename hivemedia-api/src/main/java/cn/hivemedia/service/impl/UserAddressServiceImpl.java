package cn.hivemedia.service.impl;

import java.util.HashMap;
import java.util.Map;

import cn.hivemedia.dao.UserAddressDao;
import cn.hivemedia.entity.UserAddressEntity;
import cn.hivemedia.utils.Query;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.hivemedia.common.utils.PageUtils;
import cn.hivemedia.service.UserAddressService;

@Service("userAddressService")
public class UserAddressServiceImpl extends ServiceImpl<UserAddressDao, UserAddressEntity>
		implements UserAddressService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<UserAddressEntity> page = this.selectPage(new Query<UserAddressEntity>(params).getPage(),
				new EntityWrapper<UserAddressEntity>());

		return new PageUtils(page);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * UserAddressService#deleteReceivingAddress(java.lang.
	 * Long)
	 */
	@Override
	public boolean deleteReceivingAddress(Long addressId) {

		return baseMapper.deleteReceivingAddress(addressId);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * UserAddressService#saveReceivingAddress(java.lang.Long,
	 * java.lang.Long, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Integer saveReceivingAddress(Long userId, Long addressId, String realName, String mobileNo, String location,
			String detailAddress) {
		UserAddressEntity entity = new UserAddressEntity();
		entity.setId(addressId);
		entity.setUserId(userId);
		entity.setPhone(mobileNo);
		entity.setUserName(realName);
		entity.setLocation(location);
		entity.setDetailAddress(detailAddress);
		if (null != addressId && null != baseMapper.getById(addressId)) {
			return baseMapper.updateById(entity);
		} else {
			entity.setIsDefault(0);
			return baseMapper.insert(entity);
		}

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * UserAddressService#setDefaultAddress(java.lang.Long,
	 * java.lang.Long)
	 */
	@Override
	public Integer setDefaultAddress(Long userId, Long addressId) {
		UserAddressEntity entity = baseMapper.getDefaultAddressByUserId(userId);
		if (null != entity) {
			entity.setIsDefault(0);
			baseMapper.updateById(entity);
		}
		UserAddressEntity entityVo = new UserAddressEntity();
		entityVo.setId(addressId);
		entityVo.setIsDefault(1);
		return baseMapper.updateById(entityVo);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see UserAddressService#getById(java.lang.Long)
	 */
	@Override
	public UserAddressEntity getById(Long addressId) {

		return baseMapper.getById(addressId);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * UserAddressService#receivingAddressList(java.lang.Long)
	 */
	@Override
	public Page<UserAddressEntity> receivingAddressList(String pageNo, String pageSize, Long userId) {
		Map<String, Object> params = new HashMap<>();
		params.put("pageNo", pageNo);
		params.put("pageSize", pageSize);
		return this.selectPage(new Query<UserAddressEntity>(params).getPage(),
				new EntityWrapper<UserAddressEntity>().eq("user_id", userId));
	}
}
