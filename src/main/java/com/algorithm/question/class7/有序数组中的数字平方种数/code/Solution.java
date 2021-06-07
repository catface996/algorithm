package com.algorithm.question.class7.有序数组中的数字平方种数.code;

import com.algorithm.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/6/7 4:52 下午
 */
@Slf4j
public class Solution {

    public int squireKinds(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        int pre = 0;
        int ans = 0;
        int absL;
        int absR;
        while (l <= r) {
            absL = Math.abs(nums[l]);
            absR = Math.abs(nums[r]);
            if (absL > absR) {
                if (pre != absL) {
                    pre = absL;
                    ans++;
                }
                l++;
            } else if (absL == absR) {
                if (pre != absL) {
                    ans++;
                    pre = absL;
                }
                l++;
                r--;
            } else {
                if (pre != absR) {
                    ans++;
                    pre = absR;
                }
                r--;
            }
        }
        return ans;
    }

    public static class TestClass {
        @Test
        public void test1() {
            int[] arr = ArrayUtil.randomIntArray(10, 1, 10, true);
            Solution solution = new Solution();
            int ans1 = solution.squireKinds(arr);
            int ans2 = Code04_Power2Diffs.diff2(arr);
            if (ans1 != ans2) {
                log.info("ans1:{},ans2:{},arr:{}", ans1, ans2, arr);
            }
        }

        @Test
        public void testForce() {
            int size = (int)(Math.random() * 1000);
            int minValue = 1;
            int maxValue = 10000;
            Solution solution = new Solution();
            for (int i = 0; i < 10000; i++) {
                int[] arr = ArrayUtil.randomIntArray(size, minValue, maxValue, true);
                int ans1 = solution.squireKinds(arr);
                int ans2 = Code04_Power2Diffs.diff2(arr);
                if (ans1 != ans2) {
                    log.info("ans1:{},ans2:{},arr:{}", ans1, ans2, arr);
                }
            }
            log.info("Good,you success!");
        }
    }
}
