package com.scut.indoorLocation.utility;

import com.scut.indoorLocation.exception.FileUploadException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.Future;

/**
 * 图片操作工具
 * Created by Mingor on 2019/12/30 10:00
 */
@Component
@Slf4j
public class ImageUtil {


    @Value("${image.dir.path}")
    private String imageFilesPath;

    private static String URL_PATH = "http://39.99.131.85/images/";

    /**
     * 注意该方法为异步方法
     * 上传的照片  -->   文件url
     * @param image 待上传的文件
     * @return 图片url
     */
    @Async
    public Future<String> saveImage(MultipartFile image) throws FileUploadException {

        // 若图片为空
        if(image == null || image.isEmpty())
            throw new FileUploadException("图片不能为空");

        // 使用UUID构造新的文件路径
        String tag = image.getOriginalFilename().split("\\.")[1];
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + tag;
        String destFileName = imageFilesPath + File.separator + fileName;
        File destFile = new File(destFileName);

        // 保存图片
        try {
            image.transferTo(destFile);
        } catch (IOException e) {
            throw new FileUploadException("系统内部IO错误");
        }

        // 返回图片的URL
        return new AsyncResult<>(URL_PATH + fileName);
    }

}
