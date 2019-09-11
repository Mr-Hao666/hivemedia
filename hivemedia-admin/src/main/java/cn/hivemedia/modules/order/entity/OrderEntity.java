package cn.hivemedia.modules.order.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 订单信息表
 *
 * @author yhao
 * @email 102126854@qq.com
 * @date 2018-11-21 10:59:48
 */
@Data
@TableName("tb_order")
public class OrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 卖家用户id
     */
    private Integer saleUserId;
    /**
     * 买家用户id
     */
    private Integer buyUserId;
    /**
     * 订单单号(供查询)
     */
    private String orderNo;
    /**
     * 订单状态 1:待付款 2:已付款 3:已发货 4:已签收 5:退货申请中 6:退货中 7:已完成退货 8:取消交易
     */
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
     * 商品总价
     */
    private BigDecimal amountTotal;
    /**
     * 实际付款金额
     */
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
     * 订单收货地址id
     */
    private Long addressId;
    /**
     * 物流id
     */
    private Long logisticsId;
    /**
     * 支付渠道 1:支付宝 2:微信...
     */
    private Integer payChannel;
    /**
     * 第三方支付流水号
     */
    private String outTradeNo;
    /**
     * 创建时间(下单时间)
     */
	private Date createTime;
    /**
     * 付款时间
     */
	private Date payTime;
    /**
     * 订单备注
     */
    private String remarks;
    /**
     * 更新时间
     */
	private Date updateTime;
    /**
     * 订单状态 1:有效 2:无效
     */
    private Integer curState;
    /**
     *
     */
    private Long couponId;
}
