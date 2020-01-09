package com.scut.indoorLocation.controller;

import com.scut.indoorLocation.dto.MqttPayload;
import com.scut.indoorLocation.utility.MqttClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * mqtt 后台线程
 * Created by Mingor on 2020/1/8 21:04
 */
@Component
@Slf4j
public class MqttController implements Runnable {

    @Resource
    private MqttClientUtil mqttClientUtil;

    @PostConstruct
    public void init(){
        //启动线程实例
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true){
            try {
                MqttPayload payload = mqttClientUtil.subscribe("/test", MqttPayload.class);
                log.info("mqtt subscribe {}", payload.getMsg());
            } catch (Exception e) {
                log.error("{}", e.getMessage());
            }
        }
    }
}
