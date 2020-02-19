package com.scut.indoorLocation.exception;

/**
 * Created by Mingor on 2020/2/18 21:28
 */
public class FingerPrintEmptyException extends Exception {

    private String message;

    public FingerPrintEmptyException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
