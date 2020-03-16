package cn.honghuroad.entity.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class OrderGoods {

    @ApiModelProperty("交易金额")
    private String orderAmountTotal;

    @ApiModelProperty("交易时间")
	private Date createTime;

    @ApiModelProperty("尺码")
    private String desc;

    @ApiModelProperty("1.现货上架 2.预售上架 3.立即变现")
    private String saleType;




    public String getOrderAmountTotal() {
        return orderAmountTotal;
    }

    public void setOrderAmountTotal(String orderAmountTotal) {
        this.orderAmountTotal = orderAmountTotal;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSaleType() {
        return saleType;
    }

    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }
}
