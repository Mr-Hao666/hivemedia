package cn.honghuroad.common.enums;

/**
 * 用户账户明细账户变动类型
 * @author 杨浩
 * @create 2019-01-12 15:54
 **/
public enum UserAccountDetailLogChangeType {
    REFUND_WECHAT(1,"退换至微信"),
    DEPOSIT_REFUND(2,"定金退回"),
    PAYMENT_OF_DEPOSIT(3,"支付保证金"),
    WECHAT_TRANSFER(4,"微信转账"),
    CASH_WITHDRAWAL(5,"提现");
    private Integer code;
    private String desc;
    //变动类型 1:退换至微信 2:定金退回 3:支付保证金 4:微信转账...

    UserAccountDetailLogChangeType() {
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

    UserAccountDetailLogChangeType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }



}
