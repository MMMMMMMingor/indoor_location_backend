package com.scut.indoorLocation.handle;

import com.scut.indoorLocation.utility.ChannelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by Mingor on 2020/4/12 22:36
 */
@Controller
@Slf4j
public class LocationHandler {

    @Resource
    private ChannelUtil channelUtil;

    @MessageMapping("/service/request/{metadataId}")
    public void indoorLocationService(
            @DestinationVariable String metadataId,
            @Payload String body) {
        channelUtil.dispatcher(metadataId, body);
    }


}
