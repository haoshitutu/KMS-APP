package com.example.KMS.APP.basic.dto;

import lombok.Data;

/**
 * @Description 响应数据封装
 * @Author cx
 * @Date 2019/6/12 16:12
 * @Version 1.0
 **/
@Data
public class WebResponse<T> {

    private String code;

    private String msg;

    private T body;

    //构造方法
    public WebResponse() { }

    public WebResponse(T body) {
        this.body = body;
    }

    public WebResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public WebResponse(String code, T body, String msg) {
        this.code = code;
        this.body = body;
        this.msg = msg;
    }
}