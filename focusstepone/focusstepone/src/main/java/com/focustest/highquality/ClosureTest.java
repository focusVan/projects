package com.focustest.highquality;

/**
 * focus Create in 10:51 2018/12/7
 */
public class ClosureTest {
    public static void main(String[] args) {
        String str = "Java";
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        System.out.println(str);
                        try {
                            Thread.sleep(100);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }
}
