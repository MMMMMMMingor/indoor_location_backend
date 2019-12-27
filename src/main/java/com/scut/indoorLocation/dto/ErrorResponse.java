package com.scut.indoorLocation.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 统一错误返回
 * Created by Mingor on 2019/12/27 15:59
 */
@Data
@Builder
public class ErrorResponse {

    private String timestamp;

    private Integer status;

    private String error;

    private String message;

}
