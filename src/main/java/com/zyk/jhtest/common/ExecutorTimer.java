package com.zyk.jhtest.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class ExecutorTimer {

    private final Logger log = LoggerFactory.getLogger(ExecutorTimer.class);

    private ExecutorService executorService;

    public ExecutorTimer() {
        executorService = Executors.newSingleThreadExecutor();
    }

    public void runTask(Runnable task, long timeout, TimeUnit timeType) {
        Future<?> future = executorService.submit(task);
        try {
            future.get(timeout, timeType);

        } catch (TimeoutException e) {
            log.error(e.toString());
            //记录此次方法; 超时
        } catch (InterruptedException e) {
            log.error(e.toString());
            //记录此次方法;
        } catch (ExecutionException e) {
            log.error(e.toString());//记录此次方法;

        } finally {
            // 取消任务不会影响结果
            future.cancel(true);
        }
    }
}
