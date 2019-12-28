package com.scut.indoorLocation.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Mingor on 2019/12/27 16:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoRequest {

    @ApiModelProperty(value = "昵称，注意与用户名username不一样", name = "nickname", example = "小学生")
    private String nickname;

    @ApiModelProperty(value = "性别", name = "gender", example = "男")
    private String gender;

    @ApiModelProperty(value = "年龄", name = "age", example = "18")
    private Integer age;

    @ApiModelProperty(value = "职业", name = "vacation", example = "学生")
    private String vacation;

    @ApiModelProperty(value = "个人标签", name = "personLabel", example = "坑")
    private String personLabel;

    @ApiModelProperty(value = "头像地址", name = "avatarUrl", example = "https://i2.hdslb.com/bfs/face/719eb343c7e8f8f6e4a3b5308a1c7b6cc3fded57.jpg")
    private String avatarUrl;

}
