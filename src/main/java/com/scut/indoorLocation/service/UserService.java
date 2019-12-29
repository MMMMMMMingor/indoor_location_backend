package com.scut.indoorLocation.service;

import com.scut.indoorLocation.dto.UserAndPassRequest;
import com.scut.indoorLocation.dto.UserInfoRequest;
import com.scut.indoorLocation.entity.UserInformation;
import com.scut.indoorLocation.exception.UserInfoModifyException;
import com.scut.indoorLocation.exception.UserNameExistException;

import java.util.List;

/**
 * Created by Mingor on 2019/11/19 9:32
 */
public interface UserService {

    /**
     * 用户注册
     * @param userAndPassRequest 用户名（账号）、密码
     * @throws UserNameExistException 用户名已存在
     */
    void userRegister(UserAndPassRequest userAndPassRequest) throws UserNameExistException;

    /**
     * 获取所有用户信息
      * @return 所有用户信息
     */
    List<UserInformation> getUserInfoList();

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
