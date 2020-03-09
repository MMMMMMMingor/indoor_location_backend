package com.scut.indoorLocation.utility;

import com.scut.indoorLocation.dto.LocationRequest;
import com.scut.point.Vector2D;
import lombok.extern.slf4j.Slf4j;
import org.fusesource.mqtt.client.QoS;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


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
        Vector2D result = new Vector2D(10.0, 12.0);
        mqttClientUtil.publish("/test", result, QoS.EXACTLY_ONCE);
    }

    @Test
    void subscribe() {

        new Thread(() -> {
            LocationRequest locationRequest = new LocationRequest();
            do {
                try {
                    mqttClientUtil.subscribe("/test1",  QoS.AT_MOST_ONCE);
                    locationRequest = mqttClientUtil.receive( LocationRequest.class, 1, TimeUnit.MINUTES);
                    log.info(locationRequest.toString());
                } catch (Exception e) {
                    log.error(e.toString());
                }
            } while (!locationRequest.isFinish());
        }).start();


        try {
            int read = System.in.read();
            log.info(String.valueOf(read));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}