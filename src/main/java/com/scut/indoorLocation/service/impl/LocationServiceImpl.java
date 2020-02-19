package com.scut.indoorLocation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scut.indoorLocation.dto.FingerPrintMetadataRequest;
import com.scut.indoorLocation.dto.FingerPrintRequest;
import com.scut.indoorLocation.entity.AccessPoint;
import com.scut.indoorLocation.entity.FingerPrint2D;
import com.scut.indoorLocation.entity.FingerPrintMetadata2D;
import com.scut.indoorLocation.exception.CreateException;
import com.scut.indoorLocation.exception.FingerPrintEmptyException;
import com.scut.indoorLocation.mapper.AccessPointMapper;
import com.scut.indoorLocation.mapper.FingerPrint2DMapper;
import com.scut.indoorLocation.mapper.FingerPrintMetadata2DMapper;
import com.scut.indoorLocation.service.LocationService;
import com.scut.indoorLocation.utility.JwtUtil;
import com.scut.indoorLocation.utility.LocationAlgorithmUtil;
import com.scut.point.Vector2D;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Mingor on 2020/2/17 20:35
 */
public class LocationServiceImpl implements LocationService {

    @Resource
    private HttpServletRequest request;

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private LocationAlgorithmUtil locationAlgorithmUtil;

    @Resource
    private FingerPrintMetadata2DMapper fingerPrintMetadata2DMapper;

    @Resource
    private FingerPrint2DMapper fingerPrint2DMapper;

    @Resource
    private AccessPointMapper accessPointMapper;


    @Override
    public void saveFingerPrint(FingerPrint2D fingerPrint) {
        fingerPrint2DMapper.insert(fingerPrint);
    }

    @Override
    public void createFingerPrintMetadata(FingerPrintMetadataRequest metadataRequest) throws FingerPrintEmptyException, CreateException {

        AccessPoint ap1 = metadataRequest.getAp1();
        AccessPoint ap2 = metadataRequest.getAp2();
        AccessPoint ap3 = metadataRequest.getAp3();

        if(ap1 == null || ap2 == null || ap3 == null)
            throw new FingerPrintEmptyException("数据不能有空");

        if(ap1.getBssid().equals(ap2.getBssid()) || ap1.getBssid().equals(ap3.getBssid()) || ap2.getBssid().equals(ap3.getBssid()))
            throw new CreateException("需要独立不同的3个AP");

        // 保存AP信息
        accessPointMapper.insert(ap1);
        accessPointMapper.insert(ap2);
        accessPointMapper.insert(ap3);

        // 获取当前用户的ID
        String uid = jwtUtil.extractUidSubject(request);

        FingerPrintMetadata2D metadata2D = FingerPrintMetadata2D.builder()
                .userId(uid)
                .bssid1(metadataRequest.getAp1().getBssid())
                .bssid2(metadataRequest.getAp2().getBssid())
                .bssid3(metadataRequest.getAp3().getBssid())
                .createTime(LocalDateTime.now())
                .build();

        fingerPrintMetadata2DMapper.insert(metadata2D);

    }


    @Override
    public IPage<FingerPrintMetadata2D> queryMetadataByPage(Long pageNo, Long pageSize) {
        // 获取当前用户的ID
        String uid = jwtUtil.extractUidSubject(request);

        Page<FingerPrintMetadata2D> page = new Page<>(pageNo, pageSize);
        QueryWrapper<FingerPrintMetadata2D> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", uid);
        return fingerPrintMetadata2DMapper.selectPage(page, wrapper);
    }


    @Override
    public Vector2D getPosition2D(FingerPrintRequest request) throws FingerPrintEmptyException {

        String metadataId = request.getMetadataId();

        QueryWrapper<FingerPrint2D> wrapper = new QueryWrapper<FingerPrint2D>().eq("metadata_id", metadataId);
        List<FingerPrint2D> fingerPrint2DHistory = fingerPrint2DMapper.selectList(wrapper);

        FingerPrint2D fingerPrint2D = FingerPrint2D.builder()
                .ap1(request.getAp1())
                .ap2(request.getAp2())
                .ap3(request.getAp3())
                .build();

        return locationAlgorithmUtil.calculatePosition2D(fingerPrint2DHistory, fingerPrint2D);
    }
}
