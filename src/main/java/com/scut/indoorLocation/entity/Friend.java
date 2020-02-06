package com.scut.indoorLocation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by Mingor on 2019/12/31 20:45
 */
@Data
@Builder
@TableName("friends")
public class Friend {

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    @JsonIgnore
    private String id;

    private String userId;

    private String friendId;

    private LocalDateTime createTime;

}
