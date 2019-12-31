package com.scut.indoorLocation.controller;

import com.scut.indoorLocation.dto.CommentRequest;
import com.scut.indoorLocation.dto.SuccessResponse;
import com.scut.indoorLocation.exception.CreateException;
import com.scut.indoorLocation.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
