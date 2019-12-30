package com.scut.indoorLocation.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scut.indoorLocation.dto.PageRequest;
import com.scut.indoorLocation.dto.StoreInfoRequest;
import com.scut.indoorLocation.dto.SuccessResponse;
import com.scut.indoorLocation.entity.Store;
import com.scut.indoorLocation.exception.StoreCreateException;
import com.scut.indoorLocation.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 店铺接口
 * Created by Mingor on 2019/12/30 21:56
 */
@Api(value = "店铺接口", tags = "店铺接口")
@RestController
@Slf4j
@RequestMapping("/store")
public class StoreController {

    @Resource
    private StoreService storeService;

    @ApiOperation("创建商铺信息")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<SuccessResponse> createStore(@RequestBody StoreInfoRequest request) {
        try {
            storeService.createStore(request);
            return ResponseEntity.ok(new SuccessResponse(true, "创建成功"));
        } catch (StoreCreateException e) {
            log.error("{}", e.getMessage());
            return ResponseEntity.ok(new SuccessResponse(false, "创建失败"));
        }
    }

    @ApiOperation("分页方式查询店铺")
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ResponseEntity<IPage<Store>> queryStorePaging(@RequestBody PageRequest pageRequest) {
//        try {
        IPage<Store> storesPage = storeService.queryStoresByPage(pageRequest);
        return ResponseEntity.ok(storesPage);
//        } catch (StoreCreateException e) {
//            log.error("{}", e.getMessage());
//            return ResponseEntity.ok();
//        }
    }

}
