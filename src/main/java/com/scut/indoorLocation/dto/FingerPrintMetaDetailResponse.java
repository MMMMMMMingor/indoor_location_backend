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

    @ApiModelProperty(value = "ap信息", name = "accessPoints", example = "null")
    private List<AccessPoint> accessPoints;

    @ApiModelProperty(value = "指纹数", name = "count", example = "10")
    private List<FingerPrint2D> fingerPrintS;

}
