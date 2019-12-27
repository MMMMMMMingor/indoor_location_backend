package com.scut.indoorLocation.exception;

/**
 * Created by Mingor on 2019/12/27 20:26
 */
public class UserInfoModifyException extends Exception {
    private String message;

    public UserInfoModifyException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
