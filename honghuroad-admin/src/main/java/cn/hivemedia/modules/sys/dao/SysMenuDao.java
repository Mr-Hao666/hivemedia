package cn.honghuroad.modules.sys.dao;

import cn.honghuroad.modules.sys.entity.SysMenuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * 菜单管理
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2016年9月18日 上午9:33:01
 */
public interface SysMenuDao extends BaseMapper<SysMenuEntity> {

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId 父菜单ID
     */
    List<SysMenuEntity> queryListParentId(Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenuEntity> queryNotButtonList();
}
