package com.scut.indoorLocation;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

/**
 * Created by Mingor on 2020/2/20 16:32
 */
@SpringBootTest
@Slf4j
public class JustText {

    @Test
    public void test(){
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        log.info(uuid);
    }

}
