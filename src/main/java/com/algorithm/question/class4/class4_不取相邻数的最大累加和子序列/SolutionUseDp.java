package com.algorithm.question.class4.class4_不取相邻数的最大累加和子序列;

import com.algorithm.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/28 7:39 下午
 */
@Slf4j
public class SolutionUseDp {

    public int maxSubSeqWithoutNeighbor(int[] arr) {
        return process(arr);
    }

    public int process(int[] arr) {
        int[][] dp = new int[2][arr.length + 1];
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int chooseLast = 1; chooseLast >= 0; chooseLast--) {
                // 如果当前数字的上一个数字被选择了,当前数字不可以被选择
                if (chooseLast == 1) {
                    dp[1][index] = dp[0][index + 1];
                } else {
                    int p1 = dp[0][index + 1];
                    int p2 = dp[1][index + 1] + arr[index];
                    dp[0][index] = Math.max(p1, p2);
                }
            }
        }
        return Math.max(dp[0][0], dp[1][0]);
    }

    public static class TestClass {
        @Test
        public void test1() {
            SolutionUseDp solution = new SolutionUseDp();
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
