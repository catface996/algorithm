package course.暴力递归动态规划.两智者卡片对决;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/10 10:27 下午
 */
public class Solution {

    //给定一个整型数组arr，代表数值不同的纸牌排成一条线
    //玩家A和玩家B依次拿走每张纸牌
    //规定玩家A先拿，玩家B后拿
    //但是每个玩家每次只能拿走最左或最右的纸牌
    //玩家A和玩家B都绝顶聪明
    //请返回最后获胜者的分数。

    public int winCardGame(int[] cards) {
        if (cards == null || cards.length == 0) {
            return 0;
        }
        int first = firstHand(cards, 0, cards.length - 1);
        int second = nextHand(cards, 0, cards.length - 1);
        return Math.max(first, second);
    }

    public int firstHand(int[] cards, int leftIndex, int rightIndex) {
        if (leftIndex == rightIndex) {
            return cards[leftIndex];
        }
        // 如果本轮选取后,那么自然希望 另一方选取之后,自己作为另一方选择后的后手,希望得到最大点数
        // 如果本轮选取左侧
        int chooseLeft = cards[leftIndex] + nextHand(cards, leftIndex + 1, rightIndex);
        // 如果本轮选取右侧
        int chooseRight = cards[rightIndex] + nextHand(cards, leftIndex, rightIndex - 1);
        return Math.max(chooseLeft, chooseRight);
    }

    public int nextHand(int[] cards, int leftIndex, int rightIndex) {
        if (leftIndex == rightIndex) {
            return 0;
        }
        // 如果作为后手选牌,另一方选牌则是先手选牌,期望留个另一方最小的点数
        int chooseLeft = firstHand(cards, leftIndex + 1, rightIndex);
        int chooseRight = firstHand(cards, leftIndex, rightIndex - 1);
        return Math.min(chooseLeft, chooseRight);
    }

    // 根据规则，返回获胜者的分数
    public int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int first = f1(arr, 0, arr.length - 1);
        int second = g1(arr, 0, arr.length - 1);
        return Math.max(first, second);
    }

    // arr[L..R]，先手获得的最好分数返回
    private int f1(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        int p1 = arr[L] + g1(arr, L + 1, R);
        int p2 = arr[R] + g1(arr, L, R - 1);
        return Math.max(p1, p2);
    }

    // // arr[L..R]，后手获得的最好分数返回
    private int g1(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int p1 = f1(arr, L + 1, R); // 对手拿走了L位置的数
        int p2 = f1(arr, L, R - 1); // 对手拿走了R位置的数
        return Math.min(p1, p2);
    }

    public static class TestClass {
        @Test
        public void test1() {
            int[] cards = {5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7};
            Solution solution = new Solution();
            int winCardNum = solution.winCardGame(cards);
            int ans = solution.win1(cards);
            System.out.println("结果1:" + winCardNum + " 结果2 " + ans);
        }
    }
}
