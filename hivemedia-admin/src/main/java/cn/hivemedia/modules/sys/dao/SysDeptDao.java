package cn.hivemedia.modules.sys.dao;

import cn.hivemedia.modules.sys.entity.SysDeptEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * 部门管理
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2017-06-20 15:23:47
 */
public interface SysDeptDao extends BaseMapper<SysDeptEntity> {

    /**
     * 查询子部门ID列表
     *
     * @param parentId 上级部门ID
     */
    List<Long> queryDetpIdList(Long parentId);
}
