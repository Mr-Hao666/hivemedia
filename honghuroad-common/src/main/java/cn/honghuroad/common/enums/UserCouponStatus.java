package cn.honghuroad.common.enums;

/**
 * @author 杨浩
 * @create 2019-03-02 15:07
 **/
public enum UserCouponStatus {

    //状态：0，待发放；1，未使用 ；2，已使用；3，已过期；9，已作废；
    TO_BE_ISSUED(0, "待发放"),
    NOT_USED(1, "未使用"),
    USED(2, "已使用"),
    EXPIRED(3, "已过期"),
    SCRAPPED(9, "已作废");


    private Integer code;
    private String desc;

    UserCouponStatus() {
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

    UserCouponStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
