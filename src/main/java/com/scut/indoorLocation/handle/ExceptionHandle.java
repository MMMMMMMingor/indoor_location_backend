package com.scut.indoorLocation.handle;

import com.scut.indoorLocation.dto.Result;
import com.scut.indoorLocation.utility.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Mingor on 2019/11/30 13:34
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandle {

    /**
     * 请求接口参数错误
     * @param e MethodArgumentNotValidException
     * @return Result
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Result handleParameterError(MethodArgumentNotValidException e) {
        log.error("【请求参数错误】: {}", e.getMessage());
        return ResultUtil.error(-1, "参数错误");
    }


    /**
     * 处理所谓未知错误
     * @param e Exception
     * @return Result
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handleSystemError(Exception e) {
        log.error("【系统异常】: ", e);
        return ResultUtil.error(-1, "参数错误");
    }

}