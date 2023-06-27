package com.interview.leetcode;

/**
 * @Author qcl
 * @Description 跳跃游戏(力扣55题)
 *
 * 给定一个非负整数数组 `nums` ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。判断你是否能够到达最后一个下标。
 *
 *
```
输入：nums = [2,3,1,1,4]
输出：true
解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
```

```
输入：nums = [3,2,1,0,4]
输出：false
解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。

```
 *
 *
 * @Date 9:52 AM 6/27/2023
 */
public class Q55 {

    public static boolean canJump(int[] nums) {
        int maxReach = 0; // 当前能够到达的最远位置
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (i > maxReach) {
                // 如果当前位置已经超过了能够到达的最远位置，说明无法到达最后一个位置
                return false;
            }

            maxReach = Math.max(maxReach, i + nums[i]);

            if (maxReach >= n - 1) {
                // 如果当前能够到达的最远位置已经超过或等于最后一个位置，说明可以到达最后一个位置
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{2,3,1,1,4};
        System.out.println(canJump(nums1));

        int[] nums2 = new int[]{3,2,1,0,4};
        System.out.println(canJump(nums2));
    }

}
