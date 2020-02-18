package com.scut.indoorLocation.service.impl;

import com.scut.indoorLocation.entity.Fingerprint2D;
import com.scut.indoorLocation.service.LocationService;
import com.scut.indoorLocation.utility.LocationAlgorithmUtil;
import com.scut.point.IFingerPrint;
import com.scut.point.Vector2D;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Mingor on 2020/2/17 20:35
 */
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationAlgorithmUtil locationAlgorithmUtil;


    @Override
    public Vector2D getPosition2D(IFingerPrint fingerPrint) {
        return locationAlgorithmUtil.calculatePosition2D(fingerPrint);
    }
}
