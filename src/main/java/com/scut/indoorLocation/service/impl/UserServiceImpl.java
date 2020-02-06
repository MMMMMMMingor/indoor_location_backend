package com.scut.indoorLocation.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scut.indoorLocation.dto.RegisterRequest;
import com.scut.indoorLocation.dto.UserInfoRequest;
import com.scut.indoorLocation.entity.UserBasic;
import com.scut.indoorLocation.entity.UserInformation;
import com.scut.indoorLocation.exception.UserInfoModifyException;
import com.scut.indoorLocation.exception.UserNameExistException;
import com.scut.indoorLocation.exception.VerifyCodeException;
import com.scut.indoorLocation.mapper.UserBasicMapper;
import com.scut.indoorLocation.mapper.UserInformationMapper;
import com.scut.indoorLocation.service.MailService;
import com.scut.indoorLocation.service.UserService;
import com.scut.indoorLocation.utility.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

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

    @Resource
    private MailService mailService;

    @Override
    @Transactional(rollbackFor = {UserNameExistException.class, VerifyCodeException.class})
    public void userRegister(RegisterRequest registerRequest) throws UserNameExistException, VerifyCodeException {

        // 验证验证码是否正确
        mailService.validateCode(registerRequest.getEmail(), registerRequest.getVerifyCode());

        // 对密码进行加密
        String encryptedPassword = passwordEncoder.encode(registerRequest.getPassword()); //对密码进行加密

        UserBasic userBasic = UserBasic.builder()
                .username(registerRequest.getUsername())
                .password(encryptedPassword)
                .email(registerRequest.getEmail())
                .build();

        userBasicMapper.insert(userBasic);

        //获取uid
        String user_id = userBasicMapper.getUserIdByName(userBasic.getUsername());

        // 初始化对应的 user_information 表信息
        UserInformation userInformation = UserInformation.builder()
                .userId(user_id)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .avatarUrl("http://39.99.131.85/images/719eb343c7e8f8f6e4a3b5308a1c7b6cc3fded57.jpg")
                .build();

        if (userInformationMapper.insert(userInformation) != 1)
            throw new UserNameExistException("用户创建失败");

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
                .updateTime(LocalDateTime.now())
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
