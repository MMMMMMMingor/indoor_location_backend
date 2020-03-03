package com.scut.indoorLocation.utility;

import com.scut.indoorLocation.entity.FingerPrintMetadata2D;
import com.scut.indoorLocation.entity.UserBasic;
import com.scut.indoorLocation.utility.LevelDBUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * Created by Mingor on 2019/11/19 12:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LevelDBTest {

    @Autowired
    private LevelDBUtil levelDBUtil;

    @Test
    public void test1() {
        String key = "key";
        String value = "value";
        levelDBUtil.put(key, value);

        String result = levelDBUtil.get(key, String.class);
        log.info(result);
    }

    @Test
    public void test2() {
        List<String> keys = levelDBUtil.getKeys();
        for (String key : keys) {
            log.info(key + ": " + levelDBUtil.get(key));
        }
    }

    @Test
    public void test3() {
        List<String> keys = levelDBUtil.getKeys();
        for (String key : keys) {
            String value = levelDBUtil.get(key, String.class);
            log.info(value);
        }
    }

    @Test
    public void test4() {
        UserBasic userBasic = UserBasic.builder()
                .id("1")
                .username("test")
                .password("123456")
                .build();

        levelDBUtil.put("key", userBasic);
        UserBasic ming = levelDBUtil.get("key", UserBasic.class);
        log.info(ming.toString());
    }

    @Test
    public void test5() {
        List<String> keys = levelDBUtil.getKeys();
        for (String key : keys) {
            levelDBUtil.delete(key);
            log.info("delete : " + key);
        }
    }

}
