package leetcode.贪心.P135发糖果;

//老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
//
// 你需要按照以下要求，帮助老师给这些孩子分发糖果：
//
//
// 每个孩子至少分配到 1 个糖果。
// 评分更高的孩子必须比他两侧的邻位孩子获得更多的糖果。
//
//
// 那么这样下来，老师至少需要准备多少颗糖果呢？
//
//
//
// 示例 1：
//
//
//输入：[1,0,2]
//输出：5
//解释：你可以分别给这三个孩子分发 2、1、2 颗糖果。
//
//
// 示例 2：
//
//
//输入：[1,2,2]
//输出：4
//解释：你可以分别给这三个孩子分发 1、2、1 颗糖果。
//     第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
// Related Topics 贪心算法
// 👍 560 👎 0

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.ArrayUtil;

//leetcode submit region begin(Prohibit modification and deletion)
@Slf4j
public class Solution1 {

    // 11:45 下午	info
    //					解答成功:
    //					执行耗时:3 ms,击败了63.99% 的Java用户
    //					内存消耗:40.2 MB,击败了8.41% 的Java用户
    // 从左往右,只记录递增,从右往左,只记录递增,每个位置取最大值

    public int candy(int[] ratings) {
        int[] candyArrLeftToRight = new int[ratings.length];
        int[] candyArrRightToLeft = new int[ratings.length];
        Arrays.fill(candyArrLeftToRight, 1);
        Arrays.fill(candyArrRightToLeft, 1);
        for (int i = 0; i < ratings.length - 1; i++) {
            if (ratings[i + 1] > ratings[i]) {
                candyArrLeftToRight[i + 1] = candyArrLeftToRight[i] + 1;
            }
        }
        for (int i = ratings.length - 1; i > 0; i--) {
            if (ratings[i - 1] > ratings[i]) {
                candyArrRightToLeft[i - 1] = candyArrRightToLeft[i] + 1;
            }
        }
        int sum = 0;
        for (int i = 0; i < ratings.length; i++) {
            sum += Math.max(candyArrLeftToRight[i], candyArrRightToLeft[i]);
        }
        return sum;
    }

    public static class TestClass {
        // 示例 1：
        //
        //
        //输入：[1,0,2]
        //输出：5
        //解释：你可以分别给这三个孩子分发 2、1、2 颗糖果。
        @Test
        public void test1() {
            int[] ratings = {1, 0, 2};
            Solution1 solution = new Solution1();
            int ans = solution.candy(ratings);
            System.out.println(ans);
        }

        // 示例 2：
        //
        //
        //输入：[1,2,2]
        //输出：4
        //解释：你可以分别给这三个孩子分发 1、2、1 颗糖果。
        //     第三个孩子只得到 1 颗糖果，这已满足上述两个条件。
        @Test
        public void test2() {
            int[] ratings = {1, 2, 2};
            Solution1 solution = new Solution1();
            int ans = solution.candy(ratings);
            System.out.println(ans);
        }

        @Test
        public void test3() {
            int[] ratings = {3, 7, 8, 5, 4, 3, 2, 2, 5};
            Solution1 solution = new Solution1();
            int ans = solution.candy(ratings);
            System.out.println(ans);
            int ans2 = Code05_CandyProblem.candy2(ratings);
            System.out.println(ans2);
        }

        @Test
        public void testForce() {
            for (int i = 0; i < 1000; i++) {
                int[] ratings = ArrayUtil.randomIntArray(10, 1, 10);
                Solution1 solution = new Solution1();
                int ans = solution.candy(ratings);
                int ans2 = Code05_CandyProblem.candy2(ratings);
                if (ans != ans2) {
                    log.info("ans:{},ans2:{},arr:{}", ans, ans2, ratings);
                }
                assert ans == ans2;
            }
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

