package com.scut.indoorLocation.exception;

/**
 * 文件上传错误
 * Created by Mingor on 2019/12/30 10:18
 */
public class FileUploadException extends Exception{

    private String message;

    public FileUploadException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
