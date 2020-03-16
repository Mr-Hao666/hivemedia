package cn.honghuroad.modules.user.dao;

import cn.honghuroad.modules.user.entity.UserEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

/**
 * 用户信息表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
public interface UserDao extends BaseMapper<UserEntity> {

    /**
     * 分页获取用户列表信息
     *
     * @param params 分页参数信息
     * @return 用户列表信息
     */
    List<UserEntity> selectUserListPage(@Param("params") Map<String, Object> params);

    /**
     * 数据总数
     * @param params 分页参数信息
     * @return  数据总数
     */
    Integer countAll(@Param("params") Map<String, Object> params);
}
