package com.algorithm.course.暴力递归动态规划.两智者卡片对决;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/10 10:27 下午
 */
public class SolutionUseCache {

    //给定一个整型数组arr，代表数值不同的纸牌排成一条线
    //玩家A和玩家B依次拿走每张纸牌
    //规定玩家A先拿，玩家B后拿
    //但是每个玩家每次只能拿走最左或最右的纸牌
    //玩家A和玩家B都绝顶聪明
    //请返回最后获胜者的分数。

    public int winCardGameUseCache(int[] cards) {
        int[][] firstHandDp = initDp(cards.length);
        int[][] nextHandDp = initDp(cards.length);
        int fistHandWindNum = firstHandUseCache(cards, 0, cards.length - 1, firstHandDp, nextHandDp);
        int nextHandWinNum = nextHandUseCache(cards, 0, cards.length - 1, firstHandDp, nextHandDp);
        return Math.max(fistHandWindNum, nextHandWinNum);
    }

    private int[][] initDp(int cardsLength) {
        int[][] dp = new int[cardsLength][cardsLength];
        for (int i = 0; i < cardsLength; i++) {
            for (int j = 0; j < cardsLength; j++) {
                dp[i][j] = -1;
            }
        }
        return dp;
    }

    /**
     * 作为先手能拿到的最大点数
     *
     * @param cards       卡片列表
     * @param leftIndex   左侧允许拿牌的位置
     * @param rightIndex  右侧允许拿牌的位置
     * @param firstHandDp 先手缓存
     * @param nextHandDp  后手缓存
     * @return 先手能拿到的最大点数
     */
    private int firstHandUseCache(int[] cards, int leftIndex, int rightIndex, int[][] firstHandDp, int[][] nextHandDp) {
        if (leftIndex == rightIndex) {
            return cards[leftIndex];
        }
        int ans = firstHandDp[leftIndex][rightIndex];
        if (ans != -1) {
            return ans;
        }
        // 如果选择左侧的卡片,需要当前左侧卡片的点数 + 在剩余卡片中作为后手能拿到的最大点数
        int chooseLeftNum = cards[leftIndex] + nextHandUseCache(cards, leftIndex + 1, rightIndex, firstHandDp,
            nextHandDp);
        // 如果选择右侧的卡片,需要当前右侧卡片点数 + 在剩余卡片中作为后手能拿到的最大点数
        int chooseRightNum = cards[rightIndex] + nextHandUseCache(cards, leftIndex, rightIndex - 1, firstHandDp,
            nextHandDp);
        ans = Math.max(chooseLeftNum, chooseRightNum);
        firstHandDp[rightIndex][leftIndex] = ans;
        return ans;
    }

    private int nextHandUseCache(int[] cards, int leftIndex, int rightIndex, int[][] firstHandDp, int[][] nextHandDp) {
        if (rightIndex == leftIndex) {
            return 0;
        }
        int ans = nextHandDp[leftIndex][rightIndex];
        if (ans != -1) {
            return ans;
        }
        // 在剩余卡牌为N张的前提下,做后手决策,处于被动选择的状态,先手选卡的人,选取卡片后,会留给自己在N-1张的情况下,作为先手,能获得到的点数最小
        // 如果选择左侧卡片,在剩余N-1张卡片中,做先手决策
        int chooseLeft = firstHandUseCache(cards, leftIndex + 1, rightIndex, firstHandDp, nextHandDp);
        int chooseRight = firstHandUseCache(cards, leftIndex, rightIndex - 1, firstHandDp, nextHandDp);
        ans = Math.min(chooseLeft, chooseRight);
        nextHandDp[leftIndex][rightIndex] = ans;
        return ans;
    }

    public static class TestClass {
        @Test
        public void test1() {
            int[] cards = {5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7};
            SolutionUseCache solutionUseCache = new SolutionUseCache();
            int winCardNum = solutionUseCache.winCardGameUseCache(cards);
            Solution solution = new Solution();
            int winCardNumUseCache = solution.winCardGame(cards);
            System.out.println("结果(no cache):" + winCardNum + " 结果(use cache) " + winCardNumUseCache);
        }
    }
}
