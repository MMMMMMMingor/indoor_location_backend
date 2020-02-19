package com.scut.indoorLocation.dto;

import com.scut.indoorLocation.entity.AccessPoint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Mingor on 2020/2/19 13:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FingerPrintMetadataRequest {

    private AccessPoint ap1;

    private AccessPoint ap2;

    private AccessPoint ap3;

}
