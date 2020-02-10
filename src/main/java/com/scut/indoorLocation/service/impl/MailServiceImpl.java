package com.scut.indoorLocation.service.impl;

import com.scut.indoorLocation.exception.VerifyCodeException;
import com.scut.indoorLocation.service.MailService;
import com.scut.indoorLocation.utility.LevelDBUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

/**
 * Created by Mingor on 2020/1/13 14:32
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService {

    private static final String from = "location_2019@163.com";

    @Resource
    private JavaMailSender mailSender;

    @Resource
    private LevelDBUtil levelDBUtil;

    @Override
    public void validateCode(String email, String verifyCode) throws VerifyCodeException {
        String code = levelDBUtil.get(email, String.class);

        if(code == null || !code.equals(verifyCode))
            throw new VerifyCodeException("验证码错误");
    }


    @Override
    @Async
    public void sendMail(String to, String subject) {
        // 生成验证码
        String verifyCode = genVerifyCode();

        String emailContent = "location, 注册验证码: " + verifyCode;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(emailContent);
        try {
            mailSender.send(message);
            levelDBUtil.put(to, verifyCode);
            log.info("验证码已发送至 {}", to);
        } catch (Exception e) {
            log.error("发送验证邮件时发生异常！", e);
        }
    }

    /**
     * 生成 验证码
     * @return 验证码
     */
    private static String genVerifyCode(){
        return String.valueOf(100000 + new Random().nextInt(899999));
    }
}
