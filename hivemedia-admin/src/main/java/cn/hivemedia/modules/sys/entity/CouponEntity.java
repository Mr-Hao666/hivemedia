package cn.hivemedia.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 优惠券表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
@Data
@TableName("tb_coupon")
public class CouponEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 优惠券名
     */
    private String name;
    /**
     * 使用条件的值
     */
    private BigDecimal useAmount;
    /**
     * 优惠券金额
     */
    private BigDecimal amount;
    /**
     * 发放总数
     */
    private Integer total;
    /**
     * 已领取数量
     */
    private Integer issusedNum;
    /**
     * 状态 1:有效 2:无效 3，删除；
     */
    private Integer curState;
    /**
     * 一段时间内有效的天数
     */
    private Integer number;
    /**
     * 生效类型  1：有效期内生效，2：领取一段时间内有效
     */
    private Integer type;
    /**
     * 开始时间
     */
	private Date beginTime;
    /**
     * 结束时间
     */
	private Date endTime;
    /**
     * 简介描述
     */
    private String summary;
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
}
