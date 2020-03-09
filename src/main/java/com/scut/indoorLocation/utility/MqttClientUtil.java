package com.scut.indoorLocation.utility;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import lombok.extern.slf4j.Slf4j;
import org.fusesource.mqtt.client.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;


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

    private ThreadLocal<BlockingConnection> connectionThreadLocal = new ThreadLocal<>();

    /**
     * Bean 初始化触发函数
     * @throws Exception 连接异常
     */
    public void connect() throws Exception {
        MQTT mqtt = new MQTT();
        mqtt.setHost(MQTT_HOST, MQTT_PORT);
        BlockingConnection connection = mqtt.blockingConnection();
        connection.connect();
        connectionThreadLocal.set(connection);
    }

    /**
     * 推送消息
     * @param topic topic
     * @param message 消息
     * @param qos 消息传播等级
     */
    public <T> void publish (String topic, T message, QoS qos) throws Exception {

        Object json = JSON.toJSON(message);

        connectionThreadLocal.get().publish(topic, json.toString().getBytes(), qos, false);
    }

    /**
     * 订阅topic
     * @param topic 主题
     * @param qos 消息传播等级
     */
    public void subscribe(String topic,  QoS qos) throws Exception {
        BlockingConnection connection = connectionThreadLocal.get();
        byte[] subscribe = connection.subscribe(new Topic[]{new Topic(topic, qos)});

        log.info(new String(subscribe, Charsets.UTF_8));

    }

    /**
     * @param clazz Class
     * @param <T> pojo类
     * @return pojo
     */
    public <T> T receive(Class<T> clazz, long amount, TimeUnit timeUnit) throws Exception {
        BlockingConnection connection = connectionThreadLocal.get();

        Message message = connection.receive(amount, timeUnit);

        return JSON.parseObject(message.getPayload(), clazz);
    }

//    /**
//     * 取消订阅topic
//     * @param topic 主题
//     */
//    public void unsubscribe(String topic) throws Exception {
//        this.connection.unsubscribe(new String[]{topic});
//    }

    /**
     * Bean销毁触发函数
     */
    void disconnect(){
        try {
            connectionThreadLocal.get().disconnect();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
