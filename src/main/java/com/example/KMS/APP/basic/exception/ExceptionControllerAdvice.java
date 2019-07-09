package com.example.KMS.APP.basic.exception;

import com.example.KMS.APP.basic.dto.WebResponse;
import com.example.KMS.APP.utils.ResponseUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 全局Controller层异常处理类（Controller层不需要进行try catch）
 * @Author cx
 * @Date 2019/6/12 16:32
 * @Version 1.0
 **/
@RestControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    /**
     * 未知异常统一处理
     * @param ex 异常信息
     * @return 消息
     */
    @ExceptionHandler(value = Exception.class)
    public WebResponse<String> exceptionHandler(BusinessException ex) {
       log.error(String.format("未知异常：%s", ex.getMessage()));
        return ResponseUtil.fail(String.format("未知异常：%s", ex.getMessage()));
    }


    /**
     * 业务处理异常统一处理
     * @param ex 异常信息
     * @return 消息
     */
    @ExceptionHandler(value = BusinessException.class)
    public WebResponse<String> businessExceptionHandler(BusinessException ex) {
        log.error(String.format("业务处理异常：%s", ex.getMessage()));
        return ResponseUtil.fail(String.format("业务处理异常：%s", ex.getMessage()));
    }

    /**
     * http异常统一处理
     * @param ex 异常信息
     * @return 消息
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public WebResponse<String> paramError(HttpMessageNotReadableException ex) {
        log.error(String.format("参数格式不对，请确认再试，%s", ex.getMessage()));
        return ResponseUtil.fail(String.format("参数格式不对，请确认再试，%s", ex.getMessage()));
    }

    /**
     * 请求参数类型异常统一处理
     * @param ex 异常信息
     * @return 消息
     */
    @ExceptionHandler(value = TypeMismatchException.class)
    public WebResponse<String> requestTypeMismatch(TypeMismatchException ex) {
        log.error(String.format("参数类型不匹配，参数：%s，类型应该为：%s", ex.getPropertyName(), ex.getRequiredType()));
        return ResponseUtil.fail(String.format("参数类型不匹配，参数：%s，类型应该为：%s", ex.getPropertyName(), ex.getRequiredType()));
    }

    /**
     * 缺失请求参数异常统一处理
     * @param ex 异常信息
     * @return 消息
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public WebResponse<String> requestMissingServletRequest(MissingServletRequestParameterException ex) {
        log.error(String.format("缺少必要的参数，参数：%s，类型应该为：%s", ex.getParameterName(), ex.getParameterType()));
        return ResponseUtil.fail(String.format("缺少必要的参数，参数：%s，类型应该为：%s", ex.getParameterName(), ex.getParameterType()));
    }

    /**
     * 接口参数校验异常统一处理
     * @param ex 异常信息
     * @return 消息
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public WebResponse<List<ErrorInfo>> requestMissingServletRequest(MethodArgumentNotValidException ex) {
        List<ErrorInfo> errorInfos = new ArrayList<>();

        for (ObjectError oe : ex.getBindingResult().getAllErrors()) {
            if (oe instanceof FieldError) {
                FieldError fe = (FieldError) oe;
                errorInfos.add(new ErrorInfo(fe.getField(), fe.getDefaultMessage()));
            } else {
                errorInfos.add(new ErrorInfo(oe.getObjectName(), oe.getDefaultMessage()));
            }
        }

        return ResponseUtil.fail(errorInfos, "参数错误");
    }

    @Data
    @AllArgsConstructor
    class ErrorInfo {

        private String error;

        private String errorMsg;
    }
}