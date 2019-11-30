package com.scut.indoorLocation.dto;

import lombok.Data;

/**
 * Created by Mingor on 2019/11/30 13:36
 */
@Data
public class Result<T> {
    /** 状态码. */
    private Integer code;

    /** 提示信息. */
    private Boolean success;

    /** 信息说明. */
    private String msg;

    /** 具体的内容. */
    private T data;
}
