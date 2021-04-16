package course.暴力递归动态规划.整数分裂;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/16 2:24 下午
 */
public class SolutionUseDp {

    //给定一个正数n，求n的裂开方法数，
    //规定：后面的数不能比前面的数小
    //比如4的裂开方法有：
    //1+1+1+1、1+1+2、1+3、2+2、4
    //5种，所以返回5

    public int splitInteger(int number) {
        return process(number);
    }

    public int process(int number) {
        // 尝试的数字,剩余的数值
        int[][] dp = new int[number + 2][number + 2];
        // 剩余数字为0,找到一种分裂方案
        for (int currentInt = 1; currentInt <= number + 1; currentInt++) {
            dp[currentInt][0] = 1;
        }
        for (int leftNumber = 1; leftNumber <= number; leftNumber++) {
            for (int currentInt = number; currentInt >= 1; currentInt--) {
                if (currentInt > leftNumber) {
                    dp[currentInt][leftNumber] = 0;
                    continue;
                }
                int ways = 0;
                for (int chooseTimes = 0; chooseTimes * currentInt <= leftNumber; chooseTimes++) {
                    ways += dp[currentInt + 1][leftNumber - chooseTimes * currentInt];
                }
                dp[currentInt][leftNumber] = ways;
            }
        }
        return dp[1][number];
    }

    public static class TestClass {
        @Test
        public void test1() {
            int number = 4;
            SolutionUseDp solution = new SolutionUseDp();
            int ways = solution.splitInteger(number);
            System.out.println("分裂方案数:" + ways);
        }

        @Test
        public void test2() {
            for (int number = 1; number < 50; number++) {
                SolutionUseDp solution = new SolutionUseDp();
                int ways = solution.splitInteger(number);
                int ways2 = Code03_SplitNumber.dp1(number);
                if (ways != ways2) {
                    System.out.println("裂变不一致:" + number);
                }
                assert ways == ways2;
            }
            System.out.println("Good,you success!");
        }
    }

}
