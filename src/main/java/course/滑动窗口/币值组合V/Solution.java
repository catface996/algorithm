package course.滑动窗口.币值组合V;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/23 8:42 下午
 */
public class Solution {

    // 给定面值组合[1,5,10,20]
    // 给定张数组合[7,15,20,5]
    // 求组成目标金额的方案数

    public int calculateMinNums(int[] money, int[] nums, int aim) {
        return process(money, nums, 0, aim);
    }

    /**
     * 使用指定位置的货币的前提下,能够组成目标金额的方案数
     *
     * @param money      面值数组
     * @param nums       面值对应的张数
     * @param current    当前使用到的面值下标
     * @param leftAmount 剩余需要组装的金额
     * @return 组合数
     */
    private int process(int[] money, int[] nums, int current, int leftAmount) {
        // 剩余金额为0,发现一种方案
        if (leftAmount == 0) {
            return 0;
        }
        // 剩余待组装金额小于0,说明上次使用的币值过大,不是有效的组合方案
        if (leftAmount < 0) {
            return Integer.MAX_VALUE;
        }
        // 所有的面值均已尝试后,剩余金额大于0,无效的组合方案
        if (current >= money.length) {
            return Integer.MAX_VALUE;
        }

        int minNum = Integer.MAX_VALUE;
        for (int i = 0; i <= nums[current] && i * money[current] <= leftAmount; i++) {
            int temNum = process(money, nums, current + 1, leftAmount - i * money[current]);
            if (temNum != Integer.MAX_VALUE) {
                temNum += i;
                minNum = Math.min(minNum, temNum);
            }
        }
        return minNum;
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
            Solution solution = new Solution();
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
            Solution solution = new Solution();
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
            Solution solution = new Solution();
            int ans = solution.calculateMinNums(money, nums, aim);
            System.out.println(ans);
        }

    }

}
