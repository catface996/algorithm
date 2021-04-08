package leetcode.树.P297二叉树的序列化和反序列化;

/**
 * @author by catface
 * @date 2021/3/31 10:21 上午
 */
public class Solution2 {

    // 11:17 上午	info
    //				解答成功:
    //				执行耗时:7 ms,击败了98.72% 的Java用户
    //				内存消耗:40.1 MB,击败了85.45% 的Java用户
    // root = [1,2,3,null,null,4,5]
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        Solution2 solution = new Solution2();
        String str = solution.serialize(root);
        System.out.println(str);

        TreeNode root2 = solution.deserialize(str);
        System.out.println(root2);

    }

    public static final String NULL_NODE = "#";
    public static final String SEPARATOR = ",";
    public static final char SEPARATOR_CHAR = ',';
    public int head = 0;
    public int tail = 0;

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder(128);
        serializeProcess(root, sb);
        return sb.toString();
    }

    private void serializeProcess(TreeNode x, StringBuilder sb) {
        if (x == null) {
            sb.append(NULL_NODE).append(SEPARATOR);
            return;
        }
        sb.append(x.val).append(SEPARATOR);

        serializeProcess(x.left, sb);

        serializeProcess(x.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        return deserializeProcess(data);
    }

    public TreeNode deserializeProcess(String str) {
        String value = getNextStr(str);
        if (value == null || NULL_NODE.equals(value)) {
            return null;
        }
        TreeNode currentRoot = new TreeNode(Integer.parseInt(value));
        currentRoot.left = deserializeProcess(str);
        currentRoot.right = deserializeProcess(str);
        return currentRoot;
    }

    public String getNextStr(String str) {
        if (tail >= str.length()) {
            return null;
        }
        for (; ; ) {
            if (str.charAt(tail) == SEPARATOR_CHAR) {
                String value = str.substring(head, tail);
                tail++;
                head = tail;
                return value;
            }
            tail++;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }
}
