package com.algorithm.question.class4.class4_不取相邻数的最大累加和子序列;

import com.algorithm.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/28 7:39 下午
 */
@Slf4j
public class Solution {

    public int maxSubSeqWithoutNeighbor(int[] arr) {
        int p1 = process(arr, 0, true);
        int p2 = process(arr, 0, false);
        return Math.max(p1, p2);
    }

    public int process(int[] arr, int index, boolean chooseLast) {
        if (index == arr.length) {
            return 0;
        }
        // 如果当前数字的上一个数字被选择了,当前数字不可以被选择
        if (chooseLast) {
            return process(arr, index + 1, false);
        } else {
            int p1 = process(arr, index + 1, false);
            int p2 = process(arr, index + 1, true) + arr[index];
            return Math.max(p1, p2);
        }
    }

    public static class TestClass {
        @Test
        public void test1() {
            Solution solution = new Solution();
            for (int i = 0; i < 1000; i++) {
                int[] arr = ArrayUtil.randomIntArray(20, 1, 100);
                int ans = solution.maxSubSeqWithoutNeighbor(arr);
                int ans2 = Code04_SubArrayMaxSumFollowUp.maxSum(arr);
                if (ans != ans2) {
                    log.info("ans:{},ans2:{},arr:{}", ans, ans2, arr);
                }
            }
            log.info("Good,you success.");
        }
    }

}
