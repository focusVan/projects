package com.focustest.highquality;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * focus Create in 10:05 2018/8/27
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //参赛人数
        int num = 10;
        //参跑人数协调器
        CountDownLatch endCountDownLatch = new CountDownLatch(num);
        //发令枪
        CountDownLatch beginCountDownLach = new CountDownLatch(1);
        //每个跑步走一个跑道
        ExecutorService executorService = Executors.newFixedThreadPool(num);
        //记录比赛成绩
        List<Future<Integer>> futures = new ArrayList<>();
        //跑步者就位，所有线程处于等待状态
        for (int i = 0; i < num; i++) {
            futures.add(executorService.submit(new Runner(beginCountDownLach, endCountDownLatch, System.currentTimeMillis())));
        }
        //发令枪响，跑步者开始跑步
        beginCountDownLach.countDown();
        //等待所有跑步走跑完全程
        endCountDownLatch.await();
        //统计成绩
        int count = 0;
        for (int i = 0; i < futures.size(); i++) {
            count += futures.get(i).get();
            System.out.println("the runner of " + i + " : " + futures.get(i).get());
        }
        System.out.println("avrage : " + count / num);
    }
}

class Runner implements Callable<Integer> {
    //开始信号
    private CountDownLatch begin;
    //结束型号
    private CountDownLatch end;
    //开始时间
    private long beginTime;
    public Runner (CountDownLatch begin, CountDownLatch end, long beginTime) {
        this.begin = begin;
        this.end = end;
        this.beginTime = beginTime;
    }

    @Override
    public Integer call() throws Exception{
        //跑步成绩
        int score = new Random().nextInt(26);
        //等待发令枪响起
        begin.await();
        //开跑
        TimeUnit.SECONDS.sleep(score);
        System.out.println("runner end after : " + (System.currentTimeMillis() - beginTime)/1000);
        //跑完全程
        end.countDown();
        return score;
    }
}