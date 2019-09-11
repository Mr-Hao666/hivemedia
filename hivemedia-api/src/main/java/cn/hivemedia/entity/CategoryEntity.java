package cn.hivemedia.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 商品分类表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:26:15
 */
@Data
@ApiModel("品牌分类信息")
@TableName("tb_category")
public class CategoryEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 父分类ID
     */
    @ApiModelProperty("父分类ID")
    private Integer pid;
    /**
     * 父分类名称
     */
    @ApiModelProperty("父分类名称")
    @TableField(exist = false)
    private String pName;
    /**
     * 图标ID
     */
    @ApiModelProperty("图标ID")
    private Integer picId;

    /**
     * 图标链接
     */
    @TableField(exist = false)
    @ApiModelProperty("图标链接")
    private String picUrl;
    /**
     * 分类名称
     */
    @ApiModelProperty("分类名称")
    private String name;
    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer sortNo;
    /**
     * 创建人id
     */
    @ApiModelProperty("创建人id")
    private Long createBy;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
	private Date createTime;
    /**
     * 更新人id
     */
    @ApiModelProperty("更新人id")
    private Integer updaetBy;
    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
	private Date updateDate;
    /**
     * 数据状态 0:正常 1:删除
     */
    @ApiModelProperty("数据状态 0:正常 1:删除")
    private Integer delFlag;
    /**
     * 级别
     */
    @ApiModelProperty("级别")
    private Integer level;

    /**
     * 子分类
     */
    @ApiModelProperty("子分类")
    @TableField(exist = false)
    private List<CategoryEntity> children;

}
