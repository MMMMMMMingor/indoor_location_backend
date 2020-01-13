package com.scut.indoorLocation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Created by Mingor on 2019/11/18 23:35
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("user_basic")
public class UserBasic {

    @JsonIgnore
    @TableId(value = "user_id", type = IdType.ASSIGN_UUID)
    private String id;

    private String username;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private String email;

}
