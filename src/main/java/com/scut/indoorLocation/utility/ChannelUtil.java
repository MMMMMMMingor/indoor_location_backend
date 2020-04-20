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

    /**
     * dispatch 任务
     *
     * @param metadataId ID
     * @param data       数据
     */
    public void dispatcher(String metadataId, String data) {
        LocationRequest request = JSON.parseObject(data, LocationRequest.class);
        try {
            channels.get(metadataId).put(request);
        } catch (InterruptedException e) {
            log.error("分发错误 {}", e.getMessage());
        }
    }

    /**
     * 获取channel
     *
     * @param metadataId ID
     * @return channel
     */
    ArrayBlockingQueue<LocationRequest> getChannel(String metadataId) {
        return channels.get(metadataId);
    }

    /**
     * 判定是否包含channel
     *
     * @param metadataId ID
     * @return 是否包含channel
     */
    public boolean containsChannel(String metadataId) {
        return channels.containsKey(metadataId);
    }

    /**
     * 创建 channel
     *
     * @param metadataId ID
     */
    public void createChannel(String metadataId) {
        ArrayBlockingQueue<LocationRequest> channel = new ArrayBlockingQueue<>(COUNT);
        channels.putIfAbsent(metadataId, channel);
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
