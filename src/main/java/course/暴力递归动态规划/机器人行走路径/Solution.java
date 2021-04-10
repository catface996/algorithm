package course.暴力递归动态规划.机器人行走路径;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/10 3:26 下午
 */
public class Solution {

    //给定四个参数N、P、M、K。表示：
    //N : 一共有1～N个位置
    //P : 一共有P步要走
    //M : 机器人初始停留在M位置上
    //K : 机器人想要去的位置是K
    //题目：已知，如果机器人来到 1 位置，那么下一步一定会走到 2 位置。如果机器人来到 N 位置，那么下一步一定会走到 N - 1
    // 位置；如果机器人在中间的位置，那么下一步既可以走向左，也可以走向右。请返回，机器人如果初始停留在 M 位置，经过 P 步之后，机器人来到 K 位置的走法有多少种。

    /**
     * @param nPosition       总共有多少个位置
     * @param leftStepNum     剩余多少步到达指定的位置
     * @param currentPosition 当前的位置
     * @param aimPosition     指定的目标位置
     * @return 从当前位置, 经过指定的步数到达指定位置的路径条数
     */
    public int getPathNum(int nPosition, int leftStepNum, int currentPosition, int aimPosition) {
        if (aimPosition > nPosition) {
            return 0;
        }
        if (currentPosition > nPosition) {
            return 0;
        }
        if (leftStepNum < 0) {
            return 0;
        }
        // 如果只有一个位置,只有一种路径
        if (nPosition == 1) {
            return 1;
        }
        if (leftStepNum == 0) {
            // 如果当前位置是目标位置,则有一种方案,否则返回0
            return currentPosition == aimPosition ? 1 : 0;
        }
        if (currentPosition == 1) {
            // 机器人当前在1位置,只能往2(nPosition+1)位置走,所以从1位置到指定位置与从2位置到指定位置的方案数相同
            // 剩余步数-1,当前位置为右侧下一位置,即currentPosition+1
            return getPathNum(nPosition, leftStepNum - 1, currentPosition + 1, aimPosition);
        }
        if (currentPosition == nPosition) {
            // 如果当前位置是末尾位置,机器人只能向左侧走,并且该位置到达目标位置的方案数与左侧下一个位置到达目标位置的方案书相同
            // 剩余步数-1,当前位置为左侧下一位置,即currentPosition-1
            return getPathNum(aimPosition, leftStepNum - 1, currentPosition - 1, aimPosition);
        }

        // 如果机器人在中间某个位置,此时既可以向左,又可以向右,从当前位置到达目标位置的路径数为 向左的路径数+向右的路径数
        // 向左
        int moveLeft = getPathNum(aimPosition, leftStepNum - 1, currentPosition - 1, aimPosition);
        // 向右
        int moveRight = getPathNum(aimPosition, leftStepNum - 1, currentPosition + 1, aimPosition);
        return moveLeft + moveRight;
    }

    /**
     * @param nPosition       总共有多少个位置
     * @param leftStepNum     剩余多少步到达指定的位置
     * @param currentPosition 当前的位置
     * @param aimPosition     指定的目标位置
     * @param dp              记录已经计算过的方案数
     * @return 从当前位置, 经过指定的步数到达指定位置的路径条数
     */
    public int getPathNumUseCache(int nPosition, int leftStepNum, int currentPosition, int aimPosition, int[][] dp) {
        if (aimPosition > nPosition) {
            return 0;
        }
        if (currentPosition > nPosition) {
            return 0;
        }
        if (leftStepNum < 0) {
            return 0;
        }
        // 如果只有一个位置,只有一种路径
        if (nPosition == 1) {
            return 1;
        }
        if (leftStepNum == 0) {
            // 如果当前位置是目标位置,则有一种方案,否则返回0
            return currentPosition == aimPosition ? 1 : 0;
        }
        int ans = dp[currentPosition][leftStepNum];
        if (ans != -1) {
            return ans;
        }
        if (currentPosition == 1) {
            // 机器人当前在1位置,只能往2(nPosition+1)位置走,所以从1位置到指定位置与从2位置到指定位置的方案数相同
            // 剩余步数-1,当前位置为右侧下一位置,即currentPosition+1
            ans = getPathNum(nPosition, leftStepNum - 1, currentPosition + 1, aimPosition);
        } else if (currentPosition == nPosition) {
            // 如果当前位置是末尾位置,机器人只能向左侧走,并且该位置到达目标位置的方案数与左侧下一个位置到达目标位置的方案书相同
            // 剩余步数-1,当前位置为左侧下一位置,即currentPosition-1
            ans = getPathNum(aimPosition, leftStepNum - 1, currentPosition - 1, aimPosition);
        } else {
            // 如果机器人在中间某个位置,此时既可以向左,又可以向右,从当前位置到达目标位置的路径数为 向左的路径数+向右的路径数
            // 向左
            int moveLeft = getPathNum(aimPosition, leftStepNum - 1, currentPosition - 1, aimPosition);
            // 向右
            int moveRight = getPathNum(aimPosition, leftStepNum - 1, currentPosition + 1, aimPosition);
            ans = moveLeft + moveRight;
        }
        dp[currentPosition][leftStepNum] = ans;
        return ans;
    }

    public int process(int cur, int rest, int aim, int N) {
        if (rest == 0) {
            // 如果已经不需要走了，走完了！
            return cur == aim ? 1 : 0;
        }
        // (cur, rest)
        if (cur == 1) {
            // 1 -> 2
            return process(2, rest - 1, aim, N);
        }
        // (cur, rest)
        if (cur == N) {
            // N-1 <- N
            return process(N - 1, rest - 1, aim, N);
        }
        // (cur, rest)
        return process(cur - 1, rest - 1, aim, N) + process(cur + 1, rest - 1, aim, N);
    }

    public int dp(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        int[][] dp = new int[N + 1][K + 1];
        dp[aim][0] = 1;
        for (int rest = 1; rest <= K; rest++) {
            dp[1][rest] = dp[2][rest - 1];
            for (int cur = 2; cur < N; cur++) {
                dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
            }
            dp[N][rest] = dp[N - 1][rest - 1];
        }
        return dp[start][K];
    }

    public static class TestClass {
        @Test
        public void test1() {
            Solution solution = new Solution();
            int pathNums = solution.getPathNum(1, 0, 1, 1);
            System.out.println("路径条数:" + pathNums);
        }

        @Test
        public void test2() {
            Solution solution = new Solution();
            int nPosition = 4;
            int leftSteNum = 5;
            int currentPosition = 1;
            int aimPosition = 4;
            int[][] dp = new int[nPosition + 1][leftSteNum + 1];
            for (int i = 0; i <= nPosition; i++) {
                for (int j = 0; j <= leftSteNum; j++) {
                    dp[i][j] = -1;
                }
            }
            int pathNums = solution.getPathNum(nPosition, leftSteNum, currentPosition, aimPosition);
            int pathNums2 = solution.process(nPosition, leftSteNum, currentPosition, aimPosition);
            int pathNums3 = solution.getPathNumUseCache(nPosition, leftSteNum, currentPosition, aimPosition, dp);
            int pathNum4 = solution.dp(nPosition, currentPosition, aimPosition, leftSteNum);
            System.out.println("路径条数:getPathNum" + pathNums + " process1 " + pathNums2
                + " getPathNumUseCache " + pathNums3 + " dp " + pathNum4);
        }
    }

}
