package com.scut.indoorLocation.dto;

import com.scut.point.IFingerPrint;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by Mingor on 2020/2/19 13:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationRequest implements IFingerPrint {

    @ApiModelProperty(value = "信号强度列表", name = "intensities", example = "[1,2,3,4,5]")
    private List<Integer> intensities;

    @ApiModelProperty(value = "是否结束", name = "finish", example = "false")
    private boolean finish;

    @Override
    public int[] getAPS() {
        return intensities.stream().mapToInt(value -> value).toArray();
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
