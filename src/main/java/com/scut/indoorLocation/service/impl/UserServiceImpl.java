package com.scut.indoorLocation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scut.indoorLocation.entity.User;
import com.scut.indoorLocation.mapper.UserMapper;
import com.scut.indoorLocation.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Mingor on 2019/11/19 9:35
 */
@Service()
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public List<User> getUsersList() {
        return null;
    }

    @Override
    public List<User> getByAge(int age) {
        return null;
    }
}
