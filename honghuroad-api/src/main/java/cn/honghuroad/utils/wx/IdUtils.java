package cn.honghuroad.utils.wx;

import java.util.UUID;

public class IdUtils {
    /**
     * 生成去掉“-”的uuid（全大写）
     */
	public static String uuid(){
		return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}
}
