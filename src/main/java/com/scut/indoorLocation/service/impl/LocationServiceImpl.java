package com.scut.indoorLocation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scut.indoorLocation.dto.FingerPrintMetaDetailResponse;
import com.scut.indoorLocation.dto.FingerPrintMetadataRequest;
import com.scut.indoorLocation.entity.AccessPoint;
import com.scut.indoorLocation.entity.FingerPrintMetadata2D;
import com.scut.indoorLocation.exception.CreateException;
import com.scut.indoorLocation.exception.FingerPrintAuthorizationException;
import com.scut.indoorLocation.exception.NotOwnerException;
import com.scut.indoorLocation.mapper.AccessPointMapper;
import com.scut.indoorLocation.mapper.FingerPrintMetadata2DMapper;
import com.scut.indoorLocation.service.LocationService;
import com.scut.indoorLocation.utility.JwtUtil;
import com.scut.indoorLocation.utility.LevelDBUtil;
import com.scut.indoorLocation.utility.LocationAlgorithmUtil;
import com.scut.indoorLocation.utility.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

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
    private LevelDBUtil levelDBUtil;

    @Resource
    private LocationAlgorithmUtil locationAlgorithmUtil;

    @Resource
    private FingerPrintMetadata2DMapper fingerPrintMetadata2DMapper;

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
    public void createFingerPrintMetadata(FingerPrintMetadataRequest metadataRequest) throws CreateException {

        if (metadataRequest.getAccessPoints() == null || metadataRequest.getAccessPoints().size() < 3)
            throw new CreateException("AP数量不应该小于3个");

        Set<String> set = metadataRequest.getAccessPoints().stream().map(AccessPoint::getBssid).collect(Collectors.toSet());

        if (set.size() != metadataRequest.getAccessPoints().size())
            throw new CreateException("AP存在重复");

        String metaId = uuidUtil.get32LengthString();

        // 获取当前用户的ID
        String uid = jwtUtil.extractUidSubject(request);

        FingerPrintMetadata2D metadata2D = FingerPrintMetadata2D.builder()
                .metaId(metaId)
                .userId(uid)
                .remark(metadataRequest.getRemark())
                .accessPoints(new ArrayList<>())
                .fingerPrint2DList(new ArrayList<>())
                .createTime(LocalDateTime.now())
                .build();

        // 插入MySQL数据库
        fingerPrintMetadata2DMapper.insert(metadata2D);

        // 按照bssid排序
        metadataRequest.getAccessPoints().sort((a, b) -> {
            String s1 = a.getBssid().replace("-", "");
            String s2 = b.getBssid().replace("-", "");
            return s1.compareTo(s2);
        });

        // 保存AP数据
        for (AccessPoint ap : metadataRequest.getAccessPoints()) {
            ap.setMetaId(metaId);
            metadata2D.getAccessPoints().add(ap);
            accessPointMapper.insert(ap);
        }

        // 插入levelDB数据库
        levelDBUtil.put(metaId, metadata2D);

    }

    @Override
    public void deleteFingerPrintMetadata(String metadataId) throws NotOwnerException {
        // 获取当前用户的ID
        String uid = jwtUtil.extractUidSubject(request);

        FingerPrintMetadata2D fingerPrintMetadata2D = fingerPrintMetadata2DMapper.selectById(metadataId);

        if (!uid.equals(fingerPrintMetadata2D.getUserId()))
            throw new NotOwnerException("权限错误");

        // 从MySQL数据库中删除
        fingerPrintMetadata2DMapper.deleteById(metadataId);

        // 从levelDB数据库中删除
        levelDBUtil.delete(metadataId);
    }


    @Override
    public FingerPrintMetaDetailResponse queryMetaById(String metadataId) throws NotOwnerException {
        // 获取当前用户的ID
        String uid = jwtUtil.extractUidSubject(request);

        FingerPrintMetadata2D f = levelDBUtil.get(metadataId, FingerPrintMetadata2D.class);
        if (!uid.equals(f.getUserId()))
            throw new NotOwnerException("权限错误");

        return FingerPrintMetaDetailResponse.builder()
                .metadataId(metadataId)
                .accessPoints(f.getAccessPoints())
                .fingerPrintS(f.getFingerPrint2DList())
                .build();
    }


    @Override
    public IPage<FingerPrintMetadata2D> queryMetadataByPage(Long pageNo, Long pageSize) {
        // 获取当前用户的ID
        String uid = jwtUtil.extractUidSubject(request);

        Page<FingerPrintMetadata2D> page = new Page<>(pageNo, pageSize);
        QueryWrapper<FingerPrintMetadata2D> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", uid);
        fingerPrintMetadata2DMapper.selectPage(page, wrapper);

        for (FingerPrintMetadata2D record : page.getRecords()) {
            String metaId = record.getMetaId();
            FingerPrintMetadata2D metadata2D = levelDBUtil.get(metaId, FingerPrintMetadata2D.class);
            record.setAccessPoints(metadata2D.getAccessPoints());
            record.setCount(metadata2D.getFingerPrint2DList().size());
        }

        return page;
    }

}
