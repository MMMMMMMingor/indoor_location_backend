package com.scut.indoorLocation.dto;

import com.scut.indoorLocation.entity.AccessPoint;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;


/**
 * Created by Mingor on 2020/2/27 21:06
 */
@Data
@Builder
public class FingerPrintMetaDetailResponse {

    @ApiModelProperty(value = "元数据ID", name = "metadataId", example = "36ed8eba21765f8595fe8d45b7892ba6")
    private String metadataId;

    @ApiModelProperty(value = "ap信息", name = "accessPoints", example = "{\"bssid\":\"78:44:fd:61:ce:d6\",\"ssid\":\"3-303_5\",\"x\":25.0,\"y\":20.0}")
    private List<AccessPoint> accessPoints;

    @ApiModelProperty(value = "指纹数", name = "fingerPrints", example = "{\"x\":25.0,\"y\":30.0,\"intensities\":[0,-47,-52],\"createTime\":\"2020-03-13T18:59:42.756\",\"aps\":[0,-47,-52],\"positionX\":25.0,\"positionY\":30.0}")
    private List<FingerPrint2D> fingerPrints;

}
