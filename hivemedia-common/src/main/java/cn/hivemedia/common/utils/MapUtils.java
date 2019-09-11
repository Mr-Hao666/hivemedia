package cn.hivemedia.common.utils;

import java.util.HashMap;


/**
 * Map工具类
 *
 * @author yhao
 * @since 3.0.0
 */
public class MapUtils extends HashMap<String, Object> {

    @Override
    public MapUtils put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
