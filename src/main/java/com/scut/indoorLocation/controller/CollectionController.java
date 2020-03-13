package com.scut.indoorLocation.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scut.indoorLocation.dto.SuccessResponse;
import com.scut.indoorLocation.entity.Collection;
import com.scut.indoorLocation.exception.CreateException;
import com.scut.indoorLocation.exception.DeleteException;
import com.scut.indoorLocation.service.CollectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Mingor on 2020/1/1 10:32
 */
@Api(value = "收藏接口", tags = "收藏接口")
@RestController
@RequestMapping("/api/collection")
public class CollectionController {

    @Resource
    private CollectionService collectionService;


    @ApiOperation("添加收藏记录")
    @RequestMapping(value = "/create/{storeId}", method = RequestMethod.POST)
    public ResponseEntity<SuccessResponse> createCollection(@ApiParam(value = "商铺ID") @PathVariable String storeId) {

        //保存collection
        try {
            collectionService.createCollection(storeId);
            return ResponseEntity.ok(new SuccessResponse(true, "添加成功"));
        } catch (CreateException e) {
            return ResponseEntity.ok(new SuccessResponse(true, "添加失败"));
        }

    }


    @ApiOperation("分页查询收藏记录")
    @RequestMapping(value = "/query/{pageNo}/{pageSize}", method = RequestMethod.GET)
    public ResponseEntity<IPage<Collection>> queryCollection(@ApiParam(value = "页号") @PathVariable Long pageNo,
                                                             @ApiParam(value = "页大小") @PathVariable Long pageSize) {
        // 分页查询
        IPage<Collection> page = collectionService.queryByPage(pageNo, pageSize);

        return ResponseEntity.ok(page);
    }

    @ApiOperation("删除收藏记录")
    @RequestMapping(value = "/{collectionId}", method = RequestMethod.DELETE)
    public ResponseEntity<SuccessResponse> deleteCollection(@ApiParam(value = "收藏ID") @PathVariable String collectionId) {

        //删除一个collection记录
        try {
            collectionService.deleteCollection(collectionId);
            return ResponseEntity.ok(new SuccessResponse(true, "删除成功"));
        } catch (DeleteException e) {
            return ResponseEntity.ok(new SuccessResponse(true, "删除失败"));
        }

    }

}
