package com.algorithm.question.class7.排序后两数的最大差值.code;

import com.algorithm.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/6/7 1:35 下午
 */
@Slf4j
public class Solution2 {

    public int maxRange(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int len = arr.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : arr) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        if (min == max) {
            return 0;
        }
        // hasNum[i]=true 表示第i号通进入过数字,否则未进入过数字
        // 总共len个数字,有len+1个桶,根据鸽笼原理,必定存在空桶,相邻两数的最大差值,必定出现在前一个非空桶中的最大值和后一个非空桶中的最小值
        boolean[] hasNum = new boolean[len + 1];
        int[] maxs = new int[len + 1];
        int[] mins = new int[len + 1];
        int bid;
        for (int num : arr) {
            bid = bucket(num, len, min, max);
            mins[bid] = hasNum[bid] ? Math.min(mins[bid], num) : num;
            maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], num) : num;
            hasNum[bid] = true;
        }
        int res = 0;
        int lasMax = maxs[0];
        int i = 1;
        for (; i <= len; i++) {
            if (hasNum[i]) {
                res = Math.max(res, mins[i] - lasMax);
                lasMax = maxs[i];
            }
        }
        return res;
    }

    public int bucket(int num, int len, int min, int max) {
        return (num - min) * len / (max - min);
    }

    public static class TestClass {
        @Test
        public void test1() {
            int[] arr = ArrayUtil.randomIntArray(10, 1, 100);
            Solution solution = new Solution();
            Solution2 solution2 = new Solution2();
            int ans1 = solution.maxRange(arr);
            int ans2 = solution2.maxRange(arr);
            if (ans1 != ans2) {
                log.info("ans1:{},ans2:{},arr:{}", ans1, ans2, arr);
            }
        }

        @Test
        public void testForce() {
            Solution solution = new Solution();
            Solution2 solution2 = new Solution2();
            for (int i = 0; i < 10000; i++) {
                int[] arr = ArrayUtil.randomIntArray(10, 1, 100);
                int ans1 = solution.maxRange(arr);
                int ans2 = solution2.maxRange(arr);
                if (ans1 != ans2) {
                    log.info("ans1:{},ans2:{},arr:{}", ans1, ans2, arr);
                }
            }
            log.info("Good,you success.");
        }
    }

}
