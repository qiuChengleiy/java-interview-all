package com.interview.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author qcl
 * @Description 1431: 拥有最多糖果的孩子
 *给你一个数组 candies 和一个整数 extraCandies ，其中 candies[i] 代表第 i 个孩子拥有的糖果数目。
 *
 * 对每一个孩子，检查是否存在一种方案，将额外的 extraCandies 个糖果分配给孩子们之后，此孩子有 最多 的糖果。注意，允许有多个孩子同时拥有 最多 的糖果数目。
 *
 *  输入：candies = [2,3,5,1,3], extraCandies = 3
 * 输出：[true,true,true,false,true]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/kids-with-the-greatest-number-of-candies
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * @Date 2:57 PM 6/21/2023
 */
public class Q1431 {

    public static void main(String[] args) {
        int[] candies = new int[]{2,3,5,1,3};
        System.out.println(kidsWithCandies(candies, 3)); // [true, true, true, false, true]

        int[] candies1 = new int[]{4,2,1,1,2};
        System.out.println(kidsWithCandies(candies1, 1)); // [true, false, false, false, false]
    }

    /**
     * 可以使用证明方法解题
     * @param candies
     * @param extraCandies
     * @return
     */
    public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int maxCandies = 0;
        int len = candies.length;
        for(int i=0;i < len; ++i) {
            maxCandies = Math.max(maxCandies, candies[i]);
        }

        List<Boolean> ret = new ArrayList<>();
        for(int i=0;i < len; ++i) {
            ret.add(candies[i] + extraCandies >= maxCandies);
        }

        return ret;
    }
}
