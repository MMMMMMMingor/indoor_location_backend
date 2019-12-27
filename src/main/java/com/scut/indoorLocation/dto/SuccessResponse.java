package com.scut.indoorLocation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 无返回值response，描述是否成功
 * Created by Mingor on 2019/12/26 20:39
 */
@Data
@AllArgsConstructor
public class SuccessResponse {

    private Boolean success;

    private String message;

}
