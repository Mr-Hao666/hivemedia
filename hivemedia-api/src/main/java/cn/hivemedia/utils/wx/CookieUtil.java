package cn.hivemedia.utils.wx;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class CookieUtil {
    public static String getCookie(String key, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String openid = null;
        for(Cookie cookie : cookies){
            if(StringUtils.equals(key, cookie.getName())){
                openid = cookie.getValue();
                break;
            }
        }
        return openid;
    }
    
    public static void setCookie(String key, String value, HttpServletResponse response){
        Cookie cookie = new Cookie(key, value);     
        cookie.setPath("/");
        cookie.setMaxAge(Integer.MAX_VALUE);     
        response.addCookie(cookie);     
    }
}
