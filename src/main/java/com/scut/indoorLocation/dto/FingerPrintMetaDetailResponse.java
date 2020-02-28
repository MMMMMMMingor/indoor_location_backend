package com.scut.indoorLocation.dto;

import com.scut.indoorLocation.entity.AccessPoint;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


/**
 * Created by Mingor on 2020/2/27 21:06
 */
public class FingerPrintMetaDetailResponse {

    @ApiModelProperty(value = "元数据ID", name = "metadataId", example = "36ed8eba21765f8595fe8d45b7892ba6")
    @Getter
    @Setter
    private String metadataId;

    @Getter
    @Setter
    private AccessPoint ap1;

    @Getter
    @Setter
    private AccessPoint ap2;

    @Getter
    @Setter
    private AccessPoint ap3;
}
