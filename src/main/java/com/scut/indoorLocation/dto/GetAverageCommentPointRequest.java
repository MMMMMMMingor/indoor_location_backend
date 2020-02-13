package com.scut.indoorLocation.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by YellowBroke on 2020/2/12 22：55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAverageCommentPointRequest {

    @ApiModelProperty(value = "店铺ID", name = "storeId", example = "54f300588c849624b9cc4910482afb99")
    private String storeId;

}
