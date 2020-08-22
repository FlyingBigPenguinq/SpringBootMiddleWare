package com.study.boot.SpringBootMiddleWare.server.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName CommonScheduler
 * @Description: TODO
 * @Author lxl
 * @Date 2020/8/20
 * @Version V1.0
 **/
@Component
public class CommonScheduler {

    private Logger log = LoggerFactory.getLogger(CommonScheduler.class);
    private final String CRON = "0 0 0 * * ? *";

    @Scheduled(cron = CRON)
    @Async("taskExecutor")
    public void schedulerOne(){
        log.info("----当定时任务1 ");

        try {
            Thread.sleep(2000);
        }catch (Exception e){}
    }

    @Scheduled(cron = CRON)
    @Async("taskExecutor")
    public void schedulerTwo(){
        log.info("----当定时任务2 ");

        try {
            Thread.sleep(2000);
        }catch (Exception e){}
    }

    @Scheduled(cron = CRON)
    @Async("taskExecutor")
    public void schedulerThree(){
        log.info("----当定时任务3 ");

        try {
            Thread.sleep(2000);
        }catch (Exception e){}
    }
}
