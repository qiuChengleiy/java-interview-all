package com.interview.algorithm;

import java.util.HashSet;

/**
 * @Author qcl
 * @Description  找不重复子串的最长的字串 - 力扣第三题
 * @Date 9:55 AM 6/12/2023
 */
public class LongestSubstring {
    public static String findLongestSubstring(String str) {
        int n = str.length();
        int left = 0;
        int right = 0;
        int maxLength = 0;
        String longestSubstring = "";

        HashSet<Character> set = new HashSet<>();

        while (right < n) {
            char currentChar = str.charAt(right);
            if (!set.contains(currentChar)) {
                set.add(currentChar);
                int currentLength = right - left + 1;
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    longestSubstring = str.substring(left, right + 1);
                }
                right++;
            } else {
                set.remove(str.charAt(left));
                left++;
            }
        }

        return longestSubstring;
    }

    public static void main(String[] args) {
        String input = "pwwkew";
        String longestSubstring = findLongestSubstring(input);
        System.out.println("Longest Substring: " + longestSubstring);
    }
}

