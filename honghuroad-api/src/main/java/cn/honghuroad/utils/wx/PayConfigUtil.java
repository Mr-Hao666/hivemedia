package cn.honghuroad.utils.wx;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class PayConfigUtil {

    public static final String APP_ID = "wx627f64c615165a25";//微信给
    
    public static final String MCH_ID = "1515446811";//微信给
    
    public static final String API_KEY = "35269bb7f5a3d55b3968edad1810f5fc"; //签名使用,微信给
    
    public static final String CREATE_IP = "127.0.0.1";
    
//    public static final String NOTIFY_URL = "http://" + getAccessDomain() + "/wx_pay_notify";
	// 回掉url
    public static final String NOTIFY_URL =  "hhttps://dev.api.honghuroad.com/honghuroad-api/api/pay/weixinPayNotify";
    
    public static final String UFDODER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    //退款url
    public static final String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";

	public static String getAccessDomain() {
		Resource resource = new ClassPathResource("sys_config.properties");
		Properties props = null;
		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String projectUrl = props.getProperty("project_url");
		if (!StringUtils.startsWith(projectUrl, "http://")) {
			projectUrl = "http://" + projectUrl;
		}
		if (StringUtils.endsWith(projectUrl, "/")) {
			projectUrl = projectUrl.substring(0, projectUrl.length() - 1);
		}
		return projectUrl;
	}

}
