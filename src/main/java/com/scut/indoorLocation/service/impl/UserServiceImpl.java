package com.scut.indoorLocation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scut.indoorLocation.dto.UserInfoRequest;
import com.scut.indoorLocation.entity.UserBasic;
import com.scut.indoorLocation.entity.UserInformation;
import com.scut.indoorLocation.exception.UserNameExistException;
import com.scut.indoorLocation.mapper.UserBasicMapper;
import com.scut.indoorLocation.mapper.UserInformationMapper;
import com.scut.indoorLocation.service.UserService;
import com.scut.indoorLocation.utility.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Mingor on 2019/11/19 9:35
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private HttpServletRequest request;

    @Resource
    private JwtUtil jwtUtil;

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
    public String getUserIdByName(String username) {
        QueryWrapper<UserBasic> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        UserBasic userBasic = userBasicMapper.selectOne(wrapper);

        return userBasic.getId();
    }

    @Override
    public List<UserBasic> getUsersList() {
        return userBasicMapper.selectList(null);
    }

    @Override
    public void modifyUserInformation(UserInfoRequest userInfoRequest) {
        String uid = jwtUtil.extractUidSubject(this.request);
        UserInformation userInformation = UserInformation.builder()
                .userId(uid)
                .nickname(userInfoRequest.getNickname())
                .gender(userInfoRequest.getGender())
                .age(userInfoRequest.getAge())
                .vacation(userInfoRequest.getVacation())
                .personLabel(userInfoRequest.getPersonLabel())
                .avatarUrl(userInfoRequest.getAvatarUrl())
                .build();

        userInformationMapper.updateById(userInformation);
    }


}
