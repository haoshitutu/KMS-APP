package com.example.KMS.APP.utils;

import com.example.KMS.APP.basic.constant.Constant;
import com.example.KMS.APP.basic.dto.WebResponse;

/**
 * @Description 接口响应返回封装工具类
 * @Author cx
 * @Date 2019/6/12 16:07
 * @Version 1.0
 **/
public class ResponseUtil {

    public static WebResponse success(Object body, String msg) {
        return new WebResponse(Constant.Y, body, msg);
    }

    public static WebResponse success(String msg) {
        return new WebResponse(Constant.Y, msg);
    }

    public static WebResponse fail(Object body, String msg) {
        return new WebResponse(Constant.N, body, msg);
    }

    public static WebResponse fail(String msg) {
        return new WebResponse(Constant.N, msg);
    }
}