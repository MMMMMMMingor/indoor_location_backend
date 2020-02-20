package com.scut.indoorLocation.entity;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scut.indoorLocation.mapper.AccessPointMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

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
        AccessPoint accessPoint = AccessPoint.builder()
                .bssid("00-01-6C-06-A6-29")
                .ssid("wifi名字")
                .x(10.0)
                .y(10.0)
                .build();

        accessPointMapper.insert(accessPoint);

        QueryWrapper<AccessPoint> wrapper = new QueryWrapper<AccessPoint>().eq("bssid", "00-01-6C-06-A6-29")
                .orderByDesc("create_time");

        List<AccessPoint> accessPoints = accessPointMapper.selectList(wrapper);
        AccessPoint accessPoint1 = accessPoints.get(0);
        assertEquals(accessPoint.getId(), accessPoint1.getId());
    }


}