package com.example.KMS.APP.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description 时间处理工具类
 * @Author tutu
 * @Date 2020/8/13
 * @Version 1.0
 **/
public class TimeUtil {

    /**
     * 获取当前日期
     * @return 返回当前日期，格式为：2020-08-13
     */
    public static String getNowDate() {
        return getNowTime("YYYY-MM-dd");
    }

    /**
     * 获取当前日期时间
     * @return 返回当前日期，格式为：2020-08-13 12:22:33
     */
    public static String getNowDateTime() {
        return getNowTime("YYYY-MM-dd HH:mm:ss");
    }

    /**
     * 获取当前时间
     * @param reg 时间返回的格式
     */
    public static String getNowTime(String reg) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(reg);
        return dateFormat.format(date);
    }
}
