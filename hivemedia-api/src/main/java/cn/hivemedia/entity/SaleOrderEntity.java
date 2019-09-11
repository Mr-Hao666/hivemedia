package cn.hivemedia.entity;

import com.baomidou.mybatisplus.annotations.TableField;
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
 * 出售订单信息表
 * 
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:24:48
 */
@Data
@ApiModel("出售订单信息")
@TableName("tb_sale_order")
public class SaleOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	@ApiModelProperty("订单id")
	private Long id;
	/**
	 * 卖家用户id
	 */
	@ApiModelProperty("卖家用户id")
	private Integer saleUserId;
	/**
	 * 买家用户id
	 */
	@ApiModelProperty("卖家用户id")
	private Integer buyUserId;
	/**
	 * 订单单号(供查询)
	 */
	@ApiModelProperty("订单单号(供查询)")
	private String orderNo;
	/**
	 * 订单状态 0：出售中；1:待付款 2:已付款 3:已发货 4:已签收 5:退货申请中 6:退货中 7:已完成退货 8:取消交易
	 */
	@ApiModelProperty("订单状态 -1：待支付保证金 0：出售中；1:待付款 2:已付款 3:已发货 4:已签收 5:退货申请中 6:退货中 7:已完成退货 8:取消交易 9:交易失败")
	private Integer orderStatus;
	/**
	 * 仅针对订单状态为8,订单取消原因：1、拍错了；2、商品不发货；3、协商一致退款；4、其他原因'
	 */
	@ApiModelProperty("仅针对订单状态为8,订单取消原因：1、拍错了；2、商品不发货；3、协商一致退款；4、其他原因'")
	private Integer cancelReason;
	/**
	 * 商品项目数量(不是商品的数量)
	 */
	private Integer goodsCount;
	/**
	 * 商品总价(定价)
	 */
	@ApiModelProperty("商品总价(定价)")
	private BigDecimal amountTotal;
	/**
	 * 实际付款金额
	 */
	@ApiModelProperty("实际付款金额")
	private BigDecimal orderAmountTotal;
	/**
	 * 运费金额
	 */
	private BigDecimal logisticsFee;
	/**
	 * 是否开发票 0:否 1:是 (保留字段)
	 */
	private Integer makeOutInvoice;
	/**
	 * 发票编号 (保留字段)
	 */
	private String invoiceNo;
	/**
	 * 订单收货地址
	 */
	@ApiModelProperty("订单收货地址")
	private String address;
	/**
	 * 物流id
	 */
	@ApiModelProperty("物流id")
	private Long logisticsId;
	/**
	 * 支付渠道 1:支付宝 2:微信...
	 */
	@ApiModelProperty("支付渠道 1:支付宝 2:微信...")
	private Integer payChannel;
	/**
	 * 创建时间(下单时间)
	 */
	@ApiModelProperty("创建时间(下单时间)")
	private Date createTime;
	/**
	 * 付款时间
	 */
	@ApiModelProperty("付款时间")
	private Date payTime;
	/**
	 * 订单备注
	 */
	@ApiModelProperty("订单备注")
	private String remarks;
	/**
	 * 更新时间
	 */
	@ApiModelProperty("更新时间")
	private Date updateTime;
	/**
	 * 订单状态 1:有效 2:无效
	 */
	@ApiModelProperty("订单状态 1:有效 2:无效")
	private Integer curState;
	/**
	 * 优惠券ID
	 */
	@ApiModelProperty("优惠券ID")
	private Long couponId;
	/**
	 * 1.现货上架 2.预售上架 3.立即变现
	 */
	@ApiModelProperty("1.现货上架 2.预售上架 3.立即变现")
	private Integer saleType;
	/**
	 * 保证金
	 */
	@ApiModelProperty("保证金")
	private BigDecimal deposit;
	/**
	 * 银行转账费
	 */
	@ApiModelProperty("银行转账费")
	private BigDecimal bankFee;
	/**
	 * 商品查验费
	 */
	@ApiModelProperty("商品查验费")
	private BigDecimal goodsCheckFee;
	/**
	 * 包装费
	 */
	@ApiModelProperty("包装费")
	private BigDecimal packFee;
	/**
	 * 手续费
	 */
	@ApiModelProperty("手续费")
	private BigDecimal proceduresFee;
	/**
	 * 快递费
	 */
	@ApiModelProperty("快递费")
	private BigDecimal expressFee;

	/**
	 * 商品ID
	 */
	@ApiModelProperty("商品ID")
	private Integer goodsId;

	@ApiModelProperty("商品展示图(主图)地址")
	@TableField(exist = false)
	private String picShowUrl;

	/**
	 * 商品尺码ID
	 */
	@ApiModelProperty("商品尺码ID")
	private Integer sizeId;

	/**
	 * 商品尺码
	 */
	@ApiModelProperty("商品尺码")
	@TableField(exist = false)
	private String sizeDesc;

	/**
	 * 立即变现对应的最高求购订单的订单ID
	 */
	@ApiModelProperty("立即变现对应的最高求购订单的订单ID")
	private Integer buyOrderId;

	/**
	 * 是否已售出，0：未售出；1：已售出;
	 */
	@ApiModelProperty("是否已售出，0：未售出；1：已售出;")
	private Boolean isSale;

	/**
	 * 商品名称
	 */
	@ApiModelProperty("商品名称")
	@TableField(exist = false)
	private String goodsName;

	/**
	 * 平台费用
	 */
	@ApiModelProperty("平台费用")
	private BigDecimal platformFee;

	@TableField(exist = false)
	private String sale;

	/**
	 * 物流信息
	 */
	@ApiModelProperty("物流信息")
	@TableField(exist = false)
	private OrderLogisticsEntity orderLogistics;


}
