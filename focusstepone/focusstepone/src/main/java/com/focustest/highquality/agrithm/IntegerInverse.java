package com.focustest.highquality.agrithm;

/**
 * 题目：给出一个32位的有符号整数，你需要将这个整数中每位上的数字进行反转，溢出返回0
 * 思路：我们可以一次构建反转整数的一位数字，在这样做的时候，我们可以预先检查向原整数附加另一位数字是否会导致溢出
 * 算法：反转整数的方法可以与反转字符串进行类比，我们想重复弹出“x”的最后一位数字，并将它推入到rev的后面
 *      要在没有辅助堆栈/数组的帮助下 弹出 和 推入 数字，我们可以使用数学方法
 *      pop operation: pop = x % 10; x /= 10; push operation: temp = rev * 10 + pop, rev = temp
 *      但是，这种方法很危险，因为当temp = rev * 10 + pop会导致溢出
 *      所以：1. 如果temp = rev * 10 + pop导致溢出，那么一定有个rev >= INTMAX / 10;
 *          2. 如果rev > INTMAX/10, 那么temp = rev * 10 + pos一定会溢出
 *          3. 如果rev = INTMAX/10, 那么只要pop > 7, temp = rev * 10 + pop就会溢出
 */
public class IntegerInverse {
    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE/10 && pop > 7)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE/10 && pop < -8)) {
                return 0;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }

    public static void main(String[] args){

        System.out.println(123 % 10);
        int temp = -15647412;
        System.out.println(reverse(temp));
    }
}
