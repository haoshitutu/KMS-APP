package com.example.KMS.APP.basic.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @Description 请求参数封装
 * @Author cx
 * @Date 2019/6/12 16:09
 * @Version 1.0
 **/
@Data
public class WebRequest<T> {

    @NotNull
    @Valid
    private T body;
}