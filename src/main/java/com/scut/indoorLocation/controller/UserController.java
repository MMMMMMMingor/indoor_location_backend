package com.scut.indoorLocation.controller;

import com.scut.indoorLocation.dto.SuccessResponse;
import com.scut.indoorLocation.dto.UserAndPassRequest;
import com.scut.indoorLocation.dto.UserInfoRequest;
import com.scut.indoorLocation.entity.UserBasic;
import com.scut.indoorLocation.exception.UserNameExistException;
import com.scut.indoorLocation.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Mingor on 2019/11/18 23:35
 */
@Api("用户接口")
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Resource
    HttpServletRequest request;

    @Resource
    private UserService userService;

    @ApiOperation("新用户注册")
    @PostMapping("/register")
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
    @GetMapping("/modify")
    public ResponseEntity<SuccessResponse> getUsers(@RequestBody UserInfoRequest request){
        userService.modifyUserInformation(request);
        return ResponseEntity.ok(new SuccessResponse(true, "修改成功"));
    }



    @ApiOperation("查询所有用户")
    @GetMapping("/all")
    public ResponseEntity<List<UserBasic>> getUsers(){
        return ResponseEntity.ok(userService.getUsersList());
    }

}
