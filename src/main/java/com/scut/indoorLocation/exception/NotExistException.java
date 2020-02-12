package com.scut.indoorLocation.exception;

/**
 * Create by hxy 2020/01/10
 *
 * */
public class NotExistException extends Exception{

    private String message;
    public NotExistException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
