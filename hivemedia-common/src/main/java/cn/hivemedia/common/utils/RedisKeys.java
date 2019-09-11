package cn.hivemedia.common.utils;

/**
 * @author ZengXiong
 * @Description 获取redis中keys
 * @Date 2018/11/22 19:10
 */
public class RedisKeys {

    public static String getSysConfigKey(String key) {
        return "sys:config:" + key;
    }

    public static String getShiroSessionKey(String key) {
        return "sessionid:" + key;
    }
}
