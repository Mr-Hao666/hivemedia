package cn.hivemedia.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 角色与部门对应关系
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2017年6月21日 23:28:13
 */
@Data
@TableName("sys_role_dept")
public class SysRoleDeptEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 部门ID
     */
    private Long deptId;
}
