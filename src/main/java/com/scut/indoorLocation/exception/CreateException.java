package com.scut.indoorLocation.exception;

/**
 * 商铺创建异常
 * Created by Mingor on 2019/12/30 22:13
 */
public class CreateException extends Exception{

    private String message;

    public CreateException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
