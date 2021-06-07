package com.algorithm.question.class4.class4_达标数组的生成.code;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/31 10:07 上午
 */
@Slf4j
public class Solution {

    public int[] makeNo(int size) {
        if (size == 1) {
            return new int[] {1};
        }
        int halfSize = (size + 1) / 2;
        int[] base = makeNo(halfSize);
        int[] ans = new int[size];
        int index = 0;
        for (; index < halfSize; index++) {
            ans[index] = base[index] * 2 + 1;
        }
        for (int i = 0; index < size; i++, index++) {
            ans[index] = base[i] * 2;
        }
        return ans;
    }

    public boolean isValid(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int k = i + 1; k < arr.length; k++) {
                for (int j = k + 1; j < arr.length; j++) {
                    if (arr[i] + arr[j] == arr[k] * 2) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static class TestClass {

        @Test
        public void test1() {
            Solution solution = new Solution();
            int[] arr = solution.makeNo(10);
            boolean isValid = solution.isValid(arr);
            log.info("arr:{},isValid:{}", arr, isValid);
        }

    }
}
