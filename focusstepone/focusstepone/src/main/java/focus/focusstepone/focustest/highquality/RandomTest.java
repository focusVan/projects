package focus.focusstepone.focustest.highquality;

import java.util.Random;

/**
 * focus Create in 16:40 2018/8/17
 */
public class RandomTest {
    public static void main(String[] args) {
        Random random1 = new Random(1000);
        for (int i = 0; i < 5; i++) {
            System.out.println(random1.nextInt());
        }
        System.out.println("********************");
        Random random2 = new Random(1000);
        for (int i = 0; i < 5; i++) {
            System.out.println(random2.nextInt());
        }
    }
}
