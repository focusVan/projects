package com.focustest.highquality;

import java.util.Arrays;

/**
 * focus Create in 10:39 2018/12/7
 */
public class CommandTest {
    public int[] process(IntArrayProducer cmd, int length) {
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = cmd.produce();
        }
        return result;
    }

    public static void main(String[] args) {
        CommandTest ct = new CommandTest();
        final int seed = 5;
        int[] result = ct.process(new IntArrayProducer() {
            @Override
            public int produce() {
                return (int)Math.round(Math.random() * seed);
            }
        }, 6);

        System.out.println(Arrays.toString(result));
    }
}

interface IntArrayProducer {
    int produce();
}
