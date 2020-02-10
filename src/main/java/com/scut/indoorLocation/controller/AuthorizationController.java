package com.scut.indoorLocation.controller;

import com.scut.indoorLocation.dto.JWTResponse;
import com.scut.indoorLocation.mapper.UserBasicMapper;
import com.scut.indoorLocation.utility.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/** Authorization模块（JWT）
 * Created by Mingor on 2019/12/26 23:05
 */
@Api(value = "认证接口", tags = "认证接口")
@RestController
@Slf4j
public class AuthorizationController {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private UserBasicMapper userBasicMapper;

    @Resource
    private JwtUtil jwtUtil;

    @ApiOperation("登录认证")
    @RequestMapping(value = "/auth/{username}/{password}", method = RequestMethod.POST)
    public ResponseEntity<JWTResponse> auth(@PathVariable("username") String username,
                                            @PathVariable("password") String password){
        // authenticationManager最终调用的是JWTUserDetailsService中的loadUserByUsername
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        // 获取用户ID
        Map<String, Object> claims = new HashMap<>();
        claims.put("uid", userBasicMapper.getUserIdByName(username));

        // 生成Token并返回
        String jwt = jwtUtil.generateToken(username, claims);
        log.info("登录用户: {}, JWT: {}", username, jwt);
        return ResponseEntity.ok(new JWTResponse(jwt));

    }

}
