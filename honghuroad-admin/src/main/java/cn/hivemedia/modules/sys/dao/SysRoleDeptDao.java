package cn.honghuroad.modules.sys.dao;

import cn.honghuroad.modules.sys.entity.SysRoleDeptEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * 角色与部门对应关系
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2017年6月21日 23:33:46
 */
public interface SysRoleDeptDao extends BaseMapper<SysRoleDeptEntity> {

    /**
     * 根据角色ID，获取部门ID列表
     */
    List<Long> queryDeptIdList(Long[] roleIds);

    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);
}
