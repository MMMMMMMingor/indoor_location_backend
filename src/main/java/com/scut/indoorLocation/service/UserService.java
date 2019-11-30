package com.scut.indoorLocation.service;

import com.scut.indoorLocation.entity.User;

import java.util.List;

/**
 * Created by Mingor on 2019/11/19 9:32
 */
public interface UserService {

    /**
     * 获取所有用户
      * @return 所有用户
     */
    List<User> getUsersList();

    /**
     * 根据age查询小于该age的用户
     * @param age 年龄
     * @return 小于该age的所有用户
     */
    List<User> getByAge(int age);

}
