package com.scut.indoorLocation.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Mingor on 2019/12/31 11:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {

    @ApiModelProperty(value = "店铺ID", name = "storeId", example = "54f300588c849624b9cc4910482afb99")
    private String storeId;

    @ApiModelProperty(value = "评分", name = "score", example = "7")
    private Integer score;

    @ApiModelProperty(value = "评论内容", name = "comment", example = "还行，味道不错")
    private String comment;

}
