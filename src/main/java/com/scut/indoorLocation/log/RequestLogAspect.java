package com.scut.indoorLocation.log;

import com.alibaba.fastjson.JSON;
import com.scut.indoorLocation.utility.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mingor on 2020/3/13 15:15
 */
@Component
@Aspect
@Slf4j
public class RequestLogAspect {

    @Resource
    private JwtUtil jwtUtil;

    @Pointcut("execution(public * com.scut.indoorLocation.controller..*(..))")
    public void requestServer() {
    }

    @Before("requestServer()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();

        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();

            log.info("--------------------------------------------------------");
            log.info("IP                 : {}", request.getRemoteAddr());
            log.info("URL                : {}", request.getRequestURL().toString());
            log.info("HTTP Method        : {}", request.getMethod());
            log.info("Class Method       : {}.{}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName());
            log.info("User Id            : {}", jwtUtil.extractUidSubject(request));

        }

    }


    @Around("requestServer()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        log.info("Request Params     : {}", getRequestParams(proceedingJoinPoint));
        log.info("Result             : {}", JSON.toJSON(result));
        log.info("Time Cost          : {} ms", System.currentTimeMillis() - start);

        return result;
    }

    @After("requestServer()")
    void doAfter() {
        log.info("--------------------------------------------------------");
    }

    /**
     * 获取入参
     *
     * @param proceedingJoinPoint 处理请求参数
     * @return requestParams
     */
    private Map<String, Object> getRequestParams(ProceedingJoinPoint proceedingJoinPoint) {
        Map<String, Object> requestParams = new HashMap<>();

        //参数名
        String[] paramNames =
                ((MethodSignature) proceedingJoinPoint.getSignature()).getParameterNames();
        //参数值
        Object[] paramValues = proceedingJoinPoint.getArgs();

        for (int i = 0; i < paramNames.length; i++) {
            Object value = paramValues[i];

            //如果是文件对象
            if (value instanceof MultipartFile) {
                MultipartFile file = (MultipartFile) value;
                value = file.getOriginalFilename();  //获取文件名
            }

            requestParams.put(paramNames[i], value);
        }

        return requestParams;
    }
}

