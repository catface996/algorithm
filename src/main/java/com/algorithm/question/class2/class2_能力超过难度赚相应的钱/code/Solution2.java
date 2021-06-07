package com.algorithm.question.class2.class2_能力超过难度赚相应的钱.code;

import java.util.Arrays;
import java.util.Comparator;

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

    public int[] maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        if (worker == null || worker.length < 1 || difficulty == null || difficulty.length < 1 || profit == null
            || profit.length < 1) {
            return new int[0];
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
        Worker[] workers = new Worker[worker.length];
        for (int i = 0; i < worker.length; i++) {
            workers[i] = new Worker(worker[i], i);
        }
        Arrays.sort(workers, Comparator.comparingInt(o -> o.ability));
        int[] bestProfit = new int[workers.length];
        int jobIndex = 0;
        int maxProfit = 0;
        for (Worker w : workers) {
            while (jobIndex < jobs.length && w.ability >= jobs[jobIndex].difficulty) {
                maxProfit = Math.max(maxProfit, jobs[jobIndex].profit);
                jobIndex++;
            }
            bestProfit[w.index] = maxProfit;
        }
        return bestProfit;
    }

    public static class Job {
        private int difficulty;
        private int profit;

        public Job(int difficulty, int profit) {
            this.difficulty = difficulty;
            this.profit = profit;
        }
    }

    public static class Worker {
        int ability;
        int index;

        public Worker(int ability, int index) {
            this.ability = ability;
            this.index = index;
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
            int[] bestProfit = solution2.maxProfitAssignment(difficulty, profit, worker);
            log.info("bestProfit:{}", bestProfit);
        }

        // 示例：
        //         解答失败:
        //        测试用例:[68,35,52,47,86]
        //            [67,17,1,81,3]
        //            [92,10,85,84,82]
        //        测试结果:204
        //        期望结果:324
        @Test
        public void test2() {
            int[] difficulty = {68, 35, 52, 47, 86}, profit = {67, 17, 1, 81, 3}, worker = {92, 10, 85, 84, 82};
            Solution2 solution2 = new Solution2();
            int[] bestProfit = solution2.maxProfitAssignment(difficulty, profit, worker);
            log.info("bestProfit:{}", bestProfit);
        }
    }
}
