package cn.hivemedia.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单物流信息表
 * 
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:24:48
 */
@Data
@ApiModel("订单物流信息")
@TableName("tb_order_logistics")
public class OrderLogisticsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	@ApiModelProperty("id")
	private Long id;
	/**
	 * 订单id
	 */
	@ApiModelProperty("订单id")
	private Long orderId;
	/**
	 * 物流单号
	 */
	@ApiModelProperty("物流单号")
	private String expressNo;
	/**
	 * 收货人姓名(更保险)
	 */
	@ApiModelProperty("收货人姓名(更保险)")
	private String consigneeRealname;
	/**
	 * 联系电话(更保险)
	 */
	@ApiModelProperty("联系电话(更保险)")
	private String consigneeTelphone;
	/**
	 * 收货地址
	 */
	@ApiModelProperty("收货地址")
	private String consigneeAddress;
	/**
	 * 邮政编码
	 */
	@ApiModelProperty("邮政编码")
	private String consigneeZip;
	/**
	 * 物流方式 1:ems ...
	 */
	@ApiModelProperty("物流方式 1:ems ...")
	private Integer logisticsType;
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
	 * 订单类型 1:出售 2:购买
	 */
	@ApiModelProperty("订单类型 1:出售 2:购买")
	private Integer orderType;

	/**
	 * 寄回联系人姓名
	 */
	@ApiModelProperty("寄回联系人姓名")
	private String returnConsigneeRealname;

	/**
	 * 寄回联系人电话
	 */
	@ApiModelProperty("寄回联系人电话")
	private String returnConsigneeTelphone;

	/**
	 * 寄回地址
	 */
	@ApiModelProperty("寄回地址")
	private String returnConsigneeAddress;

}
