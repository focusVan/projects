package com.focustest.highquality.agrithm;


import javafx.util.Pair;

/**
 * focus Create in 14:23 2019/1/11
 */
public class LongestPalindrome {
    public static String longestPalindrome(String s) {
        Pair<Integer, Integer> posPair = palindromeIndex(s);
        return s.substring(posPair.getKey(), posPair.getValue());
    }

    private static Pair<Integer, Integer> palindromeIndex(String s) {
        int start = 0, end = 0, maxLength = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j <= length; j++) {
                if (isPalindrome(s, i, j)) {
                    if (j - i > maxLength) {
                        maxLength = j - i;
                        start = i;
                        end = j;
                    }
                }
            }
        }
        return new Pair<>(start, end);
    }

    private static boolean isPalindrome(String s, int start, int end) {
        String substr = s.substring(start, end);
        int mediaPos = substr.length()/2;
        for (int i = 0; i < mediaPos; i++) {
            if (substr.charAt(i) != substr.charAt(substr.length() - i - 1)){
                return false;
            }
        }
        return true;
    }

    public static String longestPalindromeOffice(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenters(s, i, i);
            int len2 = expandAroundCenters(s, i, i+1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1)/2;
                end = i + len/2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenters(String s, int left, int right) {
        int L = left, R = right;
        while(L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    public static void main(String[] args) {
        String test = "aaccbccba";
        System.out.println(longestPalindromeOffice(test));
    }
}
