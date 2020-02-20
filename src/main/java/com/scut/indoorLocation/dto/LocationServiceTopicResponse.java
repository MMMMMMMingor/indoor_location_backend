package com.scut.indoorLocation.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Mingor on 2020/2/20 16:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationServiceTopicResponse {

    @ApiModelProperty(value = "请求状态描述", name = "success", example = "true")
    private Boolean success;

    @ApiModelProperty(value = "定位请求发布topic", name = "sendTopic", example = "36ed8eba21765f8595fe8d45b7892ba6")
    private String sendTopic;

    @ApiModelProperty(value = "定位记过订阅topic", name = "receiveTopic", example = "36ed8eba21765f8595fe8d45b7892ba6")
    private String receiveTopic;

    @ApiModelProperty(value = "请求状态描述", name = "describe", example = "请求成功")
    private String describe;

}
