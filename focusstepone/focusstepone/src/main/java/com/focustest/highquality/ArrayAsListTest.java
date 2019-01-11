package com.focustest.highquality;

import java.util.Arrays;
import java.util.List;

/**
 * focus Create in 14:20 2018/8/22
 */
public class ArrayAsListTest {
    public static void main(String[] args) {
        int[] array = {1,2,3,4,5};
        List list = Arrays.asList(array);
        System.out.println(list.size());
        System.out.println(list.get(0));

        System.out.println("*******************");

        Integer[] arrayInteger = {4,2,3,1,5};
        List listInteger = Arrays.asList(arrayInteger);
        System.out.println(listInteger.size());
        System.out.println(listInteger.get(0));
        //listInteger.add(9); 该list作为Arrays的私有静态内部类，未实现add方法


        Arrays.sort(arrayInteger);
        System.out.println(arrayInteger[0]);
        System.out.println((2 << 8 - 1) - 1);
        System.out.println(Math.pow(2, 8) - 1);
    }
}
