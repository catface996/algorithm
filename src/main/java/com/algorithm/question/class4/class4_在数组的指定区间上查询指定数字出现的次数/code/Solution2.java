package com.algorithm.question.class4.class4_在数组的指定区间上查询指定数字出现的次数.code;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import com.algorithm.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/28 10:30 上午
 */
@Slf4j
public class Solution2 {

    private Map<Integer, TreeSet<Integer>> targetNumPositionsMap;

    public void init(int[] arr) {
        targetNumPositionsMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            TreeSet<Integer> positions = targetNumPositionsMap.computeIfAbsent(arr[i], k -> new TreeSet<>());
            positions.add(i);
        }
    }

    public int query(int start, int end, int targetNum) {
        TreeSet<Integer> treeSet = targetNumPositionsMap.get(targetNum);
        if (treeSet == null) {
            return 0;
        }
        // subSet 方法的下标范围为[start,end),所以要用end+1
        return treeSet.subSet(start, end + 1).size();
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
        public void test2() {
            Solution2 solution2 = new Solution2();
            int[] arr = {5, 8, 6, 3, 1, 3, 1, 2, 4, 9, 9, 9, 4, 5, 5, 6, 1, 5, 4, 8};
            solution2.init(arr);
            log.info("arr:{}", arr);
            int start = 0;
            int end = 8;
            int ans = solution2.query(start, end, 4);
            log.info("ans:{}", ans);
        }

        @Test
        public void testForce() {
            Solution2 solution2 = new Solution2();
            Solution1 solution1 = new Solution1();
            int size = 20;
            for (int i = 0; i < 1000; i++) {
                int[] arr = ArrayUtil.randomIntArray(20, 1, 10);
                solution1.init(arr);
                solution2.init(arr);
                int s = (int)(Math.random() * size);
                int e = (int)(Math.random() * size);
                int start = Math.min(s, e);
                int end = Math.max(s, e);
                for (int targetNum = 0; targetNum < 10; targetNum++) {
                    int ans = solution1.query(start, end, targetNum);
                    int ans2 = solution2.query(start, end, targetNum);
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
