package focus.focusstepone.focustest.highquality;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * focus Create in 16:22 2018/8/27
 */
public class ReadSourceTest {
    public static void main(String[] args) {
        List<String> arrayList = new ArrayList<>();
        List<String> linkList = new LinkedList<>();

        System.out.println(logisticFomule(-8.0));
        System.out.println(logisticFomule(-7.0));
        System.out.println(logisticFomule(-6.0));
        System.out.println(logisticFomule(-4.0));
        System.out.println(logisticFomule(-2.0));
        System.out.println(logisticFomule(0.0));
        System.out.println(logisticFomule(2.0));
        System.out.println(logisticFomule(4.0));
        System.out.println(logisticFomule(6.0));
        System.out.println(logisticFomule(7.0));
        System.out.println(logisticFomule(8.0));
    }

    private static double logisticFomule(double x) {
        return 1 / (1 + Math.pow(Math.E, x));
    }
}
