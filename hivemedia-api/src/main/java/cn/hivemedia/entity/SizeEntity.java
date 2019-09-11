package cn.hivemedia.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 尺码信息表
 * 
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:26:16
 */
@Data
@TableName("tb_size")
public class SizeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	@ApiModelProperty("尺码id")
	private Integer id;
	/**
	 * 尺码描述
	 */
	@ApiModelProperty("尺码描述")
	private String desc;
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
	private Long updateBy;
	/**
	 * 更新时间
	 */
	@ApiModelProperty("更新时间")
	private Date updateTime;
	/**
	 * 数据状态 0:有效 1:无效
	 */
	@ApiModelProperty("数据状态 0:有效 1:无效")
	private Integer delFlag;

}
