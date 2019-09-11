package cn.hivemedia.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 关键字设置表
 * 
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:27:18
 */
@Data
@ApiModel("关键字")
@TableName("tb_keyword_config")
public class KeywordConfigEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Integer id;
	/**
	 * 关键字
	 */
	private String keyword;
	/**
	 * 排序编号
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
	private Long updateBy;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 数据状态 0:有效 1:无效
	 */
	private Integer delFlag;

}
