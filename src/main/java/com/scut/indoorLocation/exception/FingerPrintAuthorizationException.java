package com.scut.indoorLocation.exception;

/**
 * Created by Mingor on 2020/2/20 16:22
 */
public class FingerPrintAuthorizationException extends Exception {

    private String message;

    public FingerPrintAuthorizationException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
