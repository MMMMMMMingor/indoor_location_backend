package com.scut.indoorLocation.handle;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局错误处理
 * Created by Mingor on 2019/11/30 13:34
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandle {

//    @ExceptionHandler(ExpiredJwtException.class)
//    public ResponseEntity<String> jwtOutOfTimeHandler(ExpiredJwtException e){
//        log.error("JWT超时 {}", e.getMessage());
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("JWT过期，请从新认证");
//    }


}