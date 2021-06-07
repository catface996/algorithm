package com.algorithm.question.class6.class6_nim博弈.code;

import com.algorithm.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/6/4 11:50 上午
 */
@Slf4j
public class Solution {

    public boolean nimGame(int[] arr) {
        int num = arr[0];
        for (int i = 1; i < arr.length; i++) {
            num ^= arr[i];
        }
        return num != 0;
    }

    public static class TestClass {
        @Test
        public void test() {
            Solution solution = new Solution();
            for (int i = 0; i < 1000; i++) {
                int[] arr = ArrayUtil.randomIntArray(10, 1, 30);
                boolean win = solution.nimGame(arr);
                log.info("先手赢?  {},arr:{}", win, arr);
            }

        }
    }
}
