package course.morris.impl;

import org.junit.Test;
import util.ArrayUtil;
import util.PrintTreeNodeUtil;
import util.TreeConvert;
import util.TreeNode;

/**
 * @author by catface
 * @date 2021/4/30 4:56 下午
 */
public class Solution {

    public void morris(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode mostRight;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
    }

    public void morrisPre(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode mostRight;
        while (cur != null) {
            if (cur.left != null) {
                // 当前指向的节点有左树,寻找左树的最右节点
                mostRight = cur.left;
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    // mostRight == null 表明首次进入左树;先序遍历的处理时机,发现最右,处理头结点
                    System.out.print(cur.val + "->");
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {
                // 如果左侧节点不为空,处理左侧节点
                System.out.print(cur.val + "->");
            }
            cur = cur.right;
        }
        System.out.println();
    }

    public void morrisIn(TreeNode root){
        if (root == null){

        }
    }

    public static class TestClass {
        @Test
        public void testMorrisPre() {
            TreeNode root = TreeConvert.convert(ArrayUtil.randomIntegerArray(13, 1, 40));
            PrintTreeNodeUtil.printTree(root);
            Solution solution = new Solution();
            solution.morrisPre(root);
        }
    }
}
