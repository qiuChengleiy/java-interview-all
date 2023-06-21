package com.interview.leetcode;

/**
 * @Author qcl
 * @Description 1768: 交替合并字符串 : https://leetcode.cn/problems/merge-strings-alternately/
 * @Date 2:10 PM 6/21/2023
 */
public class Q1768 {
    public static void main(String[] args) {
        System.out.println(mergeAlternately("abc", "pqrss")); // apbqcrss
    }

    /**
     * 双指针解法
     * 空间复杂度: O(1)
     * 时间复杂度: O(m+n)
     * @param word1
     * @param word2
     * @return
     */
    public static String mergeAlternately(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int i=0, j =0;

        StringBuilder ans = new StringBuilder();
        while (i < m || j < n) {
            if(i < m) {
                ans.append(word1.charAt(i));
                i ++;
            }

            if(j < n) {
                ans.append(word2.charAt(j));
                j++;
            }
        }

        return ans.toString();
    }
}
