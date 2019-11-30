package com.scut.indoorLocation.controller;

import com.scut.indoorLocation.dto.Result;
import com.scut.indoorLocation.entity.User;
import com.scut.indoorLocation.service.UserService;
import com.scut.indoorLocation.utility.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Mingor on 2019/11/18 23:35
 */
@Api("测试接口")
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation("查询所有的user")
    @GetMapping("/allUsers")
    public Result<List<User>> getUsers(){
        return ResultUtil.success(userService.getUsersList());
    }

    @ApiOperation("根据age查询user")
    @ApiImplicitParam(name = "age", value = "年龄", required = true)
    @GetMapping("/getUsersByAge")
    public Result<List<User>> getUsersByAge(@RequestParam("age") int age){
        return ResultUtil.success(userService.getByAge(age));
    }
}
