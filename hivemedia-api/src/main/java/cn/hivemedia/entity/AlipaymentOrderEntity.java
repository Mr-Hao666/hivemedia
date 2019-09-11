package cn.hivemedia.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-08 16:53:24
 */
@Data
@TableName("alipayment_order")
public class AlipaymentOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 购买订单ID
	 */
	private Integer clubOrderId;
	/**
	 * 订单类型，0：购买订单；1：出售订单
	 */
	private Integer orderType;
	/**
	 * 支付类型：1，保证金支付，2，货款支付',
	 */
	private Integer payType;
	/**
	 * 
	 */
	private String outTradeNo;
	/**
	 * 判断交易结果:0,交易创建并等待买家付款;
	 * 1,未付款交易超时关闭或支付完成后全额退款;
	 * 2,交易支付成功;
	 * 3,交易结束并不可退款;
	 */
	private Integer tradeStatus;
	/**
	 * 
	 */
	private BigDecimal totalAmount;
	/**
	 * 
	 */
	private BigDecimal receiptAmount;
	/**
	 * 
	 */
	private BigDecimal invoiceAmount;
	/**
	 * 
	 */
	private BigDecimal buyerPayAmount;
	/**
	 * 
	 */
	private BigDecimal refundFee;
	/**
	 * 
	 */
	private Date notifyTime;
	/**
	 * 
	 */
	private Date gmtCreate;
	/**
	 * 
	 */
	private Date gmtPayment;
	/**
	 * 
	 */
	private Date gmtRefund;
	/**
	 * 
	 */
	private Date gmtClose;
	/**
	 * 
	 */
	private String tradeNo;
	/**
	 * 
	 */
	private String outBizNo;
	/**
	 * 
	 */
	private String buyerLogonId;
	/**
	 * 
	 */
	private String sellerId;
	/**
	 * 
	 */
	private String sellerEmail;

}
