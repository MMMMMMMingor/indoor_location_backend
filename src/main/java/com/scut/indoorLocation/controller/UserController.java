package com.scut.indoorLocation.controller;

import com.scut.indoorLocation.dto.SuccessResponse;
import com.scut.indoorLocation.dto.UserAndPassRequest;
import com.scut.indoorLocation.dto.UserInfoRequest;
import com.scut.indoorLocation.entity.UserInformation;
import com.scut.indoorLocation.exception.UserInfoModifyException;
import com.scut.indoorLocation.exception.UserNameExistException;
import com.scut.indoorLocation.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Mingor on 2019/11/18 23:35
 */
@Api("用户接口")
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation("新用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<SuccessResponse> register(@RequestBody UserAndPassRequest request) {
        try {
            userService.userRegister(request.getUsername(), request.getPassword());
            log.info("用户: {} 注册成功!",request.getUsername());
            return ResponseEntity.ok(new SuccessResponse(true, "注册成功"));
        } catch (UserNameExistException e) {
            log.error("用户: {} 注册失败!", request.getUsername());
            return ResponseEntity.ok(new SuccessResponse(false,"用户名已存在"));
        }
    }

    @ApiOperation("修改用户信息")
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ResponseEntity<SuccessResponse> getUsers(@RequestBody UserInfoRequest request) throws ExecutionException, InterruptedException {
        try {
            userService.modifyUserInformation(request);
            return ResponseEntity.ok(new SuccessResponse(true, "修改成功"));
        } catch (UserInfoModifyException e) {
            log.error("{}", e.getMessage());
            return ResponseEntity.ok(new SuccessResponse(false,"修改失败"));
        }
    }



    @ApiOperation("查询所有用户")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<UserInformation>> getUsers(){
        return ResponseEntity.ok(userService.getUserInfoList());
    }

}
