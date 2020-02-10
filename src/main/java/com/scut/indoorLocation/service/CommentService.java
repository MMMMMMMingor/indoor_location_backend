package com.scut.indoorLocation.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scut.indoorLocation.dto.CommentRequest;
import com.scut.indoorLocation.entity.Comment;
import com.scut.indoorLocation.exception.CreateException;

/**
 * Created by Mingor on 2019/12/31 11:38
 */
public interface CommentService  {

    /**
     * 创建评论
     * @param commentRequest 评论请求
     */
    void createComment(CommentRequest commentRequest) throws CreateException;


    /**
     * 分页方式查询评论
     * @param storeId 商铺ID
     * @param pageNO 页号
     * @param pageSize 页大小
     * @return 分页后的评论
     */
    IPage<Comment> queryCommentByPage(String storeId, Long pageNO, Long pageSize);

}
