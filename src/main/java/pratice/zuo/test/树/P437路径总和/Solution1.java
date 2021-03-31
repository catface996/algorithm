package pratice.zuo.test.树.P437路径总和;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by catface
 * @date 2021/3/31 2:27 下午
 */
public class Solution1 {

    //TODO 3:12 下午	info
    //				解答成功:
    //				执行耗时:30 ms,击败了52.32% 的Java用户
    //				内存消耗:38.9 MB,击败了5.06% 的Java用户

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
        Solution1 solution1 = new Solution1();
        int total = solution1.pathSum(root, 8);
        System.out.println(total);
    }

    int total = 0;

    public int pathSum(TreeNode root, int sum) {
        process(root, sum);
        return total;
    }

    private List<Info> process(TreeNode x, int sum) {
        if (x == null) {
            return new ArrayList<>();
        }

        List<Info> leftList = process(x.left, sum);
        for (Info info : leftList) {
            info.sum += x.val;
            if (info.sum == sum) {
                total++;
            }
        }

        List<Info> rightList = process(x.right, sum);
        for (Info info : rightList) {
            info.sum += x.val;
            if (info.sum == sum) {
                total++;
            }
        }

        List<Info> allInfo = new ArrayList<>();
        allInfo.addAll(leftList);
        allInfo.addAll(rightList);
        allInfo.add(new Info(x.val));
        if (x.val == sum) {
            total++;
        }
        return allInfo;
    }

    public static class Info {
        private int sum;

        public Info(int sum) {
            this.sum = sum;
        }
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
