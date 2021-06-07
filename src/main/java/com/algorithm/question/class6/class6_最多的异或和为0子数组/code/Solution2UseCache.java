package com.algorithm.question.class6.class6_最多的异或和为0子数组.code;

import java.util.Arrays;

import com.algorithm.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/6/4 2:13 下午
 */
@Slf4j
public class Solution2UseCache {

    public int xorIsZero(int[] arr) {
        int[] xor = new int[arr.length + 1];
        xor[0] = 0;
        for (int i = 1; i <= arr.length; i++) {
            xor[i] = xor[i - 1] ^ arr[i - 1];
        }
        int[][] cache = new int[arr.length][arr.length];
        for (int[] ints : cache) {
            Arrays.fill(ints, -1);
        }
        return process(xor, arr, 0, arr.length - 1, cache);
    }

    public int process(int[] xor, int[] arr, int start, int end, int[][] cache) {
        if (start > end) {
            return 0;
        }
        if (start == end) {
            return arr[start] == 0 ? 1 : 0;
        }
        if (cache[start][end] != -1) {
            return cache[start][end];
        }
        int ans = 0;
        if ((xor[end + 1] ^ xor[start]) == 0) {
            ans = 1;
        }
        for (int mid = start; mid < end; mid++) {
            ans = Math.max(ans, (process(xor, arr, start, mid, cache) + process(xor, arr, mid + 1, end, cache)));
        }
        cache[start][end] = ans;
        return ans;
    }

    public static class TestClass {
        @Test
        public void test1() {
            Solution2UseCache solution = new Solution2UseCache();
            int[] arr = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            int ans = solution.xorIsZero(arr);
            log.info("ans:{}", ans);
        }

        @Test
        public void test2() {
            Solution2UseCache solution = new Solution2UseCache();
            for (int i = 0; i < 1000; i++) {
                int[] arr = ArrayUtil.randomIntArray(100, 1, 5);
                int ans = solution.xorIsZero(arr);
                int ans2 = Code04_MostXorZero.mostXor(arr);
                if (ans != ans2) {
                    log.info("ans:{},ans2:{},arr:{}", ans, ans2, arr);
                }
            }
            log.info("Good,you success.");
        }
    }
}
