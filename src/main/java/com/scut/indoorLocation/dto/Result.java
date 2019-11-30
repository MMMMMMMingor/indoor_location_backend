package com.scut.indoorLocation.dto;

import lombok.Data;

/**
 * Created by Mingor on 2019/11/30 13:36
 */
@Data
public class Result<T> {

    /** 状态码、信息说明. */
    private int code;

    /** 信息说明 */
    private String msg;

    /** 提示信息. */
    private Boolean success;

    /** 具体的内容. */
    private T data;
}
