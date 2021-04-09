package util;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/8 8:07 下午
 */
public class PrintTreeNodeUtilTest {

    @Test
    public void test_1() {
        Integer[] array = ArrayUtil.randomIntegerArray(50, 1, 99);
        TreeNode root = TreeConvert.convert(array);
        PrintTreeNodeUtil.printTree(root);
    }

    @Test
    public void test_2() {
        Integer[] array = new Integer[] {4, 2, 6, 1, 3, 5, 7};
        TreeNode root = TreeConvert.convert(array);
        PrintTreeNodeUtil.printTree(root);
    }

    @Test
    public void test_3() {
        Integer[] array = new Integer[] {1, 1, 1, 1, 1};
        TreeNode root = TreeConvert.convert(array);
        PrintTreeNodeUtil.printTree(root);
    }

    @Test
    public void test_4() {
        Integer[] array = new Integer[] {1, 1, null, 1, null, 1, 1, 1};
        TreeNode root = TreeConvert.convert(array);
        PrintTreeNodeUtil.printTree(root);
    }

}
