package course.暴力递归动态规划.两智者卡片对决;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/12 11:06 上午
 */
public class SolutionUseDp {

    //给定一个整型数组arr，代表数值不同的纸牌排成一条线
    //玩家A和玩家B依次拿走每张纸牌
    //规定玩家A先拿，玩家B后拿
    //但是每个玩家每次只能拿走最左或最右的纸牌
    //玩家A和玩家B都绝顶聪明
    //请返回最后获胜者的分数。

    private int winCardGameUseDp(int[] cards) {
        int[][] firstHandDp = new int[cards.length][cards.length];
        int[][] nextHandDp = new int[cards.length][cards.length];

        // 如果仅剩一张,先手获得点数为卡片的牌面点数,后手获得的点数为0
        for (int i = 0; i < cards.length; i++) {
            firstHandDp[i][i] = cards[i];
            // 填充nextHand可以省略,默认初始为0
            nextHandDp[i][i] = 0;
        }
        // 如果是常规位置,f[i][j] = Math.max(cards[i]+n[i+1][j],cards[j]+n[i][j-1])
        // 首先填充f,再填充n
        for (int i = cards.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < cards.length; j++) {
                firstHandDp[i][j] = Math.max(cards[i] + nextHandDp[i + 1][j], cards[j] + nextHandDp[i][j - 1]);
                nextHandDp[i][j] = Math.min(firstHandDp[i + 1][j], firstHandDp[i][j - 1]);
            }
        }
        return Math.max(firstHandDp[0][cards.length - 1], nextHandDp[0][cards.length - 1]);
    }

    public static class TestClass {
        @Test
        public void test1() {
            int[] cards = {5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7, 12, 23, 5};
            SolutionUseDp solutionUseDp = new SolutionUseDp();
            int winCardNum = solutionUseDp.winCardGameUseDp(cards);
            Solution solution = new Solution();
            int winCardNumUseCache = solution.winCardGame(cards);
            System.out.println("结果(use dp):" + winCardNum + " 结果(use cache) " + winCardNumUseCache);
        }
    }
}
