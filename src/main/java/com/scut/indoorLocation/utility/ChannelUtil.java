package com.scut.indoorLocation.utility;

import com.alibaba.fastjson.JSON;
import com.scut.indoorLocation.dto.LocationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Mingor on 2020/4/19 22:47
 */
@Component
@Slf4j
public class ChannelUtil {

    private static final Map<String, ArrayBlockingQueue<LocationRequest>> channels = new ConcurrentHashMap<>();

    private static final int COUNT = 50;

    @Resource
    private LocationAlgorithmUtil locationAlgorithmUtil;

    /**
     * 添加channel
     *
     * @param metadataId ID
     */
    private void createIfAbsent(String metadataId) {
        if (!channels.containsKey(metadataId)) {
            ArrayBlockingQueue<LocationRequest> channel = new ArrayBlockingQueue<>(COUNT);
            channels.putIfAbsent(metadataId, channel);
            locationAlgorithmUtil.calculatePosition2D(channel, metadataId);
        }
    }

    /**
     * dispatch 任务
     *
     * @param metadataId ID
     * @param data       数据
     */
    public void dispatcher(String metadataId, String data) {
        createIfAbsent(metadataId);
        LocationRequest request = JSON.parseObject(data, LocationRequest.class);
        try {
            channels.get(metadataId).put(request);
        } catch (InterruptedException e) {
            log.error("分发错误 {}", e.getMessage());
        }
    }

    /**
     * 删除channel
     *
     * @param metadataId ID
     */
    public void removeChannel(String metadataId) {
        channels.remove(metadataId);
    }

}
