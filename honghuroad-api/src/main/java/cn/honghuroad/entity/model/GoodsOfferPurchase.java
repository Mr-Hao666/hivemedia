package cn.honghuroad.entity.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by chenzq
 * Date: 2019/1/23 下午9:25
 **/
@Data
public class GoodsOfferPurchase implements Serializable {
    @ApiModelProperty("最高求购价")
    private BigDecimal amountTotal;
    @ApiModelProperty("求购订单ID")
    private Integer buyOrderId;
}
