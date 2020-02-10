package com.scut.indoorLocation.service.impl;

import com.scut.indoorLocation.service.MailService;
import com.scut.indoorLocation.utility.LevelDBUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


/**
 * Created by Mingor on 2020/1/13 14:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class MailServiceTest {

    @Resource
    private MailService mailService;

    @Resource
    private LevelDBUtil levelDBUtil;

    @Test
    void sendMail() {
        mailService.sendMail("947462457@qq.com", "邮件验证码");

        log.info(levelDBUtil.get("947462457@qq.com", String.class));
    }
}