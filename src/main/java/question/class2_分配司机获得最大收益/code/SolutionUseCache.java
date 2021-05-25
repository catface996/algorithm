package question.class2_分配司机获得最大收益.code;

import java.util.Arrays;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/25 2:17 下午
 */
public class SolutionUseCache {

    public int maxProfit(int[][] income) {
        // 需要去A区的人(和需要去B区的人一致)
        int aDriverNum = income.length >> 1;
        int[][] cache = new int[income.length][aDriverNum + 1];
        for (int[] row : cache) {
            Arrays.fill(row, -1);
        }
        return process(0, aDriverNum, income, cache);
    }

    /**
     * 对第i号司机做决策
     *
     * @param i        第i号司机 i从0开始
     * @param aRestNum 还需要去a区的司机数量
     * @param income   收益表
     * @return 第i个司机决策出的最大收益
     */
    public int process(int i, int aRestNum, int[][] income, int[][] cache) {
        if (i == income.length) {
            return 0;
        }
        if (cache[i][aRestNum] != -1) {
            return cache[i][aRestNum];
        }
        int ans;
        if (aRestNum == income.length - i) {
            // 如果剩余司机数量和a区域剩余派遣数量一致,剩余的所有司机只能去a区域
            ans = income[i][0] + process(i + 1, aRestNum - 1, income, cache);
        } else if (aRestNum == 0) {
            // A区的司机已经满员,只能去B区
            ans = income[i][1] + process(i + 1, aRestNum, income, cache);
        } else {
            // 否则,可分以下两种情况
            // 1.i号司机去A区
            // 2.i号司机去B去
            int profit1 = income[i][1] + process(i + 1, aRestNum, income, cache);
            int profit2 = income[i][0] + process(i + 1, aRestNum - 1, income, cache);
            ans = Math.max(profit1, profit2);
        }
        cache[i][aRestNum] = ans;
        return ans;
    }

    public static class TestClass {
        @Test
        public void testForce() {
            int N = 10;
            int value = 100;
            int testTime = 500;
            System.out.println("测试开始");
            SolutionUseCache solution = new SolutionUseCache();
            for (int i = 0; i < testTime; i++) {
                int len = (int)(Math.random() * N) + 1;
                int[][] matrix = Code04_Drive.randomMatrix(len, value);
                int ans1 = Code04_Drive.maxMoney1(matrix);
                int ans2 = solution.maxProfit(matrix);
                if (ans1 != ans2) {
                    System.out.println(ans1);
                    System.out.println(ans2);
                    System.out.println("Oops!");
                }
            }
            System.out.println("测试结束");
        }
    }
}
