package com.scut.indoorLocation.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scut.indoorLocation.dto.RegisterRequest;
import com.scut.indoorLocation.dto.SuccessResponse;
import com.scut.indoorLocation.dto.UserInfoRequest;
import com.scut.indoorLocation.entity.UserInformation;
import com.scut.indoorLocation.exception.UserInfoModifyException;
import com.scut.indoorLocation.exception.UserNameExistException;
import com.scut.indoorLocation.exception.VerifyCodeException;
import com.scut.indoorLocation.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by Mingor on 2019/11/18 23:35
 */
@Api(value = "用户信息接口", tags = "用户信息接口")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation("新用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<SuccessResponse> register(@RequestBody RegisterRequest request) {
        try {
            userService.userRegister(request);
            return ResponseEntity.ok(new SuccessResponse(true, "注册成功"));
        } catch (UserNameExistException e) {
            return ResponseEntity.ok(new SuccessResponse(false,"用户名已存在"));
        } catch (VerifyCodeException e) {
            return ResponseEntity.ok(new SuccessResponse(false,"验证码错误"));
        }
    }

    @ApiOperation("修改用户信息")
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ResponseEntity<SuccessResponse> getUsers(@RequestBody UserInfoRequest request){
        try {
            userService.modifyUserInformation(request);
            return ResponseEntity.ok(new SuccessResponse(true, "修改成功"));
        } catch (UserInfoModifyException e) {
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

    @ApiOperation("分页方式查询用户信息")
    @RequestMapping(value = "/query/{pageNo}/{pageSize}", method = RequestMethod.GET)
    public ResponseEntity<IPage<UserInformation>> getUsers(@ApiParam(value = "页号") @PathVariable Long pageNo,
                                                           @ApiParam(value = "页大小") @PathVariable Long pageSize){
        return ResponseEntity.ok(userService.getUserInfoByPage(pageNo, pageSize));
    }

}
