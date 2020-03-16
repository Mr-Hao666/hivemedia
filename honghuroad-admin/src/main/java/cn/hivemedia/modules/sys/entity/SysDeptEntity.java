package cn.honghuroad.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * 部门管理
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2017-06-20 15:23:47
 */
@Data
@TableName("sys_dept")
public class SysDeptEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //部门ID
    @TableId
    private Long deptId;
    //上级部门ID，一级部门为0
    private Long parentId;
    //部门名称
    private String name;
    //上级部门名称
    @TableField(exist = false)
    private String parentName;
    //排序
    private Integer orderNum;

    @TableLogic
    private Integer delFlag;
    /**
     * ztree属性
     */
    @TableField(exist = false)
    private Boolean open;
    @TableField(exist = false)
    private List<?> list;
}
