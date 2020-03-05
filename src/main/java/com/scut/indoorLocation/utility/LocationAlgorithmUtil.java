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
        log.info("定位服务调用开始, sendTopic:{}  receiveTopic{}", sendTopic, receiveTopic);

        while (true) {
            try {
                LocationRequest request = mqttClientUtil.subscribe(sendTopic, LocationRequest.class, QoS.AT_MOST_ONCE);
                if (request.isFinish()){
                    log.info("定位服务调用结束, sendTopic: {}  receiveTopic: {}", sendTopic, receiveTopic);
                    mqttClientUtil.unsubscribe(sendTopic);
                    return;
                }

                Vector2D result = KnnAlgorithm.knnAlgorithm(fingerPrintsLibrary, request, 7);

                mqttClientUtil.publish(receiveTopic, result, QoS.AT_MOST_ONCE);

                log.info("{}, 定位结果: {}", request.toString(), result.toString());

            } catch (Exception e) {
                Thread.currentThread().interrupt();
                log.error("mqtt订阅错误");
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

        FingerPrintMetadata2D fingerPrintMetadata2D = levelDBUtil.get(metadataId, FingerPrintMetadata2D.class);
        List<FingerPrint2D> list = fingerPrintMetadata2D.getFingerPrint2DList();

        while (true) {
            try {
                FingerPrintCollectRequest request = mqttClientUtil.subscribe(tmpTopic, FingerPrintCollectRequest.class, QoS.AT_MOST_ONCE);
                if (request.getFinish()){
                    fingerPrintMetadata2D.setFingerPrint2DList(list);
                    levelDBUtil.put(metadataId, fingerPrintMetadata2D);
                    mqttClientUtil.unsubscribe(tmpTopic);
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
                log.error("mqtt订阅错误");

                return;
            }

        }

    }

}
