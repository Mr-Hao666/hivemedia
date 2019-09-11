package cn.hivemedia.utils.wx;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

public class WxUtil {
	
	private static Logger logger = LoggerFactory.getLogger(WxUtil.class);
	
	/**
	 * 获取OPENID
	 * 
	 * */
	public static JSONObject getOpenIdInfo(String appid, String secret, String code) throws Exception{
		//第一次请求 获取access_token 和 openid
	    String getOpenIdUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="+secret+"&code="+code+"&grant_type=authorization_code";
	    String oppid = HttpRequestor.doGet(getOpenIdUrl);
		logger.info("oppid="+oppid);
		return JSONObject.parseObject(oppid);
	}
	
	/**
	 * 获取微信用户信息
	 * 
	 * */
	public static JSONObject getUserInfo(String access_token, String openid) throws Exception{
		String getUserInfoUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
	 	String userInfo = HttpRequestor.doGet(getUserInfoUrl);
	 	logger.info("userInfo="+userInfo);
	 	return JSONObject.parseObject(userInfo);
	}
	
	/**
	 * 获取ACCESS_TOKEN
	 * 
	 * */
	public static String getAccessToken(String appid, String secret) throws Exception{
		
		logger.info("redis中没有对应的accessToken或者accessToken过期");
		String getAccessUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret&secret="+secret+"&code";
	 	String accessInfo = HttpRequestor.doGet(getAccessUrl);
	 	logger.info("accessInfo="+accessInfo);
	 	JSONObject acc = JSONObject.parseObject(accessInfo);
		return acc.getString("access_token");
	}
	
	public static Map<String, String> parseXml(HttpServletRequest request)
			throws Exception {
		// 解析结果存储在HashMap
		Map<String, String> map = new HashMap<String, String>();
		InputStream inputStream = request.getInputStream();
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();

		// 遍历所有子节点
		for (Element e : elementList)
			map.put(e.getName(), e.getText());

		// 释放资源
		inputStream.close();
		inputStream = null;

		return map;
	}
	
	 public static String getReturnData(String urlString) throws Exception {
		String res = "";
		try {
			URL url = new URL(urlString);
			java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url
					.openConnection();
			conn.connect();
			java.io.BufferedReader in = new java.io.BufferedReader(
					new java.io.InputStreamReader(conn.getInputStream(),
							"UTF-8"));
			String line;
			while ((line = in.readLine()) != null) {
				res += line;
			}
			in.close();
		} catch (Exception e) {
			throw e;
		}
		return res;
	}

}
