package com.scut.indoorLocation.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scut.indoorLocation.dto.CommentRequest;
import com.scut.indoorLocation.dto.SuccessResponse;
import com.scut.indoorLocation.entity.Comment;
import com.scut.indoorLocation.exception.CreateException;
import com.scut.indoorLocation.exception.GetAverageCommentPointErrorException;
import com.scut.indoorLocation.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by Mingor on 2019/12/31 11:47
 */
@Api(value = "评论接口", tags = "评论接口")
@RestController
@Slf4j
@RequestMapping("/api/comment")
public class CommentController {

    @Resource
    private CommentService commentService;


    @ApiOperation("创建评论信息")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<SuccessResponse> createComment(@RequestBody CommentRequest commentRequest) {
        try {
            commentService.createComment(commentRequest);
            return ResponseEntity.ok(new SuccessResponse(true, "创建成功"));
        } catch (CreateException e) {
            log.error("{}", e.getMessage());
            return ResponseEntity.ok(new SuccessResponse(false, "创建失败"));
        }
    }


    @ApiOperation("分页方式查询店铺的评论")
    @RequestMapping(value = "/query/{storeId}/{pageNo}/{pageSize}", method = RequestMethod.GET)
    public ResponseEntity<IPage<Comment>> queryStorePaging(@ApiParam(value = "商铺ID") @PathVariable String storeId,
                                                           @ApiParam(value = "页号") @PathVariable Long pageNo,
                                                           @ApiParam(value = "页大小") @PathVariable Long pageSize) {
        IPage<Comment> commentsPage = commentService.queryCommentByPage(storeId, pageNo, pageSize);
        return ResponseEntity.ok(commentsPage);
    }


    @ApiOperation("查询店铺平均评论评分")
    @RequestMapping(value = "/average/{storeId}", method = RequestMethod.GET)
    public double getAverageCommentPoint(@ApiParam(value = "店铺ID") @PathVariable String storeId) {
        try {
            return commentService.getAverageCommentPoint(storeId);
        } catch (GetAverageCommentPointErrorException e) {
            log.error("{}", e.getMessage());
            return 0;
        }
    }
}