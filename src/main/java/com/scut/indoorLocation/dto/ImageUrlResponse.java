package com.scut.indoorLocation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Mingor on 2019/12/30 10:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageUrlResponse {

    private Boolean success;

    private String imageUrl;

    private String describe;
}
