package com.scut.indoorLocation.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Mingor on 2020/4/12 23:40
 */
@Configuration
public class ThreadPoolConfig {

    @Bean(name ="location-thread-pool")
    public ThreadPoolTaskExecutor locationThreadPool() {

        ThreadPoolTaskExecutor executor =new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(2);

        executor.setMaxPoolSize(4);

        executor.setQueueCapacity(8);

        executor.setKeepAliveSeconds(60);

        executor.setThreadNamePrefix("location-");
        //当任务数量超过MaxPoolSize和QueueCapacity时使用的策略，该策略是又调用任务的线程执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        executor.initialize();

        return executor;
    }

}
