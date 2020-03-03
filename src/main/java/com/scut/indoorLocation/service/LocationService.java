package com.scut.indoorLocation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scut.indoorLocation.dto.FingerPrintMetaDetailResponse;
import com.scut.indoorLocation.dto.FingerPrintMetadataRequest;
import com.scut.indoorLocation.dto.LocationServiceTopicResponse;
import com.scut.indoorLocation.entity.FingerPrintMetadata2D;
import com.scut.indoorLocation.exception.CreateException;
import com.scut.indoorLocation.exception.FingerPrintAuthorizationException;
import com.scut.indoorLocation.exception.FingerPrintEmptyException;
import com.scut.indoorLocation.exception.NotOwnerException;


/**
 * Created by Mingor on 2020/2/17 20:33
 */
public interface LocationService {

    /**
     * 申请采集指纹信息
     * @param metadataId 指纹库元数据ID
     */
    String collectFingerPrint(String metadataId) throws FingerPrintAuthorizationException;

    /**
     * 创建指纹库元数据
     * @param request 3个AP数据
     */
    void createFingerPrintMetadata(FingerPrintMetadataRequest request) throws CreateException;

    /**
     * 删除指纹库元数据
     * @param metadataId 指纹库元数据ID
     */
    void deleteFingerPrintMetadata(String metadataId) throws NotOwnerException;

    /**
     * 查询 metadata 详细信息
     * @param metadataId 指纹库元数据ID
     * @return 详细信息
     */
    FingerPrintMetaDetailResponse queryMetaById(String metadataId) throws NotOwnerException;

    /**
     * 分页查询指纹库元数据
     * @param pageNo 页号
     * @param pageSize 页大小
     * @return 分页后的元数据
     */
    IPage<FingerPrintMetadata2D> queryMetadataByPage(Long pageNo, Long pageSize);

    /**
     * 计算用户的2D坐标
     *
     * @param metadataId 当前指纹元数据
     * @return mqtt topic
     */
    LocationServiceTopicResponse getPosition2D(String metadataId) throws FingerPrintEmptyException;


}
