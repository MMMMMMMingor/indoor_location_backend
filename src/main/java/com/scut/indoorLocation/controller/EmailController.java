package com.scut.indoorLocation.controller;

import com.scut.indoorLocation.dto.SuccessResponse;
import com.scut.indoorLocation.service.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * Created by Mingor on 2020/1/13 15:07
 */
@Api(value = "邮件接口", tags = "邮件接口")
@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Resource
    private MailService mailService;

    @ApiOperation("发送邮件验证码")
    @RequestMapping(value = "/verifyCode/{email}", method = RequestMethod.POST)
    public ResponseEntity<SuccessResponse> sendEmail(@PathVariable String email) {
        mailService.sendMail(email, "邮件验证码");
        return ResponseEntity.ok(new SuccessResponse(true, "验证码已发送至" + email));
    }

}
