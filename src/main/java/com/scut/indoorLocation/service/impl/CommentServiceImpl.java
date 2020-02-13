package com.scut.indoorLocation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scut.indoorLocation.dto.CommentRequest;
import com.scut.indoorLocation.dto.GetAverageCommentPointRequest;
import com.scut.indoorLocation.entity.Comment;
import com.scut.indoorLocation.entity.Store;
import com.scut.indoorLocation.exception.CreateException;
import com.scut.indoorLocation.exception.GetAverageCommentPointErrorException;
import com.scut.indoorLocation.mapper.CommentMapper;
import com.scut.indoorLocation.service.CommentService;
import com.scut.indoorLocation.utility.JwtUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        String uid = jwtUtil.extractUidSubject(this.request);
        if(commentRequest.getScore()<0 || commentRequest.getScore()>10)
            throw new CreateException("评分范围须在1-10之间");
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

    @Override
    public double getAverageCommentPoint(GetAverageCommentPointRequest getAverageCommentPointRequest) throws GetAverageCommentPointErrorException {

        QueryWrapper<Comment> wrapper = new QueryWrapper<Comment>().eq("store_id",getAverageCommentPointRequest.getStoreId());
        List<Comment> list = commentMapper.selectList(wrapper);
        double sum = 0;

        for(int i = 0;i< list.size();i++){
            sum += list.get(i).getScore();
        }
        double averagePoint = sum / list.size();
        return averagePoint;

    }

    @Override
    public IPage<Comment> queryCommentByPage(String storeId, Long pageNO, Long pageSize) {

        // 构造分页查询条件
        IPage<Comment> page = new Page<>(pageNO, pageSize);
        QueryWrapper<Comment> wrapper = new QueryWrapper<Comment>().eq("store_id", storeId);

        // 分页查询
        return commentMapper.selectPage(page, wrapper);
    }


}
