package cn.honghuroad.common.enums;

/**
 * @author 杨浩
 * @create 2019-01-13 22:14
 **/
public enum SaleOrderStatus {
    //订单状态  -1:待支付保证金 0:出售中；1:待付款 2:已付款 3:已发货 4:已签收 5:退货申请中 6:退货中 7:已完成退货 8:取消交易9, "交易失败"
    UNPAID_EPOSIT(-1, "待支付保证金"),
    IN_SALE(0, "出售中"),
    PENDING_PAYMENT(1, "待付款"),
    PAID(2, "已付款"),
    SHIPPED(3, "已发货"),
    ALREADY_SIGNED(4, "平台已签收"),
    APPLICATION_FOR_RETURN(5, "退货申请中"),
    RETURNS(6, "退货中"),
    RETURN_COMPLETED(7, "已完成退货"),
    CANCEL_TRANSACTION(8, "取消交易"),
    TRANSACTION_FAIL(9, "交易失败");

    private Integer code;
    private String desc;

    SaleOrderStatus() {
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

    SaleOrderStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
