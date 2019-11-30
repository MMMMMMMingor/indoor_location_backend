package com.scut.indoorLocation.utility;

import com.scut.indoorLocation.dto.Result;

/**
 * Created by Mingor on 2019/11/30 13:36
 */
public class ResultUtil {

    public static <T> Result<T> success(T t) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("请求成功");
        result.setData(t);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setSuccess(false);
        result.setMsg(msg);
        return result;
    }
}