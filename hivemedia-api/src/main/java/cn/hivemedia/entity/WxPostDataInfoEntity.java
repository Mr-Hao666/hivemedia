package cn.hivemedia.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * 
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-09 20:27:03
 */
@Data
@TableName("wx_post_data_info")
public class WxPostDataInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 订单ID
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
	private String appid;
	/**
	 * 
	 */
	private String openid;
	/**
	 * 
	 */
	private String totalfee;
	/**
	 * 
	 */
	private String resultcode;
	/**
	 * 
	 */
	private String returncode;
	/**
	 * 
	 */
	private String timeend;
	/**
	 * 
	 */
	private String outtradeno;
	/**
	 * 
	 */
	private String cashfee;
	/**
	 * 
	 */
	private String banktype;
	/**
	 * 
	 */
	private String noncestr;
	/**
	 * 
	 */
	private String tradetype;
	/**
	 * 
	 */
	private String transactionid;
	/**
	 * 
	 */
	private String issubscribe;

}
