package com.scut.indoorLocation.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

/**
 * Created by Mingor on 2019/12/26 21:59
 */
@Data
@Builder
@TableName("user_information")
public class UserInformation {

    @TableId(value = "user_id")
    private String userId;

    private String nickname;

    private String gender;

    private Integer age;

    private String vocation;

    private String personLabel;

    private String avatarUrl;


}
