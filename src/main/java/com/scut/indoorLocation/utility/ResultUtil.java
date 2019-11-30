package com.scut.indoorLocation.utility;

import com.scut.indoorLocation.dto.Result;
import com.scut.indoorLocation.enumeration.CodeEnum;

/**
 * 接口返回处理工具类
 * Created by Mingor on 2019/11/30 13:36
 */
public class ResultUtil {

    /**
     * 有返回值的成功请求
     * @param data 返回数据
     * @param <T> 返回数据类型
     * @return 包装后的数据
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(CodeEnum.SUCCESS.getCode());
        result.setMsg(CodeEnum.SUCCESS.getMsg());
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    /**
     * 无返回值的成功请求
     * @return 包装后的数据
     */
    public static Result success() {
        return success(null);
    }

    /**
     * 有返回值的错误请求
     * @param code 状态码
     * @return 包装后的数据
     */
    public static Result error(CodeEnum code) {
        Result result = new Result();
        result.setCode(code.getCode());
        result.setMsg(code.getMsg());
        result.setSuccess(false);
        return result;
    }

    /**
     * 无返回值的错误请求
     * @return 包装后的数据
     */
    public static Result error() {
        Result result = new Result();
        result.setSuccess(false);
        return result;
    }

}