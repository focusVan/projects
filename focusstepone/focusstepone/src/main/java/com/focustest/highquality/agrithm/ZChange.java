package com.focustest.highquality.agrithm;

import java.util.ArrayList;
import java.util.List;

/**
 * focus Create in 14:04 2019/1/14
 */
public class ZChange {
    public static final String SPACE = " ";
    //按行排序
    public static String convertOfficeA(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        List<StringBuilder> rows = new ArrayList<>();
        //行数
        for (int i = 0; i < Math.min(numRows, s.length()); i++) {
            rows.add(new StringBuilder());
        }

        int curRow = 0;
        boolean goingDown = false;
        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            //只有向上移动到最上面一行curRow = 0或者向下移动到最下面一行curRow = numRows - 1，移动方向才会改变
            if (curRow == 0 || curRow == numRows - 1) {
                goingDown = !goingDown;
            }
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) {
            ret.append(row);
        }
        return ret.toString();
    }

    public static void main(String[] args) {
        String s = "LEETCODEISHIRING";
        int numRows = 3;
        System.out.println(convertOfficeA(s, numRows));
    }
}
