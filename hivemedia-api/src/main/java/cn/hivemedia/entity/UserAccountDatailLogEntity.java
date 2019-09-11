package cn.hivemedia.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 账户明细日志表
 * 
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:26:16
 */
@Data
@ApiModel("账户明细")
@TableName("tb_user_account_datail_log")
public class UserAccountDatailLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 账户id
	 */
	@ApiModelProperty("账户id")
	private Long accountId;
	/**
	 * 变动类型 1:退换至微信 2:定金退回 3:支付保证金 4:微信转账...
	 */
	@ApiModelProperty("变动类型 1:退换至微信 2:定金退回 3:支付保证金 4:微信转账...")
	private Integer changeType;
	/**
	 * 变动金额
	 */
	@ApiModelProperty("变动金额")
	private BigDecimal changeMoney;
	/**
	 * 变动前金额
	 */
	@ApiModelProperty("变动前金额")
	private BigDecimal beforeChangeMoney;
	/**
	 * 提现状态
	 */
	@ApiModelProperty("提现状态")
	private Integer cashStatus;
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
	 * 状态 0:有效 1:无效
	 */
	@ApiModelProperty("状态 0:有效 1:无效")
	private Integer state;

}
