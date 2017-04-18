package org.sixcity.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RebateTask {

    @Scheduled(cron="0 0 1 5 * ?")
    public void executeFileDownLoadTask() {

        // 每月5号凌晨1点,执行工单上传任务
        Thread current = Thread.currentThread();
        System.out.println("定时任务1:"+current.getId());
    }
}
