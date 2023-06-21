package com.interview.algorithm;

import java.util.Arrays;

/**
 * @Author qcl
 * @Description
 * @Date 10:57 AM 6/21/2023
 */
public class KSolution {
    public static int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);
        int l = 0, r = nums.length - 1;
        int ans = 0;
        while (l < r) {
            int res = nums[l] + nums[r];
            if (res > k) {
                r--;
            } else if (res < k) {
                l++;
            } else {
                ans++;
                l++;
                r--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,3,4};
        System.out.println( maxOperations(nums1, 5)); // 2

        int[] nums2 = new int[]{3,1,3,4,3};
        System.out.println( maxOperations(nums2, 6)); // 1
    }

}
