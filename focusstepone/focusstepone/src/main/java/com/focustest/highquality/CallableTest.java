package com.focustest.highquality;

import java.util.concurrent.*;

/**
 * focus Create in 14:52 2018/8/24
 */
public class CallableTest {
    public static void main(String[] args) throws Exception {
        //生成一个单线程异步执行器
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //线程执行后的期望值
        Future<Integer> future = executorService.submit(new TaxCalculator(100));
        while(!future.isDone()) {
            //还没有运算完成，等待200毫秒
            TimeUnit.MILLISECONDS.sleep(200);
            //输出进度符号
            System.out.println("loading...");
        }
        System.out.println("calculator finished,tax is: " + future.get());
        executorService.shutdown();
    }
}


//税款计算器
class TaxCalculator implements Callable<Integer> {
    //本金
    private int seedMoney;
    //接收主线程提供的参数
    public TaxCalculator(int seedMoney) {
        this.seedMoney = seedMoney;
    }

    @Override
    public Integer call() throws Exception {
        //复杂计算，运行一次需要10秒
        TimeUnit.MILLISECONDS.sleep(10 * 1000);
        return seedMoney / 10;
    }
}