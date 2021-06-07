package com.algorithm.question.class6.class6_最多的异或和为0子数组.code;

import com.algorithm.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/6/4 2:13 下午
 */
@Slf4j
public class Solution {

    public int xorIsZero(int[] arr, int start, int end) {
        if (start > end) {
            return 0;
        }
        if (start == end) {
            return arr[start] == 0 ? 1 : 0;
        }
        int ans = 0;
        int xor = 0;
        for (int i = start; i <= end; i++) {
            xor ^= arr[i];
        }
        if (xor == 0) {
            ans = 1;
        }

        for (int mid = start; mid < end; mid++) {
            ans = Math.max(ans, (xorIsZero(arr, start, mid) + xorIsZero(arr, mid + 1, end)));
        }

        return ans;
    }

    public static class TestClass {
        @Test
        public void test1() {
            Solution solution = new Solution();
            int[] arr = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int ans = solution.xorIsZero(arr, 0, arr.length - 1);
            log.info("ans:{}", ans);
        }

        @Test
        public void test2() {
            Solution solution = new Solution();
            for (int i = 0; i < 10000; i++) {
                int[] arr = ArrayUtil.randomIntArray(10, 1, 5);
                int ans = solution.xorIsZero(arr, 0, arr.length - 1);
                int ans2 = Code04_MostXorZero.mostXor(arr);
                if (ans != ans2) {
                    log.info("ans:{},ans2:{},arr:{}", ans, ans2, arr);
                }
            }
            log.info("Good,you success.");
        }
    }
}
