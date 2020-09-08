package com.example.KMS.APP.basic.exception;


/**
 * @Description 业务异常类
 * @Author cx
 * @Date 2019/6/12 16:22
 * @Version 1.0
 **/
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -2694583438869254141L;

    public BusinessException() { }

    public BusinessException(String msg) {
        super(msg);
    }
}