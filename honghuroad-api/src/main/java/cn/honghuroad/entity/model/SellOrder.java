package cn.honghuroad.entity.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by chenzq
 * Date: 2019/1/6 下午10:10
 **/
@Data
public class SellOrder implements Serializable {
    @ApiModelProperty("卖家用户id（当前登录账号用户ID）")
    private Integer saleUserId;
    @ApiModelProperty("商品ID")
    private Integer goodsId;
    @ApiModelProperty("商品尺码ID")
    private Integer sizeId;
    @ApiModelProperty("出售类型：1.现货上架 2.预售上架 3.立即变现")
    private Integer saleType;
    @ApiModelProperty("保证金")
    private BigDecimal deposit;
    @ApiModelProperty("银行转账费")
    private BigDecimal bankFee;
    @ApiModelProperty("商品查验费")
    private BigDecimal goodsCheckFee;
    @ApiModelProperty("包装费")
    private BigDecimal packFee;
    @ApiModelProperty("手续费")
    private BigDecimal proceduresFee;
    @ApiModelProperty("立即变现对应的最高求购订单的订单ID")
    private Integer buyOrderId;
    @ApiModelProperty("商品名称")
    private String goodsName;
    @ApiModelProperty("商品金额")
    private BigDecimal goodsPrice;
    @ApiModelProperty("尺码")
    private String desc;
    /**
     * 平台费用
     */
    @ApiModelProperty("平台费用")
    private BigDecimal platformFee;


}
