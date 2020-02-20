package com.scut.indoorLocation.service.impl;

import com.scut.indoorLocation.entity.AccessPoint;
import com.scut.indoorLocation.mapper.AccessPointMapper;
import com.scut.indoorLocation.service.AccessPointService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Mingor on 2020/2/19 19:56
 */
@Service
public class AccessPointServiceImpl implements AccessPointService {

    @Resource
    private AccessPointMapper accessPointMapper;

    @Override
    public AccessPoint queryById(String id) {
        return accessPointMapper.selectById(id);
    }
}
