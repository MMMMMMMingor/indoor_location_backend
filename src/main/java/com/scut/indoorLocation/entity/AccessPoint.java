package com.scut.indoorLocation.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * Created by Mingor on 2020/2/17 20:47
 */
@Data
@Builder
@TableName("access_point")
public class AccessPoint {

    @TableId(value = "bssid")
    private String bssid;

    private String ssid;

    private Double x;

    private Double y;
}
