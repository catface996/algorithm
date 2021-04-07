package pratice.leetcode.树.P437路径总和;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by catface
 * @date 2021/3/31 2:27 下午
 */
public class Solution2 {

    //TODO 3:24 下午	info
    //				解答成功:
    //				执行耗时:28 ms,击败了60.45% 的Java用户
    //				内存消耗:39 MB,击败了5.06% 的Java用户

    int total = 0;

    public static void main(String[] args) {
        test_1();
    }

    // [10,5,-3,3,2,null,11,3,-2,null,1]
    public static void test_1() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(11);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);
        Solution2 solution1 = new Solution2();
        int total = solution1.pathSum(root, 8);
        System.out.println(total);
    }

    public int pathSum(TreeNode root, int sum) {
        process(root, sum);
        return total;
    }

    private List<Integer> process(TreeNode x, int sum) {
        if (x == null) {
            return new ArrayList<>();
        }

        List<Integer> leftList = process(x.left, sum);
        List<Integer> rightList = process(x.right, sum);
        List<Integer> allList = new ArrayList<>(leftList.size() + rightList.size() + 1);
        for (Integer tempSum : leftList) {
            tempSum += x.val;
            allList.add(tempSum);
            if (tempSum == sum) {
                total++;
            }
        }

        for (Integer tempSum : rightList) {
            tempSum += x.val;
            allList.add(tempSum);
            if (tempSum == sum) {
                total++;
            }
        }

        allList.add(x.val);
        if (x.val == sum) {
            total++;
        }
        return allList;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) { this.val = val; }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
