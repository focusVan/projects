package com.focustest.highquality.agrithm;

import org.apache.commons.collections.list.TreeList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * focus Create in 10:52 2019/1/11
 */
public class Median {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            list.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            list.add(nums2[i]);
        }
        Collections.sort(list);
        int size  = list.size();
        int pos = size / 2;
        if (size % 2 == 0) {
            return (Double.parseDouble(String.valueOf(list.get(pos) ))+ Double.parseDouble(String.valueOf(list.get(pos - 1)))) / 2;
        } else {
            return list.get(pos);
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1,3}, nums2 = {2,4};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }
}
