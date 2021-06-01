package question.class5_二叉树中的相等子树数量.code;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.PrintTreeNodeUtil;
import util.TreeConvert;
import util.TreeNode;

/**
 * @author by catface
 * @date 2021/6/1 2:53 下午
 */
@Slf4j
public class Solution {

    private int id;
    private Map<String, Integer> idMap;

    public int sameTreeNums(TreeNode root) {
        this.id = 1;
        this.idMap = new HashMap<>();
        return processSameTreeNums(root);
    }

    private int processSameTreeNums(TreeNode x) {
        if (x == null) {
            return 0;
        }
        int nums = 0;
        nums += processSameTreeNums(x.left);
        nums += processSameTreeNums(x.right);
        // 可以进行适当的剪枝操作,左右节点的value不相等时,不用做序列化判断
        int leftId = serialTreeInPre(x.left);
        int rightId = serialTreeInPre(x.right);
        if (leftId == rightId) {
            nums++;
        }
        return nums;
    }

    public int serialTreeInPre(TreeNode x) {
        if (x == null) {
            return 0;
        } else {
            int leftId = serialTreeInPre(x.left);
            int rightId = serialTreeInPre(x.right);
            String xStr = x.val + "_" + leftId + "_" + rightId;
            return idMap.computeIfAbsent(xStr, key -> id++);
        }
    }

    public static class TestClass {

        @Test
        public void test1() {
            Solution solution = new Solution();
            TreeNode root = TreeConvert.convert(new Integer[] {2, 2, 2, 1, 1, 1, 1, 1, 1, 1,1});
            int ans = solution.sameTreeNums(root);
            PrintTreeNodeUtil.printTree(root);
            log.info("ans:{}", ans);
        }

    }

}
