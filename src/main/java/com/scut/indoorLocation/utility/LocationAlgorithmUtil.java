package com.scut.indoorLocation.utility;

import com.scut.indoorLocation.dto.FingerPrintCollectRequest;
import com.scut.indoorLocation.dto.LocationRequest;
import com.scut.indoorLocation.entity.FingerPrint2D;
import com.scut.indoorLocation.mapper.FingerPrint2DMapper;
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
    private FingerPrint2DMapper fingerPrint2DMapper;

    /**
     * 使用mqtt获取定位请求并处理定位请求
     * @param fingerPrintsLibrary 指纹库
     * @param sendTopic 用户ID（客户端 --> 服务端）
     * @param receiveTopic 指纹库元数据（服务端 --> 客户端）
     */
    @Async
    public void calculatePosition2D(List<FingerPrint2D> fingerPrintsLibrary, String sendTopic, String receiveTopic) {

        LocationRequest request;

        while (true) {
            try {
                request = mqttClientUtil.subscribe(sendTopic, LocationRequest.class, QoS.AT_MOST_ONCE);
                if (request.getFinish())
                    return;

                Vector2D result = KnnAlgorithm.knnAlgorithm(fingerPrintsLibrary, request, 7);

                mqttClientUtil.publish(receiveTopic, result, QoS.AT_MOST_ONCE);

            } catch (Exception e) {
                Thread.currentThread().interrupt();
                log.error("mqtt订阅错误");
            }

        }

    }


    /**
     * 采集指纹信息
     * @param tmpTopic topic
     * @param metadataId 元数据ID
     */
    @Async
    public void collectFingerPrint(String tmpTopic, String metadataId){

        FingerPrintCollectRequest request;

        while (true) {
            try {
                request = mqttClientUtil.subscribe(tmpTopic, FingerPrintCollectRequest.class, QoS.EXACTLY_ONCE);
                if (request.getFinish())
                    return;

                FingerPrint2D fingerPrint2D = FingerPrint2D.builder()
                        .metadataId(metadataId)
                        .x(request.getX())
                        .y(request.getY())
                        .ap1(request.getAp1())
                        .ap2(request.getAp2())
                        .ap3(request.getAp3())
                        .createTime(LocalDateTime.now())
                        .build();

                fingerPrint2DMapper.insert(fingerPrint2D);

                log.info(fingerPrint2D.toString());

            } catch (Exception e) {
                Thread.currentThread().interrupt();
                log.error("mqtt订阅错误");
            }

        }

    }

}
