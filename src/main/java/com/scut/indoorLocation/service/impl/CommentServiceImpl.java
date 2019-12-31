package com.scut.indoorLocation.service.impl;

import com.scut.indoorLocation.dto.CommentRequest;
import com.scut.indoorLocation.entity.Comment;
import com.scut.indoorLocation.exception.CreateException;
import com.scut.indoorLocation.mapper.CommentMapper;
import com.scut.indoorLocation.service.CommentService;
import com.scut.indoorLocation.utility.JwtUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * Created by Mingor on 2019/12/31 11:44
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Resource
    private HttpServletRequest request;

    @Resource
    private JwtUtil jwtUtil;

    @Override
    public void createComment(CommentRequest commentRequest) throws CreateException {
        String uid = this.jwtUtil.extractUidSubject(this.request);
        Comment comment = Comment.builder()
                .storeId(commentRequest.getStoreId())
                .userId(uid)
                .score(commentRequest.getScore())
                .comment(commentRequest.getComment())
                .createTime(LocalDateTime.now())
                .build();

        if (commentMapper.insert(comment) != 1)
            throw new CreateException("评论创建异常");

    }
}
