package com.scut.indoorLocation.controller;

import com.scut.indoorLocation.dto.Success;
import com.scut.indoorLocation.entity.UserBasic;
import com.scut.indoorLocation.exception.UserNameExistException;
import com.scut.indoorLocation.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Mingor on 2019/11/18 23:35
 */
@Api("测试接口")
@RestController
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation("查询所有的user")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true)
    })
    @PostMapping("/register")
    public ResponseEntity<Success> register(@RequestParam("username") String username,
                                            @RequestParam("password") String password){
        try {
            userService.userRegister(username, password);
            log.info("用户: {} 注册成功!",username);
            return ResponseEntity.ok(new Success(true, "注册成功"));
        } catch (UserNameExistException e) {
            log.error("用户: {} 注册失败!", username);
            return ResponseEntity.ok(new Success(false,"用户名已存在"));
        }
    }

    @ApiOperation("查询所有的user")
    @GetMapping("/allUsers")
    public ResponseEntity<List<UserBasic>> getUsers(){
        return ResponseEntity.ok(userService.getUsersList());
    }

}
