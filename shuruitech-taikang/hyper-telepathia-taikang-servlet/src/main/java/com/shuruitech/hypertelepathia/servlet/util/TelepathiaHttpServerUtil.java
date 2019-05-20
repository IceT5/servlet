package com.shuruitech.hypertelepathia.servlet.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author yangyuguang
 * @date 2019.4.23
 */
public class TelepathiaHttpServerUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(TelepathiaHttpServerUtil.class);
    /**
     * get the real request's ipAddress
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    /**
     * url
     * @param request
     * @return
     */
    public static String getUrl(HttpServletRequest request) {
        return request.getRequestURI();
    }
    
    public static void log(HttpServletRequest request,HttpServletResponse response) {
        StringBuffer buffer = new StringBuffer();
        String ipAddress = TelepathiaHttpServerUtil.getIpAddress(request);
        String url = TelepathiaHttpServerUtil.getUrl(request);
        buffer.append(ipAddress + " ").append(url+" ").append(response.getStatus());
        LOGGER.info(buffer.toString());
    }
}
