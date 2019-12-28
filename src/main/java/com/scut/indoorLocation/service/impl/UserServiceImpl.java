package com.scut.indoorLocation.service.impl;

import com.scut.indoorLocation.dto.UserAndPassRequest;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
    private BCryptPasswordEncoder passwordEncoder;

    @Resource
    private UserBasicMapper userBasicMapper;

    @Resource
    private UserInformationMapper userInformationMapper;

    @Override
    @Transactional(rollbackFor = {UserNameExistException.class})
    public void userRegister(UserAndPassRequest userAndPassRequest) throws UserNameExistException{
        String encryptedPassword = passwordEncoder.encode(userAndPassRequest.getPassword()); //对密码进行加密

        UserBasic userBasic = UserBasic.builder()
                .username(userAndPassRequest.getUsername())
                .password(encryptedPassword)
                .build();

            if(userBasicMapper.insert(userBasic) != 1)
                throw new UserNameExistException("用户名已存在");
            else {
                // 初始化对应的 user_information 表信息
                String user_id = userBasicMapper.getUserIdByName(userBasic.getUsername());

                UserInformation userInformation = UserInformation.builder()
                                                                .userId(user_id)
                                                                .build();

                userInformationMapper.insert(userInformation);
            }
    }


    @Override
    public List<UserInformation> getUserInfoList() {
        return userInformationMapper.selectList(null);
    }

    @Override
    public void modifyUserInformation(UserInfoRequest userInfoRequest) throws UserInfoModifyException{
            //从请求上下文中获取 uid
            String uid = jwtUtil.extractUidSubject(this.request);

            UserInformation userInformation = UserInformation.builder()
                    .userId(uid)
                    .nickname(userInfoRequest.getNickname())
                    .gender(userInfoRequest.getGender())
                    .age(userInfoRequest.getAge())
                    .vocation(userInfoRequest.getVacation())
                    .personLabel(userInfoRequest.getPersonLabel())
                    .avatarUrl(userInfoRequest.getAvatarUrl())
                    .build();

            if(userInformationMapper.updateById(userInformation) != 1)
                throw new UserInfoModifyException("用户信息修改失败");
    }

    @Override
    public UserInformation getUserInfo() {
        String uid = jwtUtil.extractUidSubject(this.request);

        return userInformationMapper.selectById(uid);

    }

    @Override
    public UserInformation getUserInfo(String uid) {
        return userInformationMapper.selectById(uid);
    }

}
