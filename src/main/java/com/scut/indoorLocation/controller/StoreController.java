package com.scut.indoorLocation.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scut.indoorLocation.dto.StoreInfoRequest;
import com.scut.indoorLocation.dto.SuccessResponse;
import com.scut.indoorLocation.entity.Store;
import com.scut.indoorLocation.exception.CreateException;
import com.scut.indoorLocation.exception.NotStoreOwnerException;
import com.scut.indoorLocation.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 店铺接口
 * Created by Mingor on 2019/12/30 21:56
 */
@Api(value = "店铺接口", tags = "店铺接口")
@RestController
@Slf4j
@RequestMapping("/api/store")
public class StoreController {

    @Resource
    private StoreService storeService;

    @ApiOperation("创建商铺信息")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<SuccessResponse> createStore(@RequestBody StoreInfoRequest request) {
        try {
            storeService.createStore(request);
            return ResponseEntity.ok(new SuccessResponse(true, "创建成功"));
        } catch (CreateException e) {
            log.error("{}", e.getMessage());
            return ResponseEntity.ok(new SuccessResponse(false, "创建失败"));
        }
    }

    @ApiOperation("分页方式查询店铺")
    @RequestMapping(value = "/query/{pageNo}/{pageSize}", method = RequestMethod.GET)
    public ResponseEntity<IPage<Store>> queryStorePaging(@ApiParam(value = "页号") @PathVariable Long pageNo,
                                                         @ApiParam(value = "也大小") @PathVariable Long pageSize) {
//        try {
        IPage<Store> storesPage = storeService.queryStoresByPage(pageNo, pageSize);
        return ResponseEntity.ok(storesPage);
//        } catch (StoreCreateException e) {
//            log.error("{}", e.getMessage());
//            return ResponseEntity.ok();
//        }
    }


    @ApiOperation("修改店铺信息")
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ResponseEntity<SuccessResponse> modifyStore(@RequestBody StoreInfoRequest param) {
        try {
            storeService.modifyStoreInfo(param);
            return ResponseEntity.ok(new SuccessResponse(true, "店铺信息修改成功"));
        } catch (NotStoreOwnerException e) {
            log.error("店铺信息修改异常 {}", e.getMessage());
            return ResponseEntity.ok(new SuccessResponse(false, "店铺信息修改失败"));
        }
    }


}
