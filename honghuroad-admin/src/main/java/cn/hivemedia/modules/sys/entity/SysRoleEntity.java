package cn.honghuroad.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * 角色
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2016年9月18日 上午9:27:38
 */
@Data
@TableName("sys_role")
public class SysRoleEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @TableId
    private Long roleId;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 部门ID
     */
    @NotNull(message = "部门不能为空")
    private Long deptId;

    /**
     * 部门名称
     */
    @TableField(exist = false)
    private String deptName;

    @TableField(exist = false)
    private List<Long> menuIdList;
    @TableField(exist = false)
    private List<Long> deptIdList;

    /**
     * 创建时间
     */
    private Date createTime;
}
