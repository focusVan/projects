package focus.focusstepone.focustest.highquality;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * focus Create in 9:55 2018/8/27
 */
public class SpinLockTest {
    public static void main(String[] args){

    }
}

class SpinLock {
    public static final Lock lock = new ReentrantLock();
    public void spinLockMethod() {
        try {
            //立刻获得锁，或者2秒等待锁资源（自旋2秒）
            if (lock.tryLock(2, TimeUnit.SECONDS)) {
                System.out.println("自旋或立即获得了锁");
            }
        } catch (Exception e) {

        } finally {
            //释放锁
            lock.unlock();
        }
    }
}
