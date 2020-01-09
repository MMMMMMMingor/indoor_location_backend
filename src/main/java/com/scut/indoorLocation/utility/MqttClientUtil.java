package com.scut.indoorLocation.utility;

import com.alibaba.fastjson.JSON;
import org.fusesource.mqtt.client.*;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;

/**
 * MQTT客户端
 * Created by Mingor on 2020/1/8 20:40
 */
@Component
public class MqttClientUtil {

    private MQTT mqtt;

    public MqttClientUtil() throws URISyntaxException {
        mqtt = new MQTT();
        mqtt.setHost("39.99.131.85", 1883);
    }

    /**
     * 推送消息
     * @param topic topic
     * @param message 消息
     */
    public void publish (String topic, String message) throws Exception {
        BlockingConnection connection = mqtt.blockingConnection();
        connection.connect();

        connection.publish(topic, message.getBytes(), QoS.AT_LEAST_ONCE, false);
    }

    /**
     * 订阅topic
     * @param topic 主题
     * @param clazz Class
     * @param <T> pojo类
     * @return pojo
     */
    public <T> T subscribe(String topic, Class<T> clazz) throws Exception {
        BlockingConnection connection = mqtt.blockingConnection();
        connection.connect();
        connection.subscribe(new Topic[]{new Topic("/test", QoS.AT_LEAST_ONCE)});

        Message message = connection.receive();

        return JSON.parseObject(message.getPayload(), clazz);
    }
}
