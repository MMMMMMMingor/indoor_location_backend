package com.scut.indoorLocation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scut.indoorLocation.dto.FingerPrintMetaDetailResponse;
import com.scut.indoorLocation.dto.FingerPrintMetadataRequest;
import com.scut.indoorLocation.dto.LocationServiceTopicResponse;
import com.scut.indoorLocation.entity.AccessPoint;
import com.scut.indoorLocation.entity.FingerPrint2D;
import com.scut.indoorLocation.entity.FingerPrintMetadata2D;
import com.scut.indoorLocation.exception.CreateException;
import com.scut.indoorLocation.exception.FingerPrintAuthorizationException;
import com.scut.indoorLocation.exception.FingerPrintEmptyException;
import com.scut.indoorLocation.exception.NotOwnerException;
import com.scut.indoorLocation.mapper.AccessPointMapper;
import com.scut.indoorLocation.mapper.FingerPrint2DMapper;
import com.scut.indoorLocation.mapper.FingerPrintMetadata2DMapper;
import com.scut.indoorLocation.service.LocationService;
import com.scut.indoorLocation.utility.JwtUtil;
import com.scut.indoorLocation.utility.LocationAlgorithmUtil;
import com.scut.indoorLocation.utility.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Mingor on 2020/2/17 20:35
 */
@Service
public class LocationServiceImpl implements LocationService {

    @Resource
    private HttpServletRequest request;

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private UUIDUtil uuidUtil;

    @Resource
    private LocationAlgorithmUtil locationAlgorithmUtil;

    @Resource
    private FingerPrintMetadata2DMapper fingerPrintMetadata2DMapper;

    @Resource
    private FingerPrint2DMapper fingerPrint2DMapper;

    @Resource
    private AccessPointMapper accessPointMapper;

    @Override
    public String collectFingerPrint(String metadataId) throws FingerPrintAuthorizationException {

        // 获取当前用户的ID
        String uid = jwtUtil.extractUidSubject(request);

        FingerPrintMetadata2D fingerPrintMetadata2D = fingerPrintMetadata2DMapper.selectById(metadataId);

        if (!fingerPrintMetadata2D.getUserId().equals(uid))
            throw new FingerPrintAuthorizationException("该用户没有权限操作本指纹库信息");

        String tmpTopic = uuidUtil.get32LengthString();

        locationAlgorithmUtil.collectFingerPrint(tmpTopic, metadataId);

        return tmpTopic;
    }

    @Override
    public void createFingerPrintMetadata(FingerPrintMetadataRequest metadataRequest) throws FingerPrintEmptyException, CreateException {

        String metaId = uuidUtil.get32LengthString();

        AccessPoint ap1 = metadataRequest.getAp1();
        AccessPoint ap2 = metadataRequest.getAp2();
        AccessPoint ap3 = metadataRequest.getAp3();

        if (ap1 == null || ap2 == null || ap3 == null)
            throw new FingerPrintEmptyException("数据不能有空");

        if (ap1.getBssid().equals(ap2.getBssid()) || ap1.getBssid().equals(ap3.getBssid()) || ap2.getBssid().equals(ap3.getBssid()))
            throw new CreateException("需要独立不同的3个AP");

        ap1.setMetaId(metaId);
        ap2.setMetaId(metaId);
        ap3.setMetaId(metaId);

        // 保存AP信息
        accessPointMapper.insert(ap1);
        accessPointMapper.insert(ap2);
        accessPointMapper.insert(ap3);

        // 获取当前用户的ID
        String uid = jwtUtil.extractUidSubject(request);

        FingerPrintMetadata2D metadata2D = FingerPrintMetadata2D.builder()
                .metaId(metaId)
                .userId(uid)
                .bssid1(metadataRequest.getAp1().getBssid())
                .bssid2(metadataRequest.getAp2().getBssid())
                .bssid3(metadataRequest.getAp3().getBssid())
                .createTime(LocalDateTime.now())
                .build();

        fingerPrintMetadata2DMapper.insert(metadata2D);
    }

    @Override
    public void deleteFingerPrintMetadata(String metadataId) throws NotOwnerException {
        // 获取当前用户的ID
        String uid = jwtUtil.extractUidSubject(request);

        FingerPrintMetadata2D fingerPrintMetadata2D = fingerPrintMetadata2DMapper.selectById(metadataId);

        if (!uid.equals(fingerPrintMetadata2D.getUserId()))
            throw new NotOwnerException("权限错误");

        fingerPrintMetadata2DMapper.deleteById(metadataId);
    }

    @Override
    public FingerPrintMetaDetailResponse queryMetaById(String metadataId) {

        FingerPrintMetadata2D metadata2D = fingerPrintMetadata2DMapper.selectById(metadataId);

        QueryWrapper<AccessPoint> wrapper = new QueryWrapper<>();
        wrapper.eq("meta_id", metadataId);
        List<AccessPoint> accessPoints = accessPointMapper.selectList(wrapper);

        FingerPrintMetaDetailResponse response = new FingerPrintMetaDetailResponse();

        response.setMetadataId(metadataId);

        for (AccessPoint ap : accessPoints) {
            if (ap.getBssid().equals(metadata2D.getBssid1()))
                response.setAp1(ap);

            if (ap.getBssid().equals(metadata2D.getBssid2()))
                response.setAp2(ap);

            if (ap.getBssid().equals(metadata2D.getBssid3()))
                response.setAp3(ap);
        }


        return response;
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
    public LocationServiceTopicResponse getPosition2D(String metadataId) throws FingerPrintEmptyException {

        QueryWrapper<FingerPrint2D> wrapper = new QueryWrapper<FingerPrint2D>().eq("metadata_id", metadataId);
        List<FingerPrint2D> fingerPrint2DHistory = fingerPrint2DMapper.selectList(wrapper);

        if (fingerPrint2DHistory == null || fingerPrint2DHistory.size() == 0)
            throw new FingerPrintEmptyException("没有指纹库信息");

        String sendTopic = uuidUtil.get32LengthString();
        String receiveTopic = uuidUtil.get32LengthString();

        locationAlgorithmUtil.calculatePosition2D(fingerPrint2DHistory, sendTopic, receiveTopic);

        return new LocationServiceTopicResponse(true, sendTopic, receiveTopic, "请求成功");
    }
}
