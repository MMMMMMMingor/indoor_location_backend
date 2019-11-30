package com.scut.indoorLocation.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Created by Mingor on 2019/11/18 23:35
 */

@Data
@TableName("user")
public class User {

    @TableId
    private Long id;

    private String name;

    private Integer age;

    private String email;
}
