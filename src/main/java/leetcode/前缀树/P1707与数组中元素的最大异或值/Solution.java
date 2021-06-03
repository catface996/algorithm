package leetcode.前缀树.P1707与数组中元素的最大异或值;
//给你一个由非负整数组成的数组 nums 。另有一个查询数组 queries ，其中 queries[i] = [xi, mi] 。
//
// 第 i 个查询的答案是 xi 和任何 nums 数组中不超过 mi 的元素按位异或（XOR）得到的最大值。换句话说，答案是 max(nums[j] XOR
// xi) ，其中所有 j 均满足 nums[j] <= mi 。如果 nums 中的所有元素都大于 mi，最终答案就是 -1 。
//
// 返回一个整数数组 answer 作为查询的答案，其中 answer.length == queries.length 且 answer[i] 是第 i 个
//查询的答案。
//
//
//
// 示例 1：
//
// 输入：nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]]
//输出：[3,3,7]
//解释：
//1) 0 和 1 是仅有的两个不超过 1 的整数。0 XOR 3 = 3 而 1 XOR 3 = 2 。二者中的更大值是 3 。
//2) 1 XOR 2 = 3.
//3) 5 XOR 2 = 7.
//
//
// 示例 2：
//
// 输入：nums = [5,2,4,6,6,3], queries = [[12,4],[8,1],[6,3]]
//输出：[15,-1,5]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length, queries.length <= 105
// queries[i].length == 2
// 0 <= nums[j], xi, mi <= 109
//
// Related Topics 位运算 字典树
// 👍 116 👎 0

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
@Slf4j
public class Solution {

    // 3:57 下午	info
    //				运行失败:
    //				Time Limit Exceeded

    Node root;

    public int[] maximizeXor(int[] nums, int[][] queries) {
        root = new Node();
        build(nums);
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = processMaxXorLeLimit(root, 30, queries[i][0], queries[i][1], false);
        }
        return ans;
    }

    /**
     * @param choose      上次选择的前缀节点
     * @param bitPosition 当前要比对的二级制位
     * @param num         要异或的数字
     * @param limit       限制数字,前缀树中的数字不能大于限制数字
     * @param any         是否可以随意选择,true:可以随意选择,false:不能随意选择
     * @return 最大异或值
     */
    private int processMaxXorLeLimit(Node choose, int bitPosition, int num, int limit, boolean any) {
        // 上一个选择的路径为空,无法继续,返回-1
        if (choose == null) {
            return -1;
        }

        if (bitPosition < 0) {
            return choose.end ^ num;
        }

        // 之前的选择已经小于limit,后续可以随意选择
        if (any) {
            // 如果限制数字的第i位为1,可以选择最优,也可以不做最优选择,最优选择不一定能进行到底
            int p1 = processMaxXorLeLimit(choose.next[0], bitPosition - 1, num, limit, true);
            int p2 = processMaxXorLeLimit(choose.next[1], bitPosition - 1, num, limit, true);
            return Math.max(p1, p2);
        }

        int limitBit = (limit >> bitPosition) & 1;
        if (limitBit == 0) {
            // 无论最优选择是什么,都必读选0分支进行异或,否则参与异或的数字会超过limit限制
            choose = choose.next[0];
            return processMaxXorLeLimit(choose, bitPosition - 1, num, limit, false);
        }

        // 限制数字的当前位为1时,选择0,则后续的选择被异或的数字一定小于limit,可以随意选择,选择1时,不能随意选择
        int p1 = processMaxXorLeLimit(choose.next[0], bitPosition - 1, num, limit, true);
        int p2 = processMaxXorLeLimit(choose.next[1], bitPosition - 1, num, limit, false);
        return Math.max(p1, p2);

    }

    private void build(int[] nums) {
        Node cur;
        int bit;
        for (int num : nums) {
            cur = this.root;
            // 由于都是非负数,第一31位一定是0,无需加入前缀树
            for (int bitPosition = 30; bitPosition >= 0; bitPosition--) {
                bit = (num >> bitPosition) & 1;
                if (cur.next[bit] == null) {
                    cur.next[bit] = new Node();
                }
                cur = cur.next[bit];
            }
            cur.end = num;
        }
    }

    public static class Node {
        private Node[] next = new Node[2];
        private int end;
    }

    public static class TestClass {
        // 示例 1：
        //
        // 输入：nums = [0,1,2,3,4], queries = [[3,1],[1,3],[5,6]]
        //输出：[3,3,7]
        //解释：
        //1) 0 和 1 是仅有的两个不超过 1 的整数。0 XOR 3 = 3 而 1 XOR 3 = 2 。二者中的更大值是 3 。
        //2) 1 XOR 2 = 3.
        //3) 5 XOR 2 = 7.
        @Test
        public void test1() {
            int[] nums = {0, 1, 2, 3, 4};
            int[][] queries = {{3, 1}, {1, 3}, {5, 6}};
            Solution solution = new Solution();
            int[] ans = solution.maximizeXor(nums, queries);
            log.info("ans:{}", ans);
        }

        // 示例 2：
        //
        // 输入：nums = [5,2,4,6,6,3], queries = [[12,4],[8,1],[6,3]]
        //输出：[15,-1,5]
        @Test
        public void test2() {
            int[] nums = {5, 2, 4, 6, 6, 3};
            int[][] queries = {{12, 4}, {8, 1}, {6, 3}};
            Solution solution = new Solution();
            int[] ans = solution.maximizeXor(nums, queries);
            log.info("ans:{}", ans);
        }

        @Test
        public void test3() {
            int[] nums = {1, 2, 3, 4};
            Solution solution = new Solution();
            solution.root = new Node();
            Node node = solution.root;
            solution.build(nums);
            while (node.next[0] != null) {
                System.out.println(node.end);
                node = node.next[0];
            }
            System.out.println(node.next[1].end);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

