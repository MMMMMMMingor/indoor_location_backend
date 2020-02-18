package com.scut.indoorLocation.entity;

import com.scut.indoorLocation.mapper.AccessPointMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Mingor on 2020/2/18 20:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AccessPointDaoTest {

    @Resource
    private AccessPointMapper accessPointMapper;

    @Test
    public void testInsert(){
        AccessPoint accessPoint = new AccessPoint("00-01-6C-06-A6-29", "wifi名字", 10.0, 10.0);
        accessPointMapper.insert(accessPoint);

        AccessPoint accessPoint1 = accessPointMapper.selectById("00-01-6C-06-A6-29");
        assertEquals(accessPoint, accessPoint1);
    }


}