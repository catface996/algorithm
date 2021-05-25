package leetcode.贪心.P826安排工作以达到最大收益;

import java.util.Arrays;
import java.util.TreeMap;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/25 12:55 下午
 */
@Slf4j
public class Solution4 {

    // 2:07 下午	info
    //				解答成功:
    //				执行耗时:50 ms,击败了45.73% 的Java用户
    //				内存消耗:40.9 MB,击败了13.02% 的Java用户

    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        TreeMap<Integer, Integer> jobMap = new TreeMap<>();
        Job[] jobs = buildAndSortJobs(difficulty, profit);
        Job preJob = jobs[0];
        for (Job job : jobs) {
            if (job.difficulty >= preJob.difficulty && job.profit >= preJob.profit) {
                jobMap.put(job.difficulty, job.profit);
                preJob = job;
            }
        }
        int ans = 0;
        for (int j : worker) {
            Integer floorKey = jobMap.floorKey(j);
            if (floorKey != null) {
                ans += jobMap.get(floorKey);
            }
        }
        return ans;
    }

    private Job[] buildAndSortJobs(int[] difficulty, int[] profit) {
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
        return jobs;
    }

    public static class Job {
        private int difficulty;
        private int profit;

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
            Solution4 solution4 = new Solution4();
            int bestProfit = solution4.maxProfitAssignment(difficulty, profit, worker);
            log.info("bestProfit:{}", bestProfit);
        }

        // 示例：
        //         解答失败:
        //        测试用例:[68,35,52,47,86]
        //            [67,17,1,81,3]
        //            [92,10,85,84,82]
        //        期望结果:324
        @Test
        public void test2() {
            int[] difficulty = {68, 35, 52, 47, 86}, profit = {67, 17, 1, 81, 3}, worker = {92, 10, 85, 84, 82};
            Solution4 solution4 = new Solution4();
            int bestProfit = solution4.maxProfitAssignment(difficulty, profit, worker);
            log.info("bestProfit:{}", bestProfit);
        }
    }
}
