package cn.hivemedia.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户地址信息表
 * 
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:26:16
 */
@Data
@ApiModel("用户地址信息")
@TableName("tb_user_address")
public class UserAddressEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	@ApiModelProperty("id")
	private Long id;
	/**
	 * 用户id
	 */
	@ApiModelProperty("用户id")
	private Long userId;
	/**
	 * 用户名
	 */
	@ApiModelProperty("用户名")
	private String userName;
	/**
	 * 手机号码
	 */
	@ApiModelProperty("手机号码")
	private String phone;
	/**
	 * 省id
	 */
	@ApiModelProperty("省id")
	private Integer provinceId;
	/**
	 * 市id
	 */
	@ApiModelProperty("市id")
	private Integer cityId;
	/**
	 * 区id
	 */
	@ApiModelProperty("区id")
	private Integer areaId;
	/**
	 * 省市区
	 */
	@ApiModelProperty("省市区")
	private String location;
	/**
	 * 详细地址
	 */
	@ApiModelProperty("详细地址")
	private String detailAddress;
	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	private Date createTime;
	/**
	 * 更新时间
	 */
	@ApiModelProperty("更新时间")
	private Date updateTime;
	/**
	 * 是否默认 0:否 1:是(同一个用户只能有一个默认地址)
	 */
	@ApiModelProperty("是否默认 0:否 1:是(同一个用户只能有一个默认地址)")
	private Integer isDefault;

}
