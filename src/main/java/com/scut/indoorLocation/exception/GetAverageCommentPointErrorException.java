package com.scut.indoorLocation.exception;

/**
 * 获取商店评论平均评分异常
 * Created by YellowBroke on 2020/2/12 23：02
 */
public class GetAverageCommentPointErrorException extends Exception{

    private String message;

    public GetAverageCommentPointErrorException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
