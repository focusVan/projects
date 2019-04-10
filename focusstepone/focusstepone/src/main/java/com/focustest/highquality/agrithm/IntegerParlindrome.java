package com.focustest.highquality.agrithm;

import java.util.ArrayList;
import java.util.List;

/**
 * focus Create in 16:41 2019/1/15
 */
public class IntegerParlindrome {
    public static boolean isParlindrome(int x) {
        List<Integer> base = new ArrayList<>();
        List<Integer> value = new ArrayList<>();

        int current = x;
        int tmp = 0;
        while (current > 0) {
            Double tmpBase = Math.pow(10.0,Double.parseDouble(String.valueOf(tmp)));
            base.add(tmpBase.intValue());
            value.add(current % 10);
            current /= 10;
            tmp++;
        }
        int ret = 0;
        for (int i = 0; i < base.size(); i++) {
            ret += base.get(i) * value.get(base.size() - i - 1);
        }
        System.out.println(ret);
        return ret == x;
    }

    public static void main(String[] args) {
        System.out.println(isParlindrome(12321));
    }
}
