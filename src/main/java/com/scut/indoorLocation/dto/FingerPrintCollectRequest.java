package com.scut.indoorLocation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Mingor on 2020/2/20 16:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FingerPrintCollectRequest {

    private Double x;

    private Double y;

    private int[] intensities;

    private Boolean finish;
}
