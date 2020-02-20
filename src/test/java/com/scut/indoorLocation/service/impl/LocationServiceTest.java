package com.scut.indoorLocation.service.impl;

import com.scut.indoorLocation.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


/**
 * Created by Mingor on 2020/2/19 14:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class LocationServiceTest {

    @Resource
    private LocationService locationService;


}