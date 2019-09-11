package cn.hivemedia.modules.goods.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 商品分类表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
@Data
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
    private Integer pid;
    /**
     * 图标ID
     */
    private Integer picId;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 排序
     */
    private Integer sortNo;
    /**
     * 创建人id
     */
    private Long createBy;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 更新人id
     */
    private Integer updaetBy;
    /**
     * 更新时间
     */
	private Date updateDate;
    /**
     * 数据状态 0:正常 1:删除
     */
    private Integer delFlag;
}
