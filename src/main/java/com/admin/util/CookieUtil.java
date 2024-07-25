package com.admin.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.SerializationUtils;

import java.util.Base64;

public class CookieUtil {

    // 요청값(이름, 값, 만료 기간)
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge, boolean httpOnly) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        cookie.setHttpOnly(httpOnly);
        response.addCookie(cookie);
    }

    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            return;
        }
        for(Cookie cookie : cookies){
            if(name.equals(cookie.getName())){
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }

    public static String getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie c : cookies) {
                if(c.getName().equals(cookieName)){
                    return c.getValue();
                }
            }
        }

        return null;
    }

    // 객체 직렬화하여 쿠키 값으로 변환
    public static String serialize(Object obj){
        return Base64.getUrlEncoder()
                .encodeToString(SerializationUtils.serialize(obj));
    }

    // 쿠키 역직렬화하여 객체 값으로 변환
    public static <T> T deserialize(Cookie cookie, Class<T> cls){
        return cls.cast(
                SerializationUtils.deserialize(
                       Base64.getUrlDecoder().decode(cookie.getValue())
                )
        );
    }
}
