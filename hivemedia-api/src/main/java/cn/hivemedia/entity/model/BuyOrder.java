package cn.hivemedia.entity.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by chenzq
 * Date: 2019/1/9 下午8:23
 **/
@Data
public class BuyOrder implements Serializable {
    @ApiModelProperty("当前登录用户ID")
    private Integer userId;
    @ApiModelProperty("出售订单ID")
    private Long saleOrderId;
    @ApiModelProperty("卖家用户id（当前登录账号用户ID）")
    private Long saleUserId;
    @ApiModelProperty("商品ID")
    private String goodsId;
    @ApiModelProperty("商品尺码ID")
    private String sizeId;
    @ApiModelProperty("保证金")
    private BigDecimal deposit;
    @ApiModelProperty("尺码")
    private String desc;
    @ApiModelProperty("价格")
    private BigDecimal amountTotal;
    @ApiModelProperty("商品名称")
    private String goodsName;
    @ApiModelProperty("收货人姓名")
    private String consigneeRealname;
    @ApiModelProperty("联系电话")
    private String consigneeTelphone;
    @ApiModelProperty("收货地址")
    private String consigneeAddress;
    @ApiModelProperty("快递费")
    private BigDecimal expressFee;
    @ApiModelProperty("实际成交价")
    private BigDecimal orderAmountTotal;
    @ApiModelProperty("优惠券ID")
    private Long couponId;
    @ApiModelProperty("求购时长")
    private Integer wantBuyDays;

}
