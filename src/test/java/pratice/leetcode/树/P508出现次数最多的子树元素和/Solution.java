package pratice.leetcode.树.P508出现次数最多的子树元素和;

//给你一个二叉树的根结点，请你找出出现次数最多的子树元素和。一个结点的「子树元素和」定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。
//
// 你需要返回出现次数最多的子树元素和。如果有多个元素出现的次数相同，返回所有出现次数最多的子树元素和（不限顺序）。
//
//
//
// 示例 1：
//输入:
//
//   5
// /  \
//2   -3
//
//
// 返回 [2, -3, 4]，所有的值均只出现一次，以任意顺序返回所有值。
//
// 示例 2：
//输入：
//
//   5
// /  \
//2   -5
//
//
// 返回 [2]，只有 2 出现两次，-5 只出现 1 次。
//
//
//
// 提示： 假设任意子树元素和均可以用 32 位有符号整数表示。
// Related Topics 树 哈希表
// 👍 107 👎 0

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by catface
 * @date 2021/4/2 8:11 下午
 */
class Solution {

    Map<Integer, Integer> subSumCount = new HashMap<>();

    public int[] findFrequentTreeSum(TreeNode root) {
        /*
        8:26 下午	info
				解答成功:
				执行耗时:6 ms,击败了49.53% 的Java用户
				内存消耗:39.1 MB,击败了14.84% 的Java用户
         */
        if (root == null) {
            return new int[0];
        }
        process(root);
        int maxCount = 0;
        for (Integer value : subSumCount.values()) {
            maxCount = Math.max(maxCount, value);
        }
        List<Integer> ansList = new ArrayList<>();
        for (Integer key : subSumCount.keySet()) {
            if (subSumCount.get(key) == maxCount) {
                ansList.add(key);
            }
        }
        int[] ans = new int[ansList.size()];
        for (int i = 0; i < ansList.size(); i++) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }

    private int process(TreeNode x) {

        if (x == null) {
            return 0;
        }
        int leftSubSum = process(x.left);
        int rightSubSum = process(x.right);
        int subSum = x.val + leftSubSum + rightSubSum;
        subSumCount.merge(subSum, 1, Integer::sum);
        return subSum;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

