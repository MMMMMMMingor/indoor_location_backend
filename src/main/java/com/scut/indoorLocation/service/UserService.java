package com.scut.indoorLocation.service;

import com.scut.indoorLocation.dto.UserInfoRequest;
import com.scut.indoorLocation.entity.UserBasic;
import com.scut.indoorLocation.exception.UserNameExistException;

import java.util.List;

/**
 * Created by Mingor on 2019/11/19 9:32
 */
public interface UserService {

    /**
     * 用户注册
     * @param username 用户名
     * @param password 密码
     * @throws UserNameExistException 用户名已存在
     */
    void userRegister(String username, String password) throws UserNameExistException;

    /**
     * 用户名  ->   用户ID
     * @param username 用户名
     * @return 用户ID
     */
    String getUserIdByName(String username);

    /**
     * 获取所有用户
      * @return 所有用户
     */
    List<UserBasic> getUsersList();

    /**
     * 修改用户个人信息
     * @param userInfoRequest 用户信息数据
     */
    void modifyUserInformation(UserInfoRequest userInfoRequest);

}
