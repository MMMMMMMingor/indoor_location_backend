package com.scut.indoorLocation.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    public void userRegister(UserAndPassRequest userAndPassRequest) throws UserNameExistException {
        try {
            String encryptedPassword = passwordEncoder.encode(userAndPassRequest.getPassword()); //对密码进行加密

            UserBasic userBasic = UserBasic.builder()
                    .username(userAndPassRequest.getUsername())
                    .password(encryptedPassword)
                    .build();

            userBasicMapper.insert(userBasic);

            //获取uid
            String user_id = userBasicMapper.getUserIdByName(userBasic.getUsername());

            // 初始化对应的 user_information 表信息
            UserInformation userInformation = UserInformation.builder()
                    .userId(user_id)
                    .build();

            userInformationMapper.insert(userInformation);

        } catch (Exception e) {
            throw new UserNameExistException("用户名已存在");
        }
    }


    @Override
    public IPage<UserInformation> getUserInfoByPage(Long pageNO, Long pageSize) {
        Page<UserInformation> page = new Page<>(pageNO, pageSize);
        return userInformationMapper.selectPage(page, null);
    }

    @Override
    public void modifyUserInformation(UserInfoRequest userInfoRequest) throws UserInfoModifyException {
        //从请求上下文中获取 uid
        String uid = jwtUtil.extractUidSubject(this.request);

        UserInformation userInformation = userInformationMapper.selectById(uid);
        userInformation = UserInformation.builder()
                .userId(uid)
                .nickname(userInfoRequest.getNickname() == null ? userInformation.getNickname() : userInfoRequest.getNickname())
                .gender(userInfoRequest.getGender() == null ? userInformation.getGender() : userInfoRequest.getGender())
                .age(userInfoRequest.getAge() == null ? userInformation.getAge() : userInfoRequest.getAge())
                .vocation(userInfoRequest.getVocation() == null ? userInformation.getVocation() : userInfoRequest.getVocation())
                .personLabel(userInfoRequest.getPersonLabel() == null ? userInformation.getPersonLabel() : userInfoRequest.getPersonLabel())
                .avatarUrl(userInfoRequest.getAvatarUrl() == null ? userInformation.getAvatarUrl() : userInfoRequest.getAvatarUrl())
                .build();

        int count = userInformationMapper.updateById(userInformation);
        if (count != 1)
            throw new UserInfoModifyException("用户信息修改失败");

        log.info("{}: 修改用户信息", uid);
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
