package com.scut.indoorLocation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scut.indoorLocation.entity.Friend;
import com.scut.indoorLocation.exception.CreateException;
import com.scut.indoorLocation.mapper.FriendMapper;
import com.scut.indoorLocation.service.FriendService;
import com.scut.indoorLocation.utility.JwtUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * Created by Mingor on 2020/1/1 11:25
 */
@Service
public class FriendServiceImpl implements FriendService {

    @Resource
    private FriendMapper friendMapper;

    @Resource
    private HttpServletRequest request;

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public void createCollection(String friendId) throws CreateException {
        //获取用户ID
        String uid = jwtUtil.extractUidSubject(request);

        Friend friend = Friend.builder()
                .userId(uid)
                .friendId(friendId)
                .createTime(LocalDateTime.now())
                .build();

        if(friendMapper.insert(friend) != 1)
            throw new CreateException("好友记录添加失败");
    }

    @Override
    public IPage<Friend> queryByPage(Long pageNO, Long pageSize) {

        //获取用户ID
        String uid = jwtUtil.extractUidSubject(request);

        // 构造分页查询条件
        IPage<Friend> page = new Page<>(pageNO, pageSize);
        QueryWrapper<Friend> wrapper = new QueryWrapper<Friend>().eq("user_id", uid);

        // 分页查询
        return friendMapper.selectPage(page, wrapper);
    }
}
