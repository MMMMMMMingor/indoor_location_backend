package com.scut.indoorLocation.service;

import com.scut.point.IFingerPrint;
import com.scut.point.Vector2D;

/**
 * Created by Mingor on 2020/2/17 20:33
 */
public interface LocationService {

    /**
     * 计算用户的2D坐标
     * @param fingerPrint 当前指纹信息
     * @return 2D坐标
     */
    Vector2D getPosition2D(IFingerPrint fingerPrint);

}
