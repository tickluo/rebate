package org.sixcity.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String args[]) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(TaskExecutorConfig.class);
        AsyncTaskService asyncTaskService = ctx.getBean(AsyncTaskService.class);

        for (int i = 0; i < 10; i++) {
            asyncTaskService.executeAsyncTask(i);
            asyncTaskService.executeAsyncTaskPlus(i);
        }
        System.out.println(ctx.getEnvironment().getProperty("os.name"));
        ctx.close();
    }
}
