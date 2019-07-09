package com.example.KMS.APP.basic.interceptor;

import com.example.KMS.APP.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

/**
 * @Description Controller层日志打印切面（构建日志）
 * @Author cx
 * @Date 2019/6/13 9:20
 * @Version 1.0
 **/
@Component
@ComponentScan(basePackages = "com.example.KMS.APP.controller")
@Aspect
@Slf4j
public class ControllerLogInterceptor {

    @Around("@annotation(org.springframework.web.bind.annotation.PostMapping)" +
            "||@annotation(org.springframework.web.bind.annotation.GetMapping)" +
            "||@annotation(org.springframework.web.bind.annotation.DeleteMapping)" +
            "||@annotation(org.springframework.web.bind.annotation.PutMapping)" +
            "||@annotation(org.springframework.web.bind.annotation.RequestMapping)" +
            ""
    )
    public Object intereptor(ProceedingJoinPoint pjp) throws Throwable {
        long beginTime = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        StringBuilder sb = new StringBuilder();
        sb.append("\r\n=====================================").append("\r\n");
        sb.append("Request Start:" + new Date()).append("\r\n");
        sb.append("Request Url:" + request.getRequestURL().toString()).append("\r\n");
        sb.append("Http Method:" + request.getMethod()).append("\r\n");
        sb.append("IP:" + HttpUtil.getReqIp(request)).append("\r\n");
        sb.append("Class Method:" + pjp.getTarget().getClass().getSimpleName() + "." + pjp.getSignature().getName()).append("\r\n");
        sb.append("Params:" + Arrays.toString(pjp.getArgs())).append("\r\n");
        sb.append("CurrentUser:" + request.getAttribute("当前登陆人员")).append("\r\n");

        Object result = null;
        try {
          result = pjp.proceed();
        } catch (Throwable e) {
            sb.append("Request error:" + e.getMessage()).append("\r\n");
        } finally {
            long costMs = System.currentTimeMillis() - beginTime;
            sb.append("Request Result:" + result).append("\r\n");
            sb.append("Request End:" + new Date() + "used:" + costMs + "ms").append("\r\n");
            sb.append("\r\n=====================================").append("\r\n");
            log.info(sb.toString());
        }

        return result;
    }
}