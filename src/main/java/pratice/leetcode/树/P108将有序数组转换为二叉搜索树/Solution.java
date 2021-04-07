package pratice.leetcode.树.P108将有序数组转换为二叉搜索树;

/**
 * @author by catface
 * @date 2021/3/29 2:40 下午
 */
public class Solution {

    public static void main(String[] args) {
        int[] nums = new int[] {1, 3};
        Solution solution = new Solution();
        TreeNode treeNode = solution.sortedArrayToBST(nums);
        System.out.println(treeNode);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return process(nums, 0, nums.length - 1);
    }

    private TreeNode process(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new TreeNode(nums[start]);
        }
        int middle = (start + end + 1) / 2;
        TreeNode root = new TreeNode(nums[middle]);
        root.left = process(nums, start, middle - 1);
        root.right = process(nums, middle + 1, end);
        return root;
    }

    public class TreeNode {
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
