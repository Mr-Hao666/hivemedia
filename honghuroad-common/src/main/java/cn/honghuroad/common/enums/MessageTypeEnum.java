package cn.honghuroad.common.enums;

/**
 * Created by chenzq
 * Date: 2019/3/1 上午12:33
 **/
public enum MessageTypeEnum {
    ORDER_NOTIFY(1, "订单通知"),
    BIU_GOODS(2, "Biu好货")
    ;

    private Integer code;
    private String desc;

    MessageTypeEnum() {
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

    MessageTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
