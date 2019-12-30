package com.scut.indoorLocation.controller;

import com.scut.indoorLocation.dto.ImageUrlResponse;
import com.scut.indoorLocation.exception.FileUploadException;
import com.scut.indoorLocation.utility.ImageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * 文件上传接口
 * Created by Mingor on 2019/12/30 10:16
 */
@Api(value = "文件上传接口", tags = "文件上传接口")
@RestController
@Slf4j
@RequestMapping("/file")
public class FileUploadController {

    @Resource
    private ImageUtil imageUtil;

    @ApiOperation("上传图片")
    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = "multipart/*",headers = "content-type=multipart/form-date")
    public ResponseEntity<ImageUrlResponse> uploadImage(MultipartFile image) throws InterruptedException {

        try{
            String url = imageUtil.uploadImage(image).get();
            return ResponseEntity.ok(new ImageUrlResponse(true, url, "上传成功"));

        }catch (FileUploadException | ExecutionException e){
            log.error("{}", e.getMessage());
            return ResponseEntity.ok(new ImageUrlResponse(false, "", "图片不能为空"));
        }

    }

}
