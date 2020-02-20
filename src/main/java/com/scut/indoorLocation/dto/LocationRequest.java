package com.scut.indoorLocation.dto;

import com.scut.point.IFingerPrint;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Mingor on 2020/2/19 13:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationRequest implements IFingerPrint {

    @ApiModelProperty(value = "ap1 信号强度", name = "ap1", example = "6")
    private Integer intensity1;

    @ApiModelProperty(value = "ap2 信号强度", name = "ap2", example = "5")
    private Integer intensity2;

    @ApiModelProperty(value = "ap3 信号强度", name = "ap3", example = "10")
    private Integer intensity3;

    @ApiModelProperty(value = "结束标志", name = "finish", example = "false")
    private Boolean finish;

    @Override
    public int getAP1() {
        return intensity1;
    }

    @Override
    public int getAP2() {
        return intensity2;
    }

    @Override
    public int getAP3() {
        return intensity3;
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
