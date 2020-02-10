package com.scut.indoorLocation.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 注册请求信息
 * Created by Mingor on 2020/1/13 15:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @ApiModelProperty(value = "用户名(账号)", name = "username", example = "foo")
    private String username;

    @ApiModelProperty(value = "密码", name = "password", example = "bar")
    private String password;

    @ApiModelProperty(value = "邮箱地址", name = "email", example = "xxxxxx@qq.com")
    private String email;

    @ApiModelProperty(value = "验证码", name = "verifyCode", example = "123456")
    private String verifyCode;
}
