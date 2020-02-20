package com.scut.indoorLocation.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scut.indoorLocation.dto.SuccessResponse;
import com.scut.indoorLocation.entity.Friend;
import com.scut.indoorLocation.exception.CreateException;
import com.scut.indoorLocation.service.FriendService;
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
 * Created by Mingor on 2020/1/1 11:28
 */
@Api(value = "交友接口", tags = "交友接口")
@RestController
@Slf4j
@RequestMapping("/api/friend")
public class FriendController {

    @Resource
    private FriendService friendService;


    @ApiOperation("添加好友")
    @RequestMapping(value = "/create/{userId}", method = RequestMethod.POST)
    public ResponseEntity<SuccessResponse> createCollection(@ApiParam(value = "商铺ID") @PathVariable String userId) {

        //保存collection
        try {
            friendService.createCollection(userId);
            return ResponseEntity.ok(new SuccessResponse(true, "添加成功"));
        } catch (CreateException e) {
            log.error("{}", e.getMessage());
            return ResponseEntity.ok(new SuccessResponse(true, e.getMessage()));
        }

    }

    @ApiOperation("分页查询好友信息")
    @RequestMapping(value = "/query/{pageNo}/{pageSize}", method = RequestMethod.GET)
    public ResponseEntity<IPage<Friend>> queryCollection(@ApiParam(value = "页号") @PathVariable Long pageNo,
                                                             @ApiParam(value = "页大小") @PathVariable Long pageSize) {
        // 分页查询
        IPage<Friend> page = friendService.queryByPage(pageNo, pageSize);

        return ResponseEntity.ok(page);
    }

}
