package com.scut.indoorLocation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Mingor on 2020/2/19 13:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FingerPrintRequest {

    private String metadataId;

    private Integer ap1;

    private Integer ap2;

    private Integer ap3;

}
