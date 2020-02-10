package com.scut.indoorLocation.exception;

/**
 * Created by Mingor on 2020/1/3 9:53
 */
public class NotStoreOwnerException extends Exception {

    private String message;

    public NotStoreOwnerException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
