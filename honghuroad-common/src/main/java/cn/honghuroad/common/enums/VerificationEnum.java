package cn.honghuroad.common.enums;

/**
 * 验证码缓存类型前缀
 * @author lxy
 */
public enum VerificationEnum {

    /**
     * 手机号登陆
     */
    LOGIN("login"),

    /**
     * 绑定
     */
    BINDING("bind"),

    /**
     * 更换手机号
     */
    CHANGE("change");

    private String type;

    VerificationEnum(String type) {
        this.type = type;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static VerificationEnum getType(String code) {

        for (VerificationEnum enums : values()) {
            if (code.equals(enums.getType())) {
                return enums;
            }
        }
        return VerificationEnum.LOGIN;

    }



    public static void main(String[] args) {
        System.out.println(VerificationEnum.LOGIN.getType());
        System.out.println(VerificationEnum.getType("bind"));

    }
}
