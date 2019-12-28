package com.scut.indoorLocation.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 账号密码
 * Created by Mingor on 2019/12/26 23:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAndPassRequest {

    @ApiModelProperty(value = "用户名(账号)", name = "username", example = "foobar")
    private String username;

    @ApiModelProperty(value = "密码", name = "password", example = "123456")
    private String password;

}
