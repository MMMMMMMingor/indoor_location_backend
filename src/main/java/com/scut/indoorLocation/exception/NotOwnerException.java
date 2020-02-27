package com.scut.indoorLocation.exception;

/**
 * Created by Mingor on 2020/2/27 17:17
 */
public class NotOwnerException extends Exception {
    private String message;

    public NotOwnerException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
