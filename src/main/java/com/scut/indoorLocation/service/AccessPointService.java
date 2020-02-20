package com.scut.indoorLocation.service;

import com.scut.indoorLocation.entity.AccessPoint;

/**
 * Created by Mingor on 2020/2/19 19:55
 */
public interface AccessPointService {

    /**
     * 查询AP信息
     * @param id AP ID
     * @return AP信息
     */
    AccessPoint queryById(String id);

}
