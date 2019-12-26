package com.scut.indoorLocation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

/**
 * Created by Mingor on 2019/11/18 23:35
 */

@Data
@Builder
@TableName("user_basic")
public class UserBasic {

    @JsonIgnore
    @TableId(value = "user_id", type = IdType.ASSIGN_UUID)
    private String id;

    private String username;

    @JsonIgnore
    private String password;

}
