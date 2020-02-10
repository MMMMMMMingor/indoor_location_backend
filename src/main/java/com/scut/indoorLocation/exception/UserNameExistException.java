package com.scut.indoorLocation.exception;

/**
 * Created by Mingor on 2019/12/26 21:14
 */
public class UserNameExistException extends Exception {

    private String message;

    public UserNameExistException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
