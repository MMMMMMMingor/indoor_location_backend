package com.scut.indoorLocation.exception;


/**
 * Created by Mingor on 2020/1/13 15:47
 */
public class VerifyCodeException extends Exception {
    private String message;

    public VerifyCodeException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
