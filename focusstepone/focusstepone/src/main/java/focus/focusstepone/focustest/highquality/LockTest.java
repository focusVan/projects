package focus.focusstepone.focustest.highquality;

import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * focus Create in 16:27 2018/8/24
 */
public class LockTest {
    public static void main(String[] args) throws Exception {
        //运行显示任务
        //runTask(TaskWithLock.class);
        //运行内部锁任务
        //runTask(TaskWithSync.class);

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    LockTemp lockTemp = new LockTemp();
                    lockTemp.write();
                    lockTemp.read();
                }
            });
        }

    }

    public static void runTask(Class<? extends  Runnable> clz) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println("*********** begin: " + clz.getName());
        //启动三个线程
        for (int i = 0; i < 3; i++) {
            executorService.submit(clz.newInstance());
        }
        //等待足够长的时间，然后关闭执行
        TimeUnit.MILLISECONDS.sleep(10 * 1000);
        System.out.println("************ end: " + clz.getName());

        executorService.shutdown();
    }
}

class Task {
    public void doSomething() {
        try {
            //每个线程等待2秒，此时线程的状态为WAITTING
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getState());
        } catch (Exception e) {}

        StringBuffer stringBuffer = new StringBuffer();
        //线程名称
        stringBuffer.append("thread name: " + Thread.currentThread().getName());
        //运行时间
        stringBuffer.append(", operating time: " + Calendar.getInstance().get(Calendar.SECOND) + " s");
        System.out.println(stringBuffer);
    }
}

//显示锁任务
class TaskWithLock extends Task implements Runnable {
    //声明显示锁
    private final Lock lock = new ReentrantLock();
    @Override
    public void run() {
        try {
            //开始锁定
            lock.lock();
            doSomething();
        } finally {
            //释放锁
            lock.unlock();
        }
    }
}

//内部锁任务
class TaskWithSync extends Task implements Runnable {
    @Override
    public void run() {
        //内部锁
        synchronized ("A") {
            doSomething();
        }
    }
}

//细粒度控制
class LockTemp {
    private final static ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
    //读锁
    private final static Lock readLock = rw.readLock();
    //写锁
    private final static Lock writeLock = rw.writeLock();
    //并行读操作
    public void read() {
        try {
            readLock.lock();
            Thread.sleep(1000);
            System.out.println("read...");
        } catch (Exception e){}
        finally {
            readLock.unlock();
        }
    }

    //串行写操作
    public void write() {
        try {
            writeLock.lock();
            Thread.sleep(1000);
            System.out.println("write...");
        } catch (Exception e){}
        finally {
            writeLock.unlock();
        }
    }
}
