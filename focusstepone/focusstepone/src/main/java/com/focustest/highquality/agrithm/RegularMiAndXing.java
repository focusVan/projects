package com.focustest.highquality.agrithm;

/**
 * focus Create in 15:28 2019/1/21
 */
public class RegularMiAndXing {
    public static boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        if ("".equals(p)) {
            return true;
        }



        if ("".equals(s) && !"".equals(p)) {
            return false;
        }
        if (!p.contains(".") && !p.contains("*")) {
            return p.contains(s);
        }
        if (p.contains(".") && !p.contains("*")) {
            if (p.length() < s.length() - 1) {
                return false;
            }
            boolean flag = true;
            char[] sArray = s.toCharArray();
            char[] pArray = p.toCharArray();
            for (int i = 0; i < s.length() - 1; i++) {
                if (sArray[i] != pArray[i] && sArray[i] != '.') {
                    flag = false;
                }
            }
            return flag;
        }
        if (p.contains("*") && !p.contains(".")) {
            String[] array = p.split("\\*");    
            if (array.length == 0) {
                return true;
            }
            if (array.length == 1) {
                if (s.contains(array[0])) {
                    return true;
                }
            }
            for (int i = 0; i < array.length - 1; i++) {
                if (s.contains(array[i]) && s.contains(array[i+1])) {
                    return true;
                }
            }
            return false;
        }
        if (p.contains(".") && p.contains("*")) {
            String[] array = p.split("\\*");
            if (array.length == 0) {
                return true;
            }
            if (array.length == 1) {
                if (s.contains(array[0]) || ".".equals(array[0])) {
                    return true;
                }
            }
            for (int i = 0; i < array.length - 1; i++) {
                if ((s.contains(array[i]) || ".".equals(array[i])) && (s.contains(array[i+1]) || ".".equals(array[i]))) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }
    public static void main(String[] args){
        String s = "ab", p = ".*";
        System.out.println(isMatch(s, p));
    }
}
