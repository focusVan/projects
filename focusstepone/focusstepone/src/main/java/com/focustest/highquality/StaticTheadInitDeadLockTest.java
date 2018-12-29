package com.focustest.highquality;

/**
 * focus Create in 10:23 2018/12/28
 */
public class StaticTheadInitDeadLockTest {
    static {
        Thread t = new Thread() {
            @Override
            public void run() {
                System.out.println("into run");
                System.out.println(website);
                website = "www.cloud.com";
                System.out.println("out of run");
            }
        };
        t.start();
        try {
           // t.join();
            Thread.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static String website = "www.focus.com";
    public static void main(String[] args) {
        System.out.println(StaticTheadInitDeadLockTest.website);
    }
}
