package com.scut.indoorLocation.enumeration;


/**
 * 返回码
 * Created by Mingor on 2019/11/30 14:24
 */
public enum CodeEnum {
    SUCCESS(200, "成功"),
    PARAMETER_ERROR(400, "参数错误"),
    UNKNOWN_ERROR(500, "未知错误"),
    ;

    private Integer code;

    private String msg;

    CodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
