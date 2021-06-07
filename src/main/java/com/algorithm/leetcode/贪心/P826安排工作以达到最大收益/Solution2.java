package com.algorithm.leetcode.贪心.P826安排工作以达到最大收益;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/24 4:29 下午
 */
@Slf4j
public class Solution2 {

    // 5:01 下午	info
    //				解答成功:
    //				执行耗时:17 ms,击败了77.53% 的Java用户
    //				内存消耗:39.6 MB,击败了52.44% 的Java用户

    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        if (worker == null || worker.length < 1 || difficulty == null || difficulty.length < 1 || profit == null
            || profit.length < 1) {
            return 0;
        }
        Job[] jobs = new Job[difficulty.length];
        for (int i = 0; i < difficulty.length; i++) {
            jobs[i] = new Job(difficulty[i], profit[i]);
        }
        Arrays.sort(jobs, (o1, o2) -> {
            if (o1.difficulty == o2.difficulty) {
                return o1.profit - o2.profit;
            }
            return o1.difficulty - o2.difficulty;
        });
        Arrays.sort(worker);
        int bestProfit = 0;
        int jobIndex = 0;
        int maxProfit = 0;
        for (int ability : worker) {
            while (jobIndex < jobs.length && ability >= jobs[jobIndex].difficulty) {
                maxProfit = Math.max(maxProfit, jobs[jobIndex].profit);
                jobIndex++;
            }
            bestProfit += maxProfit;
        }
        return bestProfit;
    }

    public static class Job {
        private final int difficulty;
        private final int profit;

        public Job(int difficulty, int profit) {
            this.difficulty = difficulty;
            this.profit = profit;
        }
    }

    public static class TestClass {
        // 示例：
        //
        // 输入: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
        //输出: 100
        //解释: 工人被分配的工作难度是 [4,4,6,6] ，分别获得 [20,20,30,30] 的收益。
        @Test
        public void test1() {
            int[] difficulty = {2, 4, 6, 8, 10}, profit = {10, 20, 30, 40, 50}, worker = {4, 5, 6, 7};
            Solution2 solution2 = new Solution2();
            int bestProfit = solution2.maxProfitAssignment(difficulty, profit, worker);
            log.info("bestProfit:{}", bestProfit);
        }
    }
}
