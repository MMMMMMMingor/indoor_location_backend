package com.scut.indoorLocation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by Mingor on 2019/12/31 11:25
 */
@Data
@Builder
@TableName("comment")
public class Comment {

    @TableId(value = "comment_id", type = IdType.ASSIGN_UUID)
    private String commentId;

    private String storeId;

    private String userId;

    private Integer score;

    private String comment;

    @JsonIgnore
    private LocalDateTime createTime;
}
