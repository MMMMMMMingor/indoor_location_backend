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
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Mingor on 2019/11/18 23:35
 */
@Api(value = "用户信息接口", tags = "用户信息接口")
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
            userService.userRegister(request);
            log.info("用户: {} 注册成功!",request.getUsername());
            return ResponseEntity.ok(new SuccessResponse(true, "注册成功"));
        } catch (UserNameExistException e) {
            log.error("用户: {} 注册失败!", request.getUsername());
            return ResponseEntity.ok(new SuccessResponse(false,"用户名已存在"));
        }
    }

    @ApiOperation("修改用户信息")
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ResponseEntity<SuccessResponse> getUsers(@RequestBody UserInfoRequest request){
        try {
            userService.modifyUserInformation(request);
            return ResponseEntity.ok(new SuccessResponse(true, "修改成功"));
        } catch (UserInfoModifyException e) {
            log.error("{}", e.getMessage());
            return ResponseEntity.ok(new SuccessResponse(false,"修改失败"));
        }
    }

    @ApiOperation("查询当前用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResponseEntity<UserInformation> getUserInfo(){
        return ResponseEntity.ok(userService.getUserInfo());
    }

    @ApiOperation("根据指定ID查询用户信息")
    @RequestMapping(value = "/info/{uid}", method = RequestMethod.GET)
    public ResponseEntity<UserInformation> getUserInfoById(@ApiParam(value = "用户ID") @PathVariable String uid){
        return ResponseEntity.ok(userService.getUserInfo(uid));
    }

    @ApiOperation("查询所有用户")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<UserInformation>> getUsers(){
        return ResponseEntity.ok(userService.getUserInfoList());
    }

}
