package cn.hivemedia.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户账户信息
 * 
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:27:17
 */
@Data
@ApiModel("用户账户")
@TableName("tb_user_account")
public class UserAccountEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 用户id
	 */
	@ApiModelProperty("用户id")
	private Long userId;
	/**
	 * 账户余额
	 */
	@ApiModelProperty("账户余额")
	private BigDecimal remainingMoney;

	@ApiModelProperty("是否绑定支付宝：0,未绑定;1,已绑定")
	@TableField(exist = false)
	private Integer hasBind;
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

}
