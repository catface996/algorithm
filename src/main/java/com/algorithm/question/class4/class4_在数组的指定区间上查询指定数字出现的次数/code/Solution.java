package com.algorithm.question.class4.class4_在数组的指定区间上查询指定数字出现的次数.code;

/**
 * @author by catface
 * @date 2021/5/28 10:34 上午
 */
public class Solution {

    int[] arr;

    public void init(int[] arr) {
        this.arr = arr;
    }

    public int query(int start, int end, int targetNum) {
        int ans = 0;
        for (int i = start; i <= end; i++) {
            if (arr[i] == targetNum) {
                ans++;
            }
        }
        return ans;
    }
}
