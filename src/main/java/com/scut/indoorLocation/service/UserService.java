package com.scut.indoorLocation.service;

import com.scut.indoorLocation.dto.UserInfoRequest;
import com.scut.indoorLocation.entity.UserBasic;
import com.scut.indoorLocation.entity.UserInformation;
import com.scut.indoorLocation.exception.UserInfoModifyException;
import com.scut.indoorLocation.exception.UserNameExistException;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by Mingor on 2019/11/19 9:32
 */
public interface UserService {

    /**
     * 用户注册
     * @param username 用户名（账号）
     * @param password 密码
     * @throws UserNameExistException 用户名已存在
     */
    void userRegister(String username, String password) throws UserNameExistException;

    /**
     * 获取所有用户信息
      * @return 所有用户信息
     */
    List<UserInformation> getUserInfoList();

    /**
     * 修改用户个人信息
     * @param userInfoRequest 用户信息数据
     */
    void modifyUserInformation(UserInfoRequest userInfoRequest) throws UserInfoModifyException, ExecutionException, InterruptedException;

    /**
     * 初始化用户信息（异步）
     * @param user_id user ID
     */
    Future<Boolean> createUserInformation(String user_id);
}
