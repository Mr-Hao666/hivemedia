package cn.honghuroad.entity.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by chenzq
 * Date: 2019/1/8 下午10:09
 **/
@Data
public class SaleOrderQueryDto implements Serializable {
    @ApiModelProperty("出售订单ID")
    private Long saleOrderId;
    @ApiModelProperty("卖家用户id（当前登录账号用户ID）")
    private Long saleUserId;
    @ApiModelProperty("商品ID")
    private Integer goodsId;
    @ApiModelProperty("商品尺码ID")
    private Integer sizeId;
    @ApiModelProperty("出售类型：1.现货上架 2.预售上架 3.立即变现")
    private Integer saleType;
    @ApiModelProperty("保证金")
    private BigDecimal deposit;
    @ApiModelProperty("尺码")
    private String desc;
    @ApiModelProperty("价格")
    private BigDecimal amountTotal;
}
