package com.scut.indoorLocation.dto;

import com.scut.indoorLocation.entity.AccessPoint;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Mingor on 2020/2/19 13:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FingerPrintMetadataRequest {

    @ApiModelProperty(value = "备注名", name = "remark", example = "华工生活区C10")
    private String remark;

    private List<AccessPoint> accessPoints;

}
