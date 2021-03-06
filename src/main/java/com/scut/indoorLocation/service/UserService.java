package com.scut.indoorLocation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scut.indoorLocation.dto.RegisterRequest;
import com.scut.indoorLocation.dto.UserAndPassRequest;
import com.scut.indoorLocation.dto.UserInfoRequest;
import com.scut.indoorLocation.entity.UserInformation;
import com.scut.indoorLocation.exception.UserInfoModifyException;
import com.scut.indoorLocation.exception.UserNameExistException;
import com.scut.indoorLocation.exception.VerifyCodeException;

/**
 * Created by Mingor on 2019/11/19 9:32
 */
public interface UserService {

    /**
     * 用户注册
     * @param registerRequest 用户名（账号）、密码、邮箱、验证码
     * @throws UserNameExistException 用户名已存在
     */
    void userRegister(RegisterRequest registerRequest) throws UserNameExistException, VerifyCodeException;

    /**
     * 分页方式获取用户信息
      * @return 所有用户信息
     */
    IPage<UserInformation> getUserInfoByPage(Long pageNO, Long pageSize);

    /**
     * 修改用户个人信息
     * @param userInfoRequest 用户信息数据
     */
    void modifyUserInformation(UserInfoRequest userInfoRequest) throws UserInfoModifyException;

    /**
     * 返回当前个人信息
     * @return 当前个人信息
     */
    UserInformation getUserInfo();

    /**
     * 查询非当前用户信息
     * @param uid 用户ID
     * @return 用户信息
     */
    UserInformation getUserInfo(String uid);

}
