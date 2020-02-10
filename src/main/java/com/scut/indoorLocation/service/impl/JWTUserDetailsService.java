package com.scut.indoorLocation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scut.indoorLocation.entity.UserBasic;
import com.scut.indoorLocation.mapper.UserBasicMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;

/**
 * JWT认证专用服务（请不要修改！）
 * Created by Mingor on 2019/12/26 22:33
 */
@Component
public class JWTUserDetailsService implements UserDetailsService {

    @Resource
    private UserBasicMapper userBasicMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<UserBasic> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        UserBasic userBasic = userBasicMapper.selectOne(wrapper);

        if(userBasic == null)
            throw new UsernameNotFoundException("用户不存在");

        Collection<GrantedAuthority> collection = new ArrayList<>();

        return (new User(username, userBasic.getPassword(), collection));

    }
}
