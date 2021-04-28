package course.kmp.判断是否是子树;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.ArrayUtil;
import util.PrintTreeNodeUtil;
import util.TreeConvert;
import util.TreeNode;

/**
 * @author by catface
 * @date 2021/4/28 11:19 上午
 */
@Slf4j
public class Solution {

    public boolean isSubTree(TreeNode root1, TreeNode root2) {
        String str1 = serialTree(root1);
        String str2 = serialTree(root2);
        return indexOf(str1, str2) >= 0;
    }

    /**
     * 采取先序遍历序列化二叉树(中序遍历不具有唯一性,也可以用后续遍历)
     * <p>
     * 节点值之间用";"连接
     *
     * @param root 根节点
     * @return 序列化后的字符串
     */
    public String serialTree(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        process(root, sb);
        return sb.substring(0, sb.length() - 2);
    }

    public void process(TreeNode x, StringBuilder sb) {
        if (x == null) {
            sb.append("#").append(";");
            return;
        }
        sb.append(x.val).append(";");
        process(x.left, sb);
        process(x.right, sb);
    }

    public int indexOf(String str1, String str2) {
        if (str1 == null || str2 == null || str2.length() < 1 || str1.length() < str2.length()) {
            return -1;
        }
        int[] next = buildNext(str2);
        int i = 0;
        int j = 0;
        while (i < str1.length() && j < str2.length()) {
            if (str1.charAt(i) == str2.charAt(j)) {
                i++;
                j++;
            } else if (next[j] == -1) {
                i++;
            } else {
                j = next[j];
            }
        }
        return j == str2.length() ? i - j : -1;
    }

    private int[] buildNext(String str2) {
        int[] next = new int[str2.length()];
        next[0] = -1;
        int i = 2;
        int cn = 0;
        while (i < str2.length()) {
            if (str2.charAt(i - 1) == str2.charAt(cn)) {
                next[++cn] = i++;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static class TestClass {
        @Test
        public void testIndexOf() {
            String str1 = "ababababca";
            String str2 = "abababca";
            Solution solution = new Solution();
            int firstIndex = solution.indexOf(str1, str2);
            System.out.println(firstIndex);
        }

        @Test
        public void testIsSubTree1() {
            Integer[] arr = ArrayUtil.randomIntegerArray(10, 1, 40);
            TreeNode root1 = TreeConvert.convert(arr);
            Solution solution = new Solution();
            boolean ans = solution.isSubTree(root1, root1);
            log.info("ans:{}", ans);
        }

        @Test
        public void testIsSubTree2() {
            Integer[] arr1 = ArrayUtil.randomIntegerArray(10, 1, 40);
            TreeNode root1 = TreeConvert.convert(arr1);
            PrintTreeNodeUtil.printTree(root1);
            Integer[] arr2 = ArrayUtil.randomIntegerArray(3, 1, 40);
            TreeNode root2 = TreeConvert.convert(arr2);
            PrintTreeNodeUtil.printTree(root2);
            Solution solution = new Solution();
            boolean ans = solution.isSubTree(root1, root2);
            log.info("ans:{}", ans);
        }

        @Test
        public void testIsSubTree3() {
            Integer[] arr1 = {9, 32, 38};
            TreeNode root1 = TreeConvert.convert(arr1);
            PrintTreeNodeUtil.printTree(root1);
            Integer[] arr2 = {9, 32};
            TreeNode root2 = TreeConvert.convert(arr2);
            PrintTreeNodeUtil.printTree(root2);
            Solution solution = new Solution();
            boolean ans = solution.isSubTree(root1, root2);
            log.info("ans:{}", ans);
        }
    }

}
