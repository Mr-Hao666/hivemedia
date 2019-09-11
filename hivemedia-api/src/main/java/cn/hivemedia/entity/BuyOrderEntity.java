package cn.hivemedia.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 购买订单信息表
 * 
 * @author yhao
 * @email 102126854@qq.com
 * @date 2019-01-01 00:24:48
 */
@Data
@ApiModel("购买订单信息表")
@TableName("tb_buy_order")
public class BuyOrderEntity implements Serializable {
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

	@ApiModelProperty("出售订单ID")
	private Long saleOrderId;
	/**
	 * 买家用户id
	 */
	@ApiModelProperty("买家用户id")
	private Long buyUserId;
	/**
	 * 订单单号(供查询)
	 */
	@ApiModelProperty("订单单号(供查询)")
	private String orderNo;
	/**
	 * 商品Id
	 */
	@ApiModelProperty("商品Id")
	private String goodsId;

	@ApiModelProperty("商品展示图(主图)地址")
	@TableField(exist = false)
	private String picShowUrl;

	/**
	 * 商品规尺码Id
	 */
	@ApiModelProperty("商品规尺码Id")
	private String sizeId;
	/**
	 * 订单状态  -1：待支付保证金  0：求购中；1:待付款 2:已付款 3:已发往Biu平台 4:买家待收货(平台验货通过) 5:平台验货未通过 6:已签收 7:退货申请中 8:退货中 9:已完成退货 10:取消交易
	 */
	@ApiModelProperty("订单状态  -1：待支付保证金  0：求购中；1:待付款 2:已付款 3:已发往Biu平台 4:买家待收货(平台验货通过) 5:平台验货未通过 6:已签收 7:退货申请中 8:退货中 9:已完成退货 10:取消交易,11:交易失败")
	private Integer orderStatus;
	/**
	 * 仅针对订单状态为8,订单取消原因：1、拍错了；2、商品不发货；3、协商一致退款；4、其他原因'
	 */
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
//	/**
//	 * 第三方支付流水号
//	 */
//	@ApiModelProperty("第三方支付流水号")
//	private String outTradeNo;
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
	private Long couponId;
	/**
	 * 保证金
	 */
	@ApiModelProperty("保证金")
	private BigDecimal deposit;
	/**
	 * 快递费
	 */
	@ApiModelProperty("快递费")
	private BigDecimal expressFee;
	/**
	 * 1:立即购买 2:出价求购
	 */
	@ApiModelProperty("购买类型：1:立即购买 2:出价求购")
	private Integer buyType;
	/**
	 * 求购时间
	 */
	@ApiModelProperty("求购时间")
	private Integer wantBuyDays;

	/**
	 * 尺码名称
	 */
	@ApiModelProperty("尺码名称")
	@TableField(exist = false)
	private String sizeDesc;

	/**
	 * 商品名称
	 */
	@ApiModelProperty("商品名称")
	@TableField(exist = false)
	private String goodsName;
	/**
	 * 快递单号
	 */
	@ApiModelProperty("快递单号")
	@TableField(exist = false)
	private String expressNo;

	/**
	 * 收货人姓名
	 */
	@ApiModelProperty("收货人姓名")
	@TableField(exist = false)
	private String consigneeRealname;
	/**
	 * 收货人地址
	 */
	@ApiModelProperty("收货人地址")
	@TableField(exist = false)
	private String consigneeAddress;

	/**
	 * 收货人手机号
	 */
	@ApiModelProperty("收货人手机号")
	@TableField(exist = false)
	private String consigneeTelphone;

	@ApiModelProperty("是否被卖家变现，1：是；0：否")
	private Integer isCash;

    /**
     * 1.现货上架 2.预售上架 3.立即变现
     */
    @ApiModelProperty("1.现货上架 2.预售上架 3.立即变现")
    @TableField(exist = false)
    private Integer saleType;
}
