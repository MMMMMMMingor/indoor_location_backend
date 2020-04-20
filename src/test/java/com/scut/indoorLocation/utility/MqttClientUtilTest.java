package com.scut.indoorLocation.utility;

import com.scut.indoorLocation.dto.LocationRequest;
import com.scut.point.Vector2D;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


/**
 * Created by Mingor on 2020/2/19 20:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class MqttClientUtilTest {

    @Resource
    private MqttClientUtil mqttClientUtil;

    @Test
    void publish() throws Exception {
    }

    @Test
    void subscribe() {

    }
}