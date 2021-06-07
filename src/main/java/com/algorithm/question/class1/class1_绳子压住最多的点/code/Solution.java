package com.algorithm.question.class1.class1_绳子压住最多的点.code;

import java.util.LinkedList;

import com.algorithm.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/20 1:38 下午
 */
@Slf4j
public class Solution {

    /**
     * 绳子上压住的最多的点个数
     * <p>
     * 使用滑动窗口
     *
     * @param arr 在x轴上有序分布的点
     * @param k   绳子的长度
     * @return 绳子压住的点的个数, 端点重合算压住
     */
    public int maxPointNum(int[] arr, int k) {
        int ans = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        int index = 0;
        while (index < arr.length) {
            if (queue.isEmpty()) {
                queue.add(arr[index]);
                ans = Math.max(ans, queue.size());
                index++;
                continue;
            }
            if (arr[index] - queue.peekFirst() <= k) {
                // 待加入的数字仍旧能被长度为k的绳子盖住,加入到队列中
                queue.add(arr[index]);
                ans = Math.max(ans, queue.size());
                index++;
            } else {
                queue.pollFirst();
            }
        }
        return ans;
    }

    public static class TestClass {
        @Test
        public void test() {
            for (int i = 0; i < 10000; i++) {
                int[] arr = ArrayUtil.randomSortedNotRepeat(10, 1, 5);
                int k = 7;
                SolutionForce solutionForce = new SolutionForce();
                Solution solution = new Solution();
                int ans1 = solutionForce.maxPointNum(arr, k);
                int ans2 = solution.maxPointNum(arr, k);
                if (ans1 != ans2) {
                    log.info("forceAns:{},ans:{},arr:{},k:{}", ans1, ans2, arr, k);
                    assert false;
                }
            }
            log.info("Good,you success!");
        }

        @Test
        public void test2() {
            int[] arr = {1, 4, 6, 8, 9, 14, 15, 18, 19, 21};
            int k = 7;
            SolutionForce solutionForce = new SolutionForce();
            Solution solution = new Solution();
            int forceAns = solutionForce.maxPointNum(arr, k);
            int ans = solution.maxPointNum(arr, k);
            System.out.println(ans);
        }
    }

}
