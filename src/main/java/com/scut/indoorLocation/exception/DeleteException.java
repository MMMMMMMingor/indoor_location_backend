package com.scut.indoorLocation.exception;

public class DeleteException extends  Exception{
    private String message;

    public DeleteException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
