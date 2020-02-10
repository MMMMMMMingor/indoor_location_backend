package com.scut.indoorLocation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Created by Mingor on 2020/1/8 21:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MqttPayload {

    private String msg;

}
