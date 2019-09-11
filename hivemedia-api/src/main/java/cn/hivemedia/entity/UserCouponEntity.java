package cn.hivemedia.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户—优惠券关联表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
@Data
@TableName("tb_user_coupon")
@ApiModel("用户优惠券")
public class UserCouponEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户优惠券关联表id
     */
    @TableId
    private Long id;
    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Long userId;
    /**
     * 满额送优惠券的子订单ID
     */
    @ApiModelProperty("满额送优惠券的子订单ID")
    private Long orderId;
    /**
     * 优惠券ID
     */
    @ApiModelProperty("优惠券ID")
    private Long couponId;
    /**
     * 使用条件的值
     */
    @ApiModelProperty("使用条件的值")
    private BigDecimal useAmount;
    /**
     * 优惠券金额
     */
    @ApiModelProperty("优惠券金额")
    private BigDecimal amount;
    /**
     * 状态：0，待发放；1，未使用 ；2，已使用；3，已过期；9，已作废；
     */
    @ApiModelProperty("状态：0，待发放；1，未使用 ；2，已使用；3，已过期；9，已作废；")
    private Integer status;
    /**
     * 生效类型  1：有效期内生效，2：领取一段时间内有效
     */
    @ApiModelProperty("生效类型  1：有效期内生效，2：领取一段时间内有效")
    private Integer type;
    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
	private Date beginTime;
    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
	private Date endTime;
    /**
     * 简介描述
     */
    @ApiModelProperty("简介描述")
    private String summary;
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
