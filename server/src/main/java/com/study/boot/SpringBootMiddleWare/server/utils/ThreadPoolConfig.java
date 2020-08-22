package com.study.boot.SpringBootMiddleWare.server.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @ClassName ThreadPoolConfig
 * @Description: 线程池的配置
 * @Author lxl
 * @Date 2020/8/21
 * @Version V1.0
 **/
@Configuration
public class ThreadPoolConfig {

    /**
     * 注册一个线程池
     * @return
     */
    @Bean("taskExecutor")
    public Executor threadPoolExecutorTest(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(5);
        threadPoolTaskExecutor.setMaxPoolSize(10);
        threadPoolTaskExecutor.setQueueCapacity(6);

        return threadPoolTaskExecutor;
    }

}
