package com.scut.indoorLocation.utility;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.fusesource.mqtt.client.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


/**
 * MQTT客户端
 * Created by Mingor on 2020/1/8 20:40
 */
@Component
@Slf4j
public class MqttClientUtil {

    @Value("${mqtt.host}")
    private String MQTT_HOST;

    @Value("${mqtt.port}")
    private int MQTT_PORT;

    private BlockingConnection connection;

    /**
     * Bean 初始化触发函数
     * @throws Exception 连接异常
     */
    @PostConstruct
    public void initMqttClientUtil() throws Exception {
        MQTT mqtt = new MQTT();
        mqtt.setHost(MQTT_HOST, MQTT_PORT);
        this.connection = mqtt.blockingConnection();
        this.connection.connect();
    }

    /**
     * 推送消息
     * @param topic topic
     * @param message 消息
     * @param qos 消息传播等级
     */
    public <T> void publish (String topic, T message, QoS qos) throws Exception {

        Object json = JSON.toJSON(message);

        this.connection.publish(topic, json.toString().getBytes(), qos, false);
    }

    /**
     * 订阅topic
     * @param topic 主题
     * @param clazz Class
     * @param <T> pojo类
     * @param qos 消息传播等级
     * @return pojo
     */
    public <T> T subscribe(String topic, Class<T> clazz, QoS qos) throws Exception {

        this.connection.subscribe(new Topic[]{new Topic(topic, qos)});

        Message message = this.connection.receive();

        return JSON.parseObject(message.getPayload(), clazz);
    }

    /**
     * Bean销毁触发函数
     */
    @PreDestroy
    void disconnect(){
        try {
            this.connection.disconnect();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
