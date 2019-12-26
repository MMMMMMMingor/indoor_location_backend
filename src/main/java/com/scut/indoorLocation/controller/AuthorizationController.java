package com.scut.indoorLocation.controller;

import com.scut.indoorLocation.dto.JWTResponse;
import com.scut.indoorLocation.dto.UserAndPassRequest;
import com.scut.indoorLocation.utility.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/** Authorization模块（JWT）
 * Created by Mingor on 2019/12/26 23:05
 */
@RestController
public class AuthorizationController {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private JwtUtil jwtUtil;

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<JWTResponse> auth(@RequestBody UserAndPassRequest request) {
        // authenticationManager最终调用的是MyUserDetailsService中的loadUserByUsername
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        String jwt = jwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(new JWTResponse(jwt));

    }

}
