package com.scut.indoorLocation.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 无返回值response，描述是否成功
 * Created by Mingor on 2019/12/26 20:39
 */
@Data
@AllArgsConstructor
public class SuccessResponse {

    @ApiModelProperty(value = "请求是否成功", name = "success", example = "true")
    private Boolean success;

    @ApiModelProperty(value = "描述信息", name = "message", example = "请求成功")
    private String message;

}
