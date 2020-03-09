package com.scut.indoorLocation.utility;

import com.scut.indoorLocation.dto.FingerPrintCollectRequest;
import com.scut.indoorLocation.dto.LocationRequest;
import com.scut.indoorLocation.dto.FingerPrint2D;
import com.scut.indoorLocation.entity.FingerPrintMetadata2D;
import com.scut.knn_algorithm.KnnAlgorithm;
import com.scut.point.Vector2D;
import lombok.extern.slf4j.Slf4j;
import org.fusesource.mqtt.client.QoS;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mingor on 2020/2/17 19:35
 */
@Component
@Slf4j
public class LocationAlgorithmUtil {

    @Resource
    private MqttClientUtil mqttClientUtil;

    @Resource
    private LevelDBUtil levelDBUtil;

    /**
     * 使用mqtt获取定位请求并处理定位请求
     * @param fingerPrintsLibrary 指纹库
     * @param sendTopic （客户端 --> 服务端）
     * @param receiveTopic （服务端 --> 客户端）
     */
    @Async
    public void calculatePosition2D(List<FingerPrint2D> fingerPrintsLibrary, String sendTopic, String receiveTopic) {
        log.info("定位服务调用开始, sendTopic:{}  receiveTopic: {}", sendTopic, receiveTopic);
        try {
            mqttClientUtil.connect();
            mqttClientUtil.subscribe(sendTopic, QoS.AT_MOST_ONCE);
        } catch (Exception e) {
            log.error("mqtt订阅错误: {}", e.getMessage());
            return;
        }

        while (true) {
            try {
                LocationRequest request = mqttClientUtil.receive(LocationRequest.class, 1, TimeUnit.MINUTES);
                if (request.isFinish()){
                    log.info("定位服务调用结束, sendTopic: {}  receiveTopic: {}", sendTopic, receiveTopic);
                    mqttClientUtil.disconnect();
                    return;
                }

                Vector2D result = KnnAlgorithm.knnAlgorithm(fingerPrintsLibrary, request, 7);

                mqttClientUtil.publish(receiveTopic, result, QoS.AT_MOST_ONCE);

                log.info("{}, 定位结果: {}", request.toString(), result.toString());

            } catch (Exception e) {
                log.error("mqtt 接收错误: {}", e.getMessage());
                mqttClientUtil.disconnect();

                return;
            }

        }

    }


    /**
     * 采集指纹信息
     * @param tmpTopic topic （客户端 --> 服务端）
     * @param metadataId 元数据ID
     */
    @Async
    public void collectFingerPrint(String tmpTopic, String metadataId){
        log.info("指纹采集服务调用开始, tmpTopic: {}", tmpTopic);
        try {
            mqttClientUtil.connect();
            mqttClientUtil.subscribe(tmpTopic, QoS.AT_MOST_ONCE);
        } catch (Exception e) {
            log.error("mqtt订阅错误");
            return;
        }

        FingerPrintMetadata2D fingerPrintMetadata2D = levelDBUtil.get(metadataId, FingerPrintMetadata2D.class);
        List<FingerPrint2D> list = fingerPrintMetadata2D.getFingerPrint2DList();

        while (true) {
            try {
                FingerPrintCollectRequest request = mqttClientUtil.receive(FingerPrintCollectRequest.class, 1, TimeUnit.MINUTES);

                if (request.getFinish()){
                    fingerPrintMetadata2D.setFingerPrint2DList(list);
                    levelDBUtil.put(metadataId, fingerPrintMetadata2D);

                    mqttClientUtil.disconnect();

                    log.info("指纹采集服务调用结束, tmpTopic: {}", tmpTopic);
                    return;
                }

                FingerPrint2D fingerPrint2D = FingerPrint2D.builder()
                        .metadataId(metadataId)
                        .x(request.getX())
                        .y(request.getY())
                        .intensities(request.getIntensities())
                        .createTime(LocalDateTime.now())
                        .build();

                list.add(fingerPrint2D);

                log.info("采集指纹: {}", fingerPrint2D.toString());

            } catch (Exception e) {
                fingerPrintMetadata2D.setFingerPrint2DList(list);
                levelDBUtil.put(metadataId, fingerPrintMetadata2D);
                log.error("mqtt receive错误");
                mqttClientUtil.disconnect();
                return;
            }

        }

    }

}
