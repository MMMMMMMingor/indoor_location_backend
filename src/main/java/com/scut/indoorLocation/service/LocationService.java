package com.scut.indoorLocation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scut.indoorLocation.dto.FingerPrintMetadataRequest;
import com.scut.indoorLocation.dto.FingerPrintRequest;
import com.scut.indoorLocation.entity.FingerPrint2D;
import com.scut.indoorLocation.entity.FingerPrintMetadata2D;
import com.scut.indoorLocation.exception.CreateException;
import com.scut.indoorLocation.exception.FingerPrintEmptyException;
import com.scut.point.Vector2D;


/**
 * Created by Mingor on 2020/2/17 20:33
 */
public interface LocationService {

    /**
     * 保存指纹信息
     *
     * @param fingerPrint 指纹信息
     */
    void saveFingerPrint(FingerPrint2D fingerPrint);

    /**
     * 创建指纹库元数据
     * @param request 3个AP数据
     */
    void createFingerPrintMetadata(FingerPrintMetadataRequest request) throws FingerPrintEmptyException, CreateException;

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
     * @param request 当前指纹信息
     * @return 2D坐标
     */
    Vector2D getPosition2D(FingerPrintRequest request) throws FingerPrintEmptyException;

}
