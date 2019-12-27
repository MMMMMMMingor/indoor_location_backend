package com.scut.indoorLocation.dto;

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

    private String nickname; //昵称，注意与用户名username不一样

    private String gender;

    private Integer age;

    private String vacation; //职业

    private String personLabel; //个人标签

    private String avatarUrl; //头像地址

}
