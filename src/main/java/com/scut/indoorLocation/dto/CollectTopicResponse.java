package com.scut.indoorLocation.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Mingor on 2020/2/20 16:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectTopicResponse {

    @ApiModelProperty(value = "请求状态描述", name = "success", example = "true")
    private Boolean success;

    @ApiModelProperty(value = "mqtt发布topic, 发送的数据将被服务器存储", name = "topic", example = "36ed8eba21765f8595fe8d45b7892ba6")
    private String topic;

    @ApiModelProperty(value = "请求状态描述", name = "describe", example = "请求成功")
    private String describe;

}
