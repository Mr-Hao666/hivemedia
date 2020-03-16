package cn.honghuroad.entity.model;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class SaleGoods {

    @ApiModelProperty("最高求购价")
    private BigDecimal amountTotal;


    @ApiModelProperty("尺码")
    private String desc;

    @ApiModelProperty("商品尺码Id")
    private Integer sizeId;

    @ApiModelProperty("最低售价")
    private BigDecimal amountMin;

    public void validate(){
        if (this.amountMin==null){
            this.setAmountMin(new BigDecimal(0));
        }
        if (this.amountTotal==null){
            this.setAmountTotal(new BigDecimal(0));
        }
    }



    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }


    public Integer getSizeId() {
        return sizeId;
    }

    public void setSizeId(Integer sizeId) {
        this.sizeId = sizeId;
    }

    public BigDecimal getAmountMin() {
        return amountMin;
    }

    public void setAmountMin(BigDecimal amountMin) {
        this.amountMin = amountMin;
    }
}
