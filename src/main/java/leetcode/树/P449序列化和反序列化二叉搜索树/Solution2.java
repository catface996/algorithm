package leetcode.树.P449序列化和反序列化二叉搜索树;

/**
 * @author by catface
 * @date 2021/3/31 3:55 下午
 */
public class Solution2 {

    //TODO 4:36 下午	info
    //				解答成功:
    //				执行耗时:5 ms,击败了95.78% 的Java用户
    //				内存消耗:39.2 MB,击败了83.02% 的Java用户
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);
        root.right.left.right = new TreeNode(7);
        Solution2 solution1 = new Solution2();
        String str = solution1.serialize(root);
        System.out.println(str);
        TreeNode root2 = solution1.deserialize(str);
        System.out.println(root2);
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        serializeProcess(root, sb);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private void serializeProcess(TreeNode x, StringBuilder sb) {
        if (x == null) {
            return;
        }
        sb.append(x.val).append(',');
        serializeProcess(x.left, sb);
        serializeProcess(x.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }
        String[] strArr = data.split(",");
        int[] values = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            values[i] = Integer.parseInt(strArr[i]);
        }
        return deserializeProcess(values, 0, values.length - 1);
    }

    private TreeNode deserializeProcess(int[] values, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new TreeNode(values[start]);
        }

        int rootValue = values[start];
        TreeNode root = new TreeNode(rootValue);
        int rightStart = start + 1;
        for (; rightStart <= end; rightStart++) {
            if (rootValue < values[rightStart]) {
                break;
            }
        }
        root.left = deserializeProcess(values, start + 1, rightStart - 1);
        root.right = deserializeProcess(values, rightStart, end);
        return root;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }
}
