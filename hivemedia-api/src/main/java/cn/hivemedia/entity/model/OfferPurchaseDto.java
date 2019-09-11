package cn.hivemedia.entity.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by chenzq
 * Date: 2019/1/8 下午11:29
 **/
@Data
public class OfferPurchaseDto implements Serializable {
    @ApiModelProperty("出售订单ID")
    private Long saleOrderId;
    @ApiModelProperty("卖家用户id（当前登录账号用户ID）")
    private Long saleUserId;
    @ApiModelProperty("商品ID")
    private Integer goodsId;
    @ApiModelProperty("商品尺码ID")
    private Integer sizeId;
    @ApiModelProperty("尺码")
    private String desc;
    @ApiModelProperty("价格（最高求购价）")
    private BigDecimal amountTotal;
    @ApiModelProperty("当前最低售价")
    private BigDecimal minAmountTotal;

    public void validate(){
        if (saleOrderId==null){
            this.setSaleOrderId(0L);
        }
        if (saleUserId==null){
            this.setSaleUserId(0L);
        }
        if (this.minAmountTotal==null){
            this.setMinAmountTotal(new BigDecimal(0));
        }
        if (this.amountTotal==null){
            this.setAmountTotal(new BigDecimal(0));
        }
    }

}
