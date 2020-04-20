package com.scut.indoorLocation.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scut.indoorLocation.dto.*;
import com.scut.indoorLocation.entity.FingerPrintMetadata2D;
import com.scut.indoorLocation.exception.CreateException;
import com.scut.indoorLocation.exception.FingerPrintAuthorizationException;
import com.scut.indoorLocation.exception.NotOwnerException;
import com.scut.indoorLocation.service.LocationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * Created by Mingor on 2020/2/19 21:32
 */
@Api(value = "定位服务接口", tags = "定位服务接口")
@RestController
@RequestMapping("/api/location")
@Slf4j
public class LocationController {

    @Resource
    private LocationService locationService;

    @ApiOperation("创建指纹库")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<SuccessResponse> createFingerPrintMetadata(@RequestBody FingerPrintMetadataRequest metadataRequest) {

        try {
            locationService.createFingerPrintMetadata(metadataRequest);
            return ResponseEntity.ok(new SuccessResponse(true, "创建成功"));

        } catch (CreateException e) {
            return ResponseEntity.ok(new SuccessResponse(false, e.getMessage()));
        }
    }

    @ApiOperation("查询指纹库详细信息")
    @RequestMapping(value = "/meta/detail/{metadataId}", method = RequestMethod.GET)
    public ResponseEntity<FingerPrintMetaDetailResponse> queryFingerPrintMetadata(@ApiParam(value = "metadataId") @PathVariable String metadataId) {

        try {
            FingerPrintMetaDetailResponse response = locationService.queryMetaById(metadataId);
            return ResponseEntity.ok(response);
        } catch (NotOwnerException e) {
            return ResponseEntity.of(Optional.empty());
        }
    }


    @ApiOperation("删除指纹库记录")
    @RequestMapping(value = "/{metadataId}", method = RequestMethod.DELETE)
    public ResponseEntity<SuccessResponse> deleteFingerPrintMetadata(@ApiParam(value = "metadataId") @PathVariable String metadataId) {

        try {
            locationService.deleteFingerPrintMetadata(metadataId);
            return ResponseEntity.ok(new SuccessResponse(true, "删除成功"));

        } catch (NotOwnerException e) {
            return ResponseEntity.ok(new SuccessResponse(false, e.getMessage()));
        }

    }

    @ApiOperation("分页查询历史指纹库元信息")
    @RequestMapping(value = "/query/{pageNo}/{pageSize}", method = RequestMethod.GET)
    public ResponseEntity<IPage<FingerPrintMetadata2D>> queryCollection(@ApiParam(value = "页号") @PathVariable Long pageNo,
                                                                        @ApiParam(value = "页大小") @PathVariable Long pageSize) {
        // 分页查询
        IPage<FingerPrintMetadata2D> data = locationService.queryMetadataByPage(pageNo, pageSize);

        return ResponseEntity.ok(data);
    }


    @ApiOperation("申请采集指纹信息，申请成功后回返一个临时码，以该临时为topic，向mqtt服务器向服务器发送客户端采集到指纹")
    @RequestMapping(value = "/collect/{metadataId}", method = RequestMethod.POST)
    public ResponseEntity<CollectTopicResponse> saveFingerPrint(@ApiParam(value = "指纹库元数据ID") @PathVariable String metadataId) {

        try {
            String tmpCode = locationService.collectFingerPrint(metadataId);
            return ResponseEntity.ok(new CollectTopicResponse(true, tmpCode, "请求成功"));

        } catch (FingerPrintAuthorizationException e) {
            return ResponseEntity.ok(new CollectTopicResponse(false, null, e.getMessage()));
        }
    }




}
