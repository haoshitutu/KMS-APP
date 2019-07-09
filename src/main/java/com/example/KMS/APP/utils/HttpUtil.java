package com.example.KMS.APP.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description http 工具类
 * @Author cx
 * @Date 2019/6/13 10:37
 * @Version 1.0
 **/
public class HttpUtil {

    public static String getReqIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unkonwn".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unkonwn".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unkonwn".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.indexOf(",") != -1) {
            String[] ipStr =  ip.split(",");
            ip = ipStr[0];
        }
        return ip;
    }
}