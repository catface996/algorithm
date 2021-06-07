package com.algorithm.question.class4.class4_在数组的指定区间上查询指定数字出现的次数.code;

import java.util.HashMap;
import java.util.Map;

import com.algorithm.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/28 10:03 上午
 */
@Slf4j
public class Solution1 {

    /**
     * 使用前缀和,用空间换时间
     */
    private Map<Integer, int[]> targetNumPositionsMap;

    public void init(int[] arr) {
        targetNumPositionsMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int[] positions = targetNumPositionsMap.computeIfAbsent(arr[i], k -> new int[arr.length]);
            positions[i] = 1;
        }
        for (Integer key : targetNumPositionsMap.keySet()) {
            int[] positions = targetNumPositionsMap.get(key);
            for (int i = 0; i < positions.length - 1; i++) {
                positions[i + 1] += positions[i];
            }
        }
    }

    public int query(int start, int end, int targetNum) {
        int[] positions = targetNumPositionsMap.get(targetNum);
        if (positions == null) {
            return 0;
        }
        if (start == 0) {
            return positions[end];
        } else {
            return positions[end] - positions[start - 1];
        }
    }

    public static class TestClass {
        @Test
        public void test1() {
            Solution1 solution1 = new Solution1();
            int size = 20;
            int[] arr = {3, 6, 9, 9, 9, 4, 5, 1, 9, 3, 6, 2, 5, 5, 1, 1, 8, 5, 5, 5};
            solution1.init(arr);
            log.info("arr:{}", arr);
            int start = 0;
            int end = size - 1;
            int ans = solution1.query(start, end, 1);
            log.info("ans:{}", ans);
        }

        @Test
        public void testForce() {
            Solution solution = new Solution();
            Solution1 solution1 = new Solution1();
            int size = 20;
            for (int i = 0; i < 1000; i++) {
                int[] arr = ArrayUtil.randomIntArray(20, 1, 10);
                solution1.init(arr);
                solution.init(arr);
                int s = (int)(Math.random() * size);
                int e = (int)(Math.random() * size);
                int start = Math.min(s, e);
                int end = Math.max(s, e);
                for (int targetNum = 0; targetNum < 10; targetNum++) {
                    int ans = solution1.query(start, end, targetNum);
                    int ans2 = solution.query(start, end, targetNum);
                    if (ans != ans2) {
                        log.info("arr:{},start:{},end:{},targetNum:{},ans1:{},ans2:{}", arr, start, end, targetNum, ans,
                            ans2);
                    }
                    assert ans == ans2;
                }
            }
            log.info("Good,you success.");
        }
    }
}
