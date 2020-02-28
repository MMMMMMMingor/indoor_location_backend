package com.scut.indoorLocation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;


/**
 * Created by Mingor on 2020/2/17 20:47
 */
@Data
@Builder
@TableName("access_point")
public class AccessPoint {

    @JsonIgnore
    @TableId(value = "meta_id")
    private String metaId;

    @TableId(value = "bssid")
    @ApiModelProperty(value = "bssid（mac地址）", name = "bssid", example = "00-01-6C-06-A6-29")
    private String bssid;

    @ApiModelProperty(value = "wifi名称", name = "ssid", example = "华工学生生活区")
    private String ssid;

    @ApiModelProperty(value = "x轴坐标", name = "x", example = "5")
    private Double x;

    @ApiModelProperty(value = "y轴坐标", name = "y", example = "5")
    private Double y;

}
