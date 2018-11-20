package com.focustest.highquality;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * focus Create in 15:04 2018/8/24
 */
public class ExecutorServiceTest {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(3,3,0L, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>());
        for (int i = 0; i < 10; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }
        executorService.shutdown();
    }
}
