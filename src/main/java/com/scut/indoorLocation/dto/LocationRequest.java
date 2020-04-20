package com.scut.indoorLocation.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.scut.point.IFingerPrint;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Mingor on 2020/2/19 13:14
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocationRequest implements IFingerPrint {

    @ApiModelProperty(value = "信号强度列表", name = "intensities", example = "[1,2,3,4,5]")
    private int[] intensities;

    @JsonIgnore
    private boolean finished = false;

    @Override
    public int[] getAPS() {
        return intensities;
    }

    @Override
    public double getPositionX() {
        return 0;
    }

    @Override
    public double getPositionY() {
        return 0;
    }
}
