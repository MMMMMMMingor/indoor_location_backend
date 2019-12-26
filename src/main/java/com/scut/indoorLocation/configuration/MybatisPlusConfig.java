package com.scut.indoorLocation.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Mingor on 2019/11/19 9:18
 */
@Configuration
@MapperScan(basePackages = {"com.scut.indoorLocation.mapper"})
public class MybatisPlusConfig {


}
