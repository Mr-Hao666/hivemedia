package cn.hivemedia.config;

public class AlipayConfig {

    // 1.商户appid
    public static String APPID = "2019011262880311";

    //2.私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY ="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDLjMfGrn+t4TrpXmJXF0LrTcKpSihsq5PxYQM+rcgVguRHLp6ZFKB3yRadZh7HGsHl2X0KS9IiHw6okznEp12BCsCAooXQsO9B7npBgMOnRYwaJznQWxTH24hx7MBYEIofPm0PEI0+1aRIgM90ZeEe0MO2mdXWM1hhHcJ54g3E2h/DbMcIBPA4MCwmJnJDWSi1eyBIYl5bHciInohuLDACED2ApqrEi7pCF30uw6Y5MATjPBduVjr+EZXN13BTQMeRaY9WyDjIyN5u5TZLnGWkzr0piMkvvaXMR+4suj31kNJBx/d2O9n8LPN4hcLQ6zYD0gzmiGH1Gmk5NRi0g9eRAgMBAAECggEANQOkoJWgQXxQ6KkhecdgMYLeOVNMNgl2Db8TpkaCvz+JDxUQT5R0ut4P2szXJUpNx/yeOYlH4IUwPy2hNRvWGZ7gRmCN862xkv12sArGapGNjN48i4y9W9RMtEdSTV15a+vJYcwQzZW9JPkPjyRdvrXm0287p39V/G1Q0ofSyDsEc57Tn1UaSoLwnVcYZUVqAo0hdvTpdLXfMePp9PYfx77l7itlEtKFwhcTB1cw6ebHdiYCKpc7hVL81Lm7IMBxI+6UDqGPjLOqddvSvIyDE3kOvIeeSltG86bAW1uQXtWi6gpQMmqxH2dEJ5MXORQq3T0s7rL87oLApdK6DeOoAQKBgQDlsY8/r17PYGEDoeIfCI5VLvHoiZm8Vs+LuFwMEYU7OnrB0ViGtTe1axfKzNIhV1ZUcVXD7+fJ53k0jOVYZmeMO9SMqD4j44tqkn6Hdpx6miMRfHu53Aiw+c5HERxkXjbwA53M8fXMq5+npGmp2Y11RJXLeLGXTHnt31u6QI2jEQKBgQDi3LVcTL6j/akbSMY9xu+crbVi8CLmO7ApHBpLJ9AspiDZVLpNa8RBPg1fhxcOHLEhHFB6FvCIRElO8gxMDQK3vJea8+z13Fun8OJRLeOIWvuL6qCVGBTdFNxPJK0yawg1bSKHUCR2qKi4Fg1T3FWFGBUnQm+oAYZDVwny+8HsgQKBgADAAa29jtuyft6aKrjbOEvC6XtO57ebuPXF1lcbv4mf+UKkwls+QD5RaTR6Ur1VIa0I6bTIFcOtA9JOG3mca/4jO+BxtDI9zfi9HbinhH9J/Y9TDrxeoZCiYDAPOD6DqD5EZMGEkL7oWI8dlFK/qRyW6r5NWJcb4tIKBqynBU9hAoGBAN1JGAaePV7M8QTSKbzcraEQt9t4DTKyihSvet5QSXkj2urjAI7ATcCt/HFv3gc/6W84pS9R7PGeWjuNUn1kO4Bpjx/wSvVPKoBYMQmqT0UUKkXCaTzFLqSv0m3IgsCpMmqat5FxlP/BaEGYEQv54dlfMlMHNM6WHhbVcbj35aMBAoGAcqFKAyLHzPISD/nvw8Scq7wch+Zw+dr/5BqZQlPI3dfN1byc3jqNRT8g6XoIuLBfy+D7NukVJp8Q4Ii5WCTQ2Us75RnFTippcavlTBhkDor3iuRBqzcyjS1Oz7Ad3zLrIauEOdfd82Fgx+TwRAAkxzuCoq3HtAxK0CFCIh+epaA=";

    // 3.支付宝公钥
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmPHXFkm7jF3m/YG82WAfXd1CeFjOgraCX2fW3O3EL9Lmyf9jliU0r0bPDj49F8Rw86dD4E2KBq7n/Yel2+gMe7+q2XG7OWk/d8urpnJllWiepilZohg+RywBxZmbPZ0O3dRwzZQriGYZchgvxM7dbUur7dEoxL23pfr4Un7luPNw7DAAckiMZyIIDp+rEsNdwUzScdPVLJ9AEvyw0g+M2jwbPFM+r/2/wdX3aUleXERn/m/cmQYS/7goT/EtEoSWpdYZviYbLlYzvEUorKQt09/YD+8IL9I2LvacRdffy6jpr1Qhw/v7aRqBwt1dUdNOH3AyLVZUf5SEJSgx2jUAXQIDAQAB";

    // 4.服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "https://dev.api.hivemedia.com/hivemedia-api/api/pay/aliPayNotify";

    //5.页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    // public static String return_url = "http://www.xxx.com/alipay/return_url.do";

    // 6.请求支付宝的网关地址
    public static String URL = "https://openapi.alipay.com/gateway.do";

    // 7.编码
    public static String CHARSET = "UTF-8";

    // 8.返回格式
    public static String FORMAT = "json";

    // 9.加密类型
    public static String SIGNTYPE = "RSA2";

}
