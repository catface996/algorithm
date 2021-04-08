package util;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/8 8:07 下午
 */
public class PrintTreeNodeUtilTest {

    @Test
    public void test_1() {
        Integer[] array = ArrayUtil.randomIntegerArray(63, 1, 99);
        TreeNode root = TreeConvert.convert(array);
        PrintTreeNodeUtil.printTree(root);
    }

}
