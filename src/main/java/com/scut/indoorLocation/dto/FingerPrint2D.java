package com.scut.indoorLocation.dto;

import com.scut.point.IFingerPrint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Mingor on 2020/2/18 20:02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FingerPrint2D implements IFingerPrint {

    private String metadataId;

    private Double x;

    private Double y;

    private int[] intensities;

    private LocalDateTime createTime;

    @Override
    public int[] getAPS() {
        return intensities;
    }

    @Override
    public double getPositionX() {
        return x;
    }

    @Override
    public double getPositionY() {
        return y;
    }

}
