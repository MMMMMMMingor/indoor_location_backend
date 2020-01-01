package com.scut.indoorLocation.service;

import com.scut.indoorLocation.dto.CommentRequest;
import com.scut.indoorLocation.exception.CreateException;

/**
 * Created by Mingor on 2019/12/31 11:38
 */
public interface CommentService  {

    void createComment(CommentRequest commentRequest) throws CreateException;


}
