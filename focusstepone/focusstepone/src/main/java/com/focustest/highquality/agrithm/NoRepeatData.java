package com.focustest.highquality.agrithm;

import java.util.HashSet;
import java.util.Set;

/**
 * focus Create in 10:01 2019/1/11
 */
public class NoRepeatData {
    public static int lengthOfLongestSubstring(String s) {
        int size = s.length();
        int ans = 0;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (uniqueString(s, i, j)) {
                    ans = Math.max(ans, j - i);
                }
            }
        }
        return ans;
    }

    public static boolean uniqueString(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) {
                return false;
            }
            set.add(ch);
        }
        return true;
    }

    public static void main(String[] args) {
        String test = "au";
        System.out.println(lengthOfLongestSubstring(test));
    }
}
