package com.interview.algorithm;

/**
 * @Author qcl
 * @Description 力扣 415. 字符串相加
 * @Date 10:35 AM 6/19/2023
 */
public class AddString {
    public static String addStrings(String num1, String num2) {
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        StringBuilder result = new StringBuilder();

        while (i >= 0 || j >= 0) {
            int digit1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int digit2 = j >= 0 ? num2.charAt(j) - '0' : 0;

            int sum = digit1 + digit2 + carry;
            result.insert(0, sum % 10);
            carry = sum / 10;

            i--;
            j--;
        }

        if (carry != 0) {
            result.insert(0, carry);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(addStrings("11", "123")); // 134
        System.out.println(addStrings("456", "77")); // 533
        System.out.println(addStrings("0", "0")); // 0
    }

}
