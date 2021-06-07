package com.algorithm.leetcode.前缀树.P421数组中两个数的最大异或值;

//给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
//
// 进阶：你可以在 O(n) 的时间解决这个问题吗？
//
//
//
//
//
// 示例 1：
//
//
//输入：nums = [3,10,5,25,2,8]
//输出：28
//解释：最大运算结果是 5 XOR 25 = 28.
//
// 示例 2：
//
//
//输入：nums = [0]
//输出：0
//
//
// 示例 3：
//
//
//输入：nums = [2,4]
//输出：6
//
//
// 示例 4：
//
//
//输入：nums = [8,10,2]
//输出：10
//
//
// 示例 5：
//
//
//输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
//输出：127
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 2 * 104
// 0 <= nums[i] <= 231 - 1
//
//
//
// Related Topics 位运算 字典树
// 👍 362 👎 0

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
@Slf4j
public class Solution1 {

    // 8:42 下午	info
    //				解答成功:
    //				执行耗时:92 ms,击败了39.91% 的Java用户
    //				内存消耗:57.3 MB,击败了6.64% 的Java用户

    Node root;

    public int findMaximumXOR(int[] nums) {
        root = new Node();
        int ans = Integer.MIN_VALUE;
        for (int num : nums) {
            ans = Math.max(ans, xor(num));
        }
        return ans;
    }

    private int xor(int num) {
        Node cur = root;
        Node choose = root;
        int bit;
        for (int bitPosition = 31; bitPosition >= 0; bitPosition--) {
            bit = num >> bitPosition & 1;
            cur = addNode(cur, bit);
            choose = choose.next[1 - bit] != null ? choose.next[1 - bit] : choose.next[bit];
        }
        cur.end = num;
        return choose.end ^ num;
    }

    public Node addNode(Node cur, int bit) {
        if (cur.next[bit] == null) {
            cur.next[bit] = new Node();
        }
        return cur.next[bit];
    }

    public static class Node {
        int end;
        Node[] next = new Node[2];
    }

    public static class TestClass {

        // 示例 1：
        //
        //
        //输入：nums = [3,10,5,25,2,8]
        //输出：28
        //解释：最大运算结果是 5 XOR 25 = 28.
        @Test
        public void test1() {
            int[] nums = {3, 10, 5, 25, 2, 8};
            Solution1 solution = new Solution1();
            int ans = solution.findMaximumXOR(nums);
            log.info("ans:{}", ans);
        }

        //
        // 示例 2：
        //
        //
        //输入：nums = [0]
        //输出：0
        @Test
        public void test2() {
            int[] nums = {0};
            Solution1 solution = new Solution1();
            int ans = solution.findMaximumXOR(nums);
            log.info("ans:{}", ans);
        }

        // 示例 3：
        //
        //
        //输入：nums = [2,4]
        //输出：6
        @Test
        public void test3() {
            int[] nums = {2, 4};
            Solution1 solution = new Solution1();
            int ans = solution.findMaximumXOR(nums);
            log.info("ans:{}", ans);
        }

        // 示例 4：
        //
        //
        //输入：nums = [8,10,2]
        //输出：10
        @Test
        public void test4() {
            int[] nums = {8, 10, 2};
            Solution1 solution = new Solution1();
            int ans = solution.findMaximumXOR(nums);
            log.info("ans:{}", ans);
        }

        // 示例 5：
        //
        //
        //输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
        //输出：127
        @Test
        public void test5() {
            int[] nums = {14, 70, 53, 83, 49, 91, 36, 80, 92, 51, 66, 70};
            Solution1 solution = new Solution1();
            int ans = solution.findMaximumXOR(nums);
            log.info("ans:{}", ans);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)


