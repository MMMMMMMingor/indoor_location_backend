package com.scut.indoorLocation.handle;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.concurrent.ExecutionException;

/**
 * 全局错误处理
 * Created by Mingor on 2019/11/30 13:34
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandle {

//    @ExceptionHandler(RequestRejectedException.class)
//    public ResponseEntity<String> executionExceptionHandler(ExecutionException e){
//        log.error("请求方式非法 {}", e.getMessage());
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("执行异常");
//    }
//
//    @ExceptionHandler(InterruptedException.class)
//    public ResponseEntity<String> interruptedExceptionHandler(InterruptedException e){
//        log.error("JWT超时 {}", e.getMessage());
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("中断异常");
//    }

}