package cc.insist.manage.utils;

import cc.insist.manage.constants.CommonConstant;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.util.Random;

/**
 * @Author: cc
 * @Date: 2023/10/7 3:59 PM
 * @Description:
 */
@Slf4j
public class CommonUtil {
    private static final String LOCALHOST = "127.0.0.1";
    private static final String UNKNOWN = "unknown";
    private static final Integer FIFTEEN = 15;

    /**
     * 获取ip
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress = StringUtils.EMPTY;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || UNKNOWN.equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (LOCALHOST.equals(ipAddress)) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > FIFTEEN) {
                // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(CommonConstant.COMMA_EN) > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(CommonConstant.COMMA_EN));
                }
            }
        } catch (Exception e) {
            ipAddress = StringUtils.EMPTY;
        }
        return ipAddress;
    }

    /**
     * 获取随机长度的串
     *
     * @param length
     * @return
     */
    private static final String ALL_CHAR_NUM = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String getStringNumRandom(int length) {
        //生成随机数字和字母,
        Random random = new Random();
        StringBuilder saltString = new StringBuilder(length);
        for (int i = 1; i <= length; ++i) {
            saltString.append(ALL_CHAR_NUM.charAt(random.nextInt(ALL_CHAR_NUM.length())));
        }
        return saltString.toString();
    }


    /**
     * MD5加密
     *
     * @param data
     * @return
     */
    public static String md5(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100), 1, 3);
            }
            return sb.toString().toUpperCase();
        } catch (Exception exception) {
            log.info("MD5加密错误，msg={}", exception.getMessage());
        }
        return data;
    }

    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 获取验证码随机数
     *
     * @param length
     * @return
     */
    public static String getRandomCode(int length) {

        String sources = "0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < length; j++) {
            sb.append(sources.charAt(random.nextInt(9)));
        }
        return sb.toString();
    }

    /**
     * 响应json数据给前端
     *
     * @param response
     * @param obj
     */
    public static void sendJsonMessage(HttpServletResponse response, Object obj) {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json; charset=utf-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.print(objectMapper.writeValueAsString(obj));
            response.flushBuffer();
        } catch (IOException e) {
            log.warn("响应json数据给前端异常:{}", e);
        }

    }

}
