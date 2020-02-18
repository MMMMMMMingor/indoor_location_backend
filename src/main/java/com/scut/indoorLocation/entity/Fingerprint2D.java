package com.scut.indoorLocation.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.scut.point.IFingerPrint;
import lombok.Builder;
import lombok.Data;

/**
 * Created by Mingor on 2020/2/18 20:02
 */
@Data
@Builder
@TableName("fingerprint_2d")
public class Fingerprint2D implements IFingerPrint {

    @TableId(value = "bssid")
    private String id;

    private String metadataId;

    private Double x;

    private Double y;

    private Integer ap1;

    private Integer ap2;

    private Integer ap3;

    @Override
    public int getAP1() {
        return ap1;
    }

    @Override
    public int getAP2() {
        return ap2;
    }

    @Override
    public int getAP3() {
        return ap3;
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
