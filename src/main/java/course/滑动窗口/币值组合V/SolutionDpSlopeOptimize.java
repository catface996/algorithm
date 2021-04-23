package course.滑动窗口.币值组合V;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/23 8:42 下午
 */
public class SolutionDpSlopeOptimize {

    // 给定面值组合[1,5,10,20]
    // 给定张数组合[7,15,20,5]
    // 求组成目标金额的方案数

    public static int compensate(int pre, int cur, int coin) {
        return (cur - pre) / coin;
    }

    public int calculateMinNums(int[] money, int[] nums, int aim) {
        return processDp(money, nums, aim);
    }

    /**
     * 使用指定位置的货币的前提下,能够组成目标金额的方案数
     * <p>
     * 通过滑动窗口做斜率优化
     *
     * @param money 面值数组
     * @param nums  面值对应的张数
     * @return 组合数
     */
    private int processDp(int[] money, int[] nums, int aim) {
        int[][] dp = new int[money.length + 1][aim + 1];
        Arrays.fill(dp[money.length], Integer.MAX_VALUE);
        dp[money.length][0] = 0;
        for (int current = money.length - 1; current >= 0; current--) {
            // 分组,如果目标金额小于面值,以面值为最小边界(mode可以达到aim),否则以面值为最小边界
            int moneyValue = money[current];
            int nextCurrent = current + 1;
            for (int mode = 0; mode < Math.min(aim + 1, moneyValue); mode++) {
                LinkedList<Integer> minQ = new LinkedList<>();
                minQ.addLast(mode);
                dp[current][mode] = dp[nextCurrent][mode];
                // 分组计算,0,5,10.15...;1,6,11,16...;2,7,12,...;...;4,9,14,...;
                for (int right = mode + moneyValue; right <= aim; right += moneyValue) {
                    // 使用滑动窗口,
                    // 窗口中的最小值是MAX_VALUE,说明是无效值,可以进入窗口
                    // 当前位置的下一行的值<= (当前金额-要比对的金额/面值)+队列中的最小值,则队列中的last抛弃
                    while (!minQ.isEmpty() && (dp[nextCurrent][minQ.peekLast()] == Integer.MAX_VALUE
                        || dp[nextCurrent][minQ.peekLast()] + compensate(minQ.peekLast(), right, moneyValue)
                        >= dp[nextCurrent][right])) {
                        minQ.pollLast();
                    }
                    minQ.addLast(right);
                    int overdue = right - moneyValue * (nums[current] + 1);
                    // 检查滑动窗口的左边界
                    if (minQ.peekFirst() == overdue) {
                        minQ.pollFirst();
                    }
                    dp[current][right] = dp[nextCurrent][minQ.peekFirst()] + compensate(minQ.peekFirst(), right,
                        moneyValue);
                }
            }
        }
        return dp[0][aim];
    }

    public static class TestClass {
        // 给定面值组合[1,5,10,20]
        // 给定张数组合[7,15,20,5]
        // 目标金额: 20
        // 1张
        @Test
        public void test() {
            int[] money = {1, 5, 10, 20};
            int[] nums = {7, 15, 20, 5};
            int aim = 20;
            SolutionDpSlopeOptimize solution = new SolutionDpSlopeOptimize();
            int ans = solution.calculateMinNums(money, nums, aim);
            System.out.println(ans);
        }

        // 给定面值组合[1,5,10,20]
        // 给定张数组合[7,15,20,5]
        // 目标金额: 20
        // 10张 3 * 1 + 2 * 10 + 5 * 20
        @Test
        public void test1() {
            int[] money = {1, 5, 10, 20};
            int[] nums = {7, 15, 20, 5};
            int aim = 123;
            SolutionDpSlopeOptimize solution = new SolutionDpSlopeOptimize();
            int ans = solution.calculateMinNums(money, nums, aim);
            System.out.println(ans);
        }

        // 给定面值组合[1,5,10,20]
        // 给定张数组合[7,15,20,5]
        // 目标金额: 20
        // 8张
        @Test
        public void test2() {
            int[] money = {1, 5, 10, 20};
            int[] nums = {7, 15, 20, 5};
            int aim = 125;
            SolutionDpSlopeOptimize solution = new SolutionDpSlopeOptimize();
            int ans = solution.calculateMinNums(money, nums, aim);
            System.out.println(ans);
        }

    }

}
