package com.algorithm.leetcode.贪心.P135发糖果;

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

import com.algorithm.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
@Slf4j
public class Solution {
    // 求时间复杂度是O(N),空间复杂度是O(1)

    public int candy(int[] ratings) {
        // 1.递增->持平
        // 2.递增,递减
        //
        return 1;
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
            Solution solution = new Solution();
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
            Solution solution = new Solution();
            int ans = solution.candy(ratings);
            System.out.println(ans);
        }

        @Test
        public void test3() {
            int[] ratings = {3, 7, 8, 5, 4, 3, 2, 2, 5};
            Solution solution = new Solution();
            int ans = solution.candy(ratings);
            System.out.println(ans);
        }

        @Test
        public void testForce() {
            int[] ratings = ArrayUtil.randomIntArray(10, 1, 10);
            Solution solution = new Solution();
            int ans = solution.candy(ratings);
            int ans2 = Code05_CandyProblem.candy2(ratings);
            if (ans != ans2) {
                log.info("ans:{},ans2:{},arr:{}", ans, ans2, ratings);
            }
            assert ans == ans2;
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

