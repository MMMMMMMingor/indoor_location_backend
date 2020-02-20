package com.scut.indoorLocation.utility;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by Mingor on 2020/2/20 16:48
 */
@Component
public class UUIDUtil {

    public String get32LengthString(){
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        return uuid;
    }

}
