package focus.focusstepone.focustest.highquality;

/**
 * focus Create in 14:34 2018/8/24
 */

// 实验证明volatile还是可靠的

public class VolatileTest {
    public static void main(String[] args) throws InterruptedException {
        //最大的循环次数
        int value = 1000;
        //循环次数，防止出现无限循环造成死机
        int loops = 0;
        //主线程组，用户估计活动线程数
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        while (loops++ < value) {
            //共享资源清零
            UnsafeThread unsafeThread = new UnsafeThread();
            for (int i = 0; i < value; i++) {
                new Thread(unsafeThread).start();
            }
            //先等15毫秒，等待活动线程数量成为1
            do {
                Thread.sleep(15);
            } while (threadGroup.activeCount() != 1);
            // 检查实际值与理论值是否一致
            if (unsafeThread.getCount() != value) {
                //出现线程不安全的情况
                System.out.println("loops at: " + loops + " then, unsafe, count = " + unsafeThread.getCount());
                System.exit(0);
            }
        }
    }
}

class UnsafeThread implements Runnable {
    //共享资源
    private volatile int count = 0;
    @Override
    public void run() {
        //增加CPU的繁忙程度
        for (int i = 0; i < 1000; i++) {
            Math.hypot(Math.pow(93322121, i), Math.cos(i));
        }
        count++;
    }
    public int getCount() {
        return count;
    }
}
