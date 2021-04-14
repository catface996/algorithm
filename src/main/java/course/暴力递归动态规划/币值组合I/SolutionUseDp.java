package course.暴力递归动态规划.币值组合I;

import java.util.Random;

import org.junit.Test;
import util.ArrayUtil;

/**
 * @author by catface
 * @date 2021/4/14 3:13 下午
 */
public class SolutionUseDp {

    //arr是货币数组，其中的值都是正数。再给定一个正数aim。
    //每个值都认为是一张货币，
    //即便是值相同的货币也认为每一张都是不同的，
    //返回组成aim的方法数
    //例如：arr = {1,1,1}，aim = 2
    //第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2
    //一共就3种方法，所以返回3

    private int[] moneyArr;

    /**
     * 计算可以组成目标金额的货币序列种数
     *
     * @param moneyArr  货币列表,每一张货币都不一样,即便面值相同
     * @param aimAmount 目标金额
     * @return 组成目标金额的货币序列种数
     */
    public int calculateWays(int[] moneyArr, int aimAmount) {
        this.moneyArr = moneyArr;
        return process(aimAmount);
    }

    /**
     * 递归组成目标金额
     *
     * @return 组成剩余金额的货币组合数量
     */
    public int process(int aimAmount) {

        // 币值列表维度增+1,用来记录 currentIndex>=moneyArr.length
        int[][] dp = new int[moneyArr.length + 1][aimAmount + 1];
        // 剩余金额为0,发现一种方案
        for (int currentIndex = 0; currentIndex <= moneyArr.length; currentIndex++) {
            dp[currentIndex][0] = 1;
        }
        for (int currentIndex = moneyArr.length - 1; currentIndex >= 0; currentIndex--) {
            for (int leftAmount = 1; leftAmount <= aimAmount; leftAmount++) {
                // 不选择当前货币,剩余目标金额不变
                int ways = dp[currentIndex + 1][leftAmount];
                // 选择当前货币,(选择当前货币的前提是,当前货币面试小于等于剩余目标金额,可以通过下一次进入process判断leftAmount来决策)
                int nextLeftAmount = leftAmount - moneyArr[currentIndex];
                if (nextLeftAmount >= 0) {
                    ways += dp[currentIndex + 1][nextLeftAmount];
                }
                dp[currentIndex][leftAmount] = ways;
            }
        }
        return dp[0][aimAmount];
    }

    public static class TestClass {
        @Test
        public void test1() {
            for (int i = 0; i < 100; i++) {
                int[] moneyArr = ArrayUtil.randomIntArray(100, 1, 10);
                int aimAmount = new Random().nextInt(50);
                SolutionUseCache solutionUseCache = new SolutionUseCache();
                SolutionUseDp solutionUseDp = new SolutionUseDp();
                int waysUseCache = solutionUseCache.calculateWays(moneyArr, aimAmount);
                int waysUseDp = solutionUseDp.calculateWays(moneyArr, aimAmount);
                assert waysUseDp == waysUseCache;
            }
        }
    }
}
