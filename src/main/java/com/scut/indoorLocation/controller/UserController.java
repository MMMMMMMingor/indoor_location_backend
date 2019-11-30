package com.scut.indoorLocation.controller;

import com.scut.indoorLocation.entity.User;
import com.scut.indoorLocation.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Mingor on 2019/11/18 23:35
 */
@Api("测试接口")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("查询所有的user")
    @GetMapping("/allUsers")
    public List<User> getUsers(){
        return userService.getUsersList();
    }

    @ApiOperation("根据age查询user")
    @ApiImplicitParam(name = "age", value = "年龄", required = true)
    @GetMapping("/getUsersByAge")
    public List<User> getUsersByAge(@RequestParam("age") int age){
        List<User> users = userService.getByAge(age);
        return users;
    }
}
