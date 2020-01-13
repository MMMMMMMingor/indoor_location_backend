package com.scut.indoorLocation.service;

import com.scut.indoorLocation.exception.VerifyCodeException;

/**
 * 发邮件服务
 * Created by Mingor on 2020/1/13 14:32
 */
public interface MailService {

    /**
     * 验证 验证码是否正确
     * @param email 邮箱
     * @param verifyCode 验证码
     */
    void validateCode(String email, String verifyCode) throws VerifyCodeException;

    /**
     * 发送邮件
     * @param to 邮件收件人
     * @param subject 邮件主题
     */
    void sendMail(String to, String subject);
}
