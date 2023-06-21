package com.interview.leetcode;

/**
 * @Author qcl
 * @Description 1071: 字符串的最大公约数  ///  字符串的最大公因子
 * @Date 2:36 PM 6/21/2023
 */
public class Q1071 {
    public static void main(String[] args) {
        System.out.println(gcdOfStrings("ABC", "ABC")); // ABC
        System.out.println(gcdOfStrings("ABABAB", "ABAB")); // AB
    }

    /**
     * gcd解法
     * @param str1
     * @param str2
     * @return
     */
    public static String gcdOfStrings(String str1, String str2) {
        if (!str1.concat(str2).equals(str2.concat(str1))) {
            return "";
        }
        return str1.substring(0, gcd(str1.length(), str2.length()));
    }

    /**
     * gcd 公式
     * @param m
     * @param n
     * @return
     */
    public static int gcd1(int m, int n) {
        return n == 0 ? m : gcd(n, m % n);
    }

    /**
     * 耗时最低
     * @param a
     * @param b
     * @return
     */
    public static int gcd(int a, int b) {
        int remainder = a % b;
        while (remainder != 0) {
            a = b;
            b = remainder;
            remainder = a % b;
        }
        return b;
    }
}
