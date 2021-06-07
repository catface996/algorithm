package com.algorithm.question.class6.class6_最大异或和子数组.code;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/6/3 10:38 上午
 */
@Slf4j
public class SolutionForce {

    public int maxSubArrXor(int[] arr) {
        int ans = arr[0];
        int tempAns;
        for (int end = 0; end < arr.length; end++) {
            for (int start = 0; start <= end; start++) {
                tempAns = 0;
                for (int i = start; i <= end; i++) {
                    tempAns ^= arr[i];
                }
                ans = Math.max(ans, tempAns);
            }
        }
        return ans;
    }

    public static class TestClass {
        @Test
        public void test1() {
            int[] arr = {1, 2, 3, 4, 5, 6};
            SolutionForce solution = new SolutionForce();
            int ans = solution.maxSubArrXor(arr);
            log.info("ans:{}", ans);
        }
    }
}
