package com.algorithm.question.class2.class2_能力超过难度赚相应的钱.code;

//有一些工作：difficulty[i] 表示第 i 个工作的难度，profit[i] 表示第 i 个工作的收益。
//
// 现在我们有一些工人。worker[i] 是第 i 个工人的能力，即该工人只能完成难度小于等于 worker[i] 的工作。
//
// 每一个工人都最多只能安排一个工作，但是一个工作可以完成多次。
//
// 举个例子，如果 3 个工人都尝试完成一份报酬为 1 的同样工作，那么总收益为 $3。如果一个工人不能完成任何工作，他的收益为 $0 。
//
// 我们能得到的最大收益是多少？
//
//
//
// 示例：
//
// 输入: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
//输出: 100
//解释: 工人被分配的工作难度是 [4,4,6,6] ，分别获得 [20,20,30,30] 的收益。
//
//
//
// 提示:
//
//
// 1 <= difficulty.length = profit.length <= 10000
// 1 <= worker.length <= 10000
// difficulty[i], profit[i], worker[i] 的范围是 [1, 10^5]
//
// Related Topics 双指针
// 👍 61 👎 0

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
@Slf4j
public class Solution {

    public int[] maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        if (worker == null || worker.length < 1 || difficulty == null || difficulty.length < 1 || profit == null
            || profit.length < 1) {
            return new int[0];
        }
        int[] bestProfit = new int[worker.length];
        for (int i = 0; i < worker.length; i++) {
            int bestChoose = 0;
            for (int h = 0; h < difficulty.length; h++) {
                if (worker[i] >= difficulty[h]) {
                    bestChoose = Math.max(bestChoose, profit[h]);
                }
            }
            bestProfit[i] = bestChoose;
        }
        return bestProfit;
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
            Solution solution = new Solution();
            int[] bestProfit = solution.maxProfitAssignment(difficulty, profit, worker);
            log.info("bestProfit:{}", bestProfit);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

