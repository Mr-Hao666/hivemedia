package cn.honghuroad.common.enums;

/**
 * Created by chenzq
 * Date: 2019/3/1 下午5:41
 **/
public enum BiuGoodsMsgTypeEnum {
    /**
     * Biu好货消息类型 1：出售提醒；2：变现提醒；3：更高求购价提醒；
     */
    BIU_GOODS_SALE_NOTIFY(1, "出售提醒"),
    LIQUIDATION_NOTIFY(2, "变现提醒"),
    MAX_PRICE(3, "更高求购价提醒"),
    ;

    private Integer code;
    private String desc;

    BiuGoodsMsgTypeEnum() {
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

    BiuGoodsMsgTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
