package cn.hivemedia.common.enums;

/**
 * @author 杨浩
 * @create 2019-01-13 22:14
 **/
public enum BuyOrderStatus {
    //订单状态   -1：待支付保证金  0：求购中；1:待付款 2:已付款 3:已发往Biu平台 4:买家待收货(平台验货通过) 5:平台验货未通过 6:已签收 7:退货申请中 8:退货中 9:已完成退货 10:取消交易
    UNPAID_EPOSIT(-1, "待支付保证金"),
    IN_BUY(0, "求购中"),
    PENDING_PAYMENT(1, "待付款"),
    PAID(2, "已付款"),
    SHIPPED_PLATFORM(3, "已发往Biu平台"),
    IN_INSPECTION(11,"平台已签收，查验中"),
    SHIPPED(4, "买家待收货(平台验货通过)"),
    UNPASS(5, " 平台验货未通过"),
    ALREADY_SIGNED(6, "已签收"),
    APPLICATION_FOR_RETURN(7, "退货申请中"),
    RETURNS(8, "退货中"),
    RETURN_COMPLETED(9, "已完成退货"),
    CANCEL_TRANSACTION(10, "取消交易");

    private Integer code;
    private String desc;

    BuyOrderStatus() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    BuyOrderStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
