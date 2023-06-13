package com.interview.algorithm;

/**
 * @Author qcl
 * @Description KMP算法实现的代码，用于找出字符串中第一个匹配项的下标。如果找不到匹配项，返回-1。
 * @Date 10:32 AM 6/13/2023
 */
public class KMPSearch {
    public static void main(String[] args) {
        String text = "Hello, World!";
        String pattern = "Hells";

        int index = kmpSearch(text, pattern);
        System.out.println("First match index: " + index);
    }

    public static int kmpSearch(String text, String pattern) {
        if (text == null || pattern == null || text.isEmpty() || pattern.isEmpty()) {
            return -1;
        }

        int[] lps = computeLPSArray(pattern);

        int i = 0; // text中的索引
        int j = 0; // pattern中的索引

        while (i < text.length()) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;

                if (j == pattern.length()) {
                    return i - j; // 匹配成功，返回第一个匹配项的下标
                }
            } else {
                if (j != 0) {
                    j = lps[j - 1]; // 回溯到最长公共前后缀的下一个位置
                } else {
                    i++; // 没有找到匹配项，继续在text中前进
                }
            }
        }

        return -1; // 没有找到匹配项
    }

    private static int[] computeLPSArray(String pattern) {
        int[] lps = new int[pattern.length()];
        int len = 0; // 最长公共前后缀的长度
        int i = 1;

        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1]; // 回溯到前一个位置继续匹配
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }
}

