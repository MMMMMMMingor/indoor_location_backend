package com.scut.indoorLocation.service.impl;

import com.scut.indoorLocation.entity.UserBasic;
import com.scut.indoorLocation.exception.UserNameExistException;
import com.scut.indoorLocation.mapper.UserBasicMapper;
import com.scut.indoorLocation.mapper.UserInformationMapper;
import com.scut.indoorLocation.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Mingor on 2019/11/19 9:35
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserBasicMapper userBasicMapper;

    @Resource
    private UserInformationMapper userInformationMapper;

    @Override
    public void userRegister(String username, String password) throws UserNameExistException{
        UserBasic userBasic = UserBasic.builder()
                .username(username)
                .password(password)
                .build();

        try{
            if(userBasicMapper.insert(userBasic) != 1)
                throw new UserNameExistException("用户名已存在");
        }catch (Exception e){
            throw new UserNameExistException("用户名已存在");
        }
    }

    @Override
    public List<UserBasic> getUsersList() {
        return userBasicMapper.selectList(null);
    }


}
