package com.focustest.highquality.agrithm;

/**
 * focus Create in 16:45 2019/1/14
 */
public class String2Int {
    public static int myAtoi(String str) {
        if (str == null || "".equals(str.trim())) {
            return 0;
        }
        String string = str.trim();
        char[] array = string.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            if (builder.length() == 0 && isMinus(array[i])){
                builder.append(array[i]);
            }
            if (isNum(array[i])){
                builder.append(array[i]);
            }
            continue;
        }
        if ("".equals(builder.toString()) || "-".equals(builder.toString())) {
            return 0;
        }

        return Integer.parseInt(builder.toString());
        
    }

    private static boolean isNum(char c) {
        return  c >= '0' && c <= '9';
    }

    private static boolean isMinus(char c) {
        return '-' == c;
    }

    public static void main(String[] args) {
        String temp = "20000000000000000000";
        System.out.println(myAtoi(temp));
    }
}
