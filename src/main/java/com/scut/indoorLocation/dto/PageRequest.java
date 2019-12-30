package com.scut.indoorLocation.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页查询
 * Created by Mingor on 2019/12/30 22:33
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequest {

    @ApiModelProperty(value = "页号", name = "pageNO", example = "1")
    private Long pageNO;

    @ApiModelProperty(value = "页大小", name = "pageSize", example = "5")
    private Long pageSize;

}
