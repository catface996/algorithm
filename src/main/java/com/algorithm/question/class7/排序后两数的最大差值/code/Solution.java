package com.algorithm.question.class7.排序后两数的最大差值.code;

import java.util.Arrays;

import com.algorithm.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/6/7 1:28 下午
 */
@Slf4j
public class Solution {

    // 复杂度最小是O(N*logN),不符合要求

    public int maxRange(int[] arr) {
        int ans = 0;
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            ans = Math.max(ans, arr[i + 1] - arr[i]);
        }
        return ans;
    }

    public static class TestClass {

        @Test
        public void test1() {
            int[] arr = ArrayUtil.randomIntArray(10, 1, 100);
            Solution solution = new Solution();
            int maxRange = solution.maxRange(arr);
            log.info("maxRange:{}", maxRange);
        }
    }

}
