package focus.focusstepone.focustest.highquality;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * focus Create in 10:33 2018/8/27
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        //设置汇集数量，以及汇集完成后的任务
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("到达里程碑1，继续里程碑2");
            }
        });
        new Thread(new Worker(cyclicBarrier, System.currentTimeMillis()),"work1").start();
        new Thread(new Worker(cyclicBarrier, System.currentTimeMillis()),"work2").start();
        new Thread(new Worker(cyclicBarrier, System.currentTimeMillis()),"work3").start();
    }
}

class Worker implements Runnable {
    //关卡
    private CyclicBarrier cyclicBarrier;
    private long beginTime;
    public Worker(CyclicBarrier cyclicBarrier,long beginTime) {
        this.cyclicBarrier = cyclicBarrier;
        this.beginTime = beginTime;
    }
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(new Random(300*1000).nextInt());
            //到达汇合点等待
            System.out.println(Thread.currentThread().getName() + "--到达汇合点." + " 开始任务后" + (System.currentTimeMillis() - beginTime)/1000 + "s到达");
            cyclicBarrier.await();
        } catch (Exception e){}

    }

}
