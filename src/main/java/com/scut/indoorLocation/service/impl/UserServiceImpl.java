package com.scut.indoorLocation.service.impl;

import com.scut.indoorLocation.dto.UserInfoRequest;
import com.scut.indoorLocation.entity.UserBasic;
import com.scut.indoorLocation.entity.UserInformation;
import com.scut.indoorLocation.exception.UserInfoModifyException;
import com.scut.indoorLocation.exception.UserNameExistException;
import com.scut.indoorLocation.mapper.UserBasicMapper;
import com.scut.indoorLocation.mapper.UserInformationMapper;
import com.scut.indoorLocation.service.UserService;
import com.scut.indoorLocation.utility.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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
    public List<UserInformation> getUserInfoList() {
        return userInformationMapper.selectList(null);
    }

    @Override
    public void modifyUserInformation(UserInfoRequest userInfoRequest) throws UserInfoModifyException, ExecutionException, InterruptedException {
            String uid = jwtUtil.extractUidSubject(this.request);

            Future<Boolean> success = this.createUserInformation(uid);

            UserInformation userInformation = UserInformation.builder()
                    .userId(uid)
                    .nickname(userInfoRequest.getNickname())
                    .gender(userInfoRequest.getGender())
                    .age(userInfoRequest.getAge())
                    .vocation(userInfoRequest.getVacation())
                    .personLabel(userInfoRequest.getPersonLabel())
                    .avatarUrl(userInfoRequest.getAvatarUrl())
                    .build();
            success.get();

            if(userInformationMapper.updateById(userInformation) != 1)
                throw new UserInfoModifyException("用户信息修改失败");
    }


    @Override
    @Async
    public Future<Boolean> createUserInformation(String user_id) {
        if(userBasicMapper.selectById(user_id) != null)
            return new AsyncResult<>(true);

        UserInformation userInformation = UserInformation.builder()
                .userId(user_id)
                .build();

        boolean success = (userInformationMapper.insert(userInformation) == 1);
        return new AsyncResult<>(success);
    }


}
