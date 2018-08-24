package focus.focusstepone.focustest.highquality;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * focus Create in 15:17 2018/8/22
 */
public class ListTravelsalTest {
    public static void main(String[] args) {
        int stuNum = 80 * 10000;
        List<Integer> scores = new ArrayList<>();
        for (int i = 0; i < stuNum; i++) {
            scores.add(new Random().nextInt(150));
        }


        long forStart = System.currentTimeMillis();
        System.out.println("for循环平均分: " + forAverage(scores) + " " + "for循环耗时:" + (System.currentTimeMillis() - forStart));

        long foreachStart = System.currentTimeMillis();
        System.out.println("foreach循环平均分: " + foreachAverage(scores) + " " + "foreach循环耗时:" + (System.currentTimeMillis() - foreachStart));

    }

    private static int forAverage(List<Integer> scores) {
        int sum = 0;
        for (int i = 0; i < scores.size(); i++) {
            sum += scores.get(i);
        }
        return sum / scores.size();
    }

    private static int foreachAverage(List<Integer> scores) {
        int sum = 0;
        for (int i : scores) {
            sum += i;
        }
        return sum / scores.size();
    }
}
