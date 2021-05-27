package question.class3_二叉树中距离指定节点为K的节点.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.ArrayUtil;
import util.PrintTreeNodeUtil;
import util.TreeConvert;
import util.TreeNode;

/**
 * @author by catface
 * @date 2021/5/27 4:52 下午
 */
@Slf4j
public class Solution {

    public List<TreeNode> getAllKDistanceNodes(TreeNode head, TreeNode target, int K) {
        Map<TreeNode, TreeNode> map = new HashMap<>();
        // 不要忘记放头
        addParentToMap(head, map);
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        List<TreeNode> ans = new ArrayList<>();
        queue.offer(target);
        visited.add(target);
        int currentLevel = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode curNode = queue.poll();
                if (currentLevel == K) {
                    ans.add(curNode);
                    continue;
                }
                // 左孩子是否存在,存在加入队列,作为下一个level的节点
                if (curNode.left != null && !visited.contains(curNode.left)) {
                    queue.add(curNode.left);
                    visited.add(curNode.left);
                }
                // 右孩子是否存在,存在加入队列,作为下一个level的节点
                if (curNode.right != null && !visited.contains(curNode.right)) {
                    queue.add(curNode.right);
                    visited.add(curNode.right);
                }
                // 父节点是否存在,如果存在,加入队列,只有root节点没有parent,其他都有
                if (curNode != head) {
                    TreeNode parentNode = map.get(curNode);
                    if (!visited.contains(parentNode)) {
                        queue.add(parentNode);
                        visited.add(parentNode);
                    }
                }
            }
            currentLevel++;
            if (currentLevel > K) {
                break;
            }
        }
        return ans;
    }

    public void addParentToMap(TreeNode x, Map<TreeNode, TreeNode> map) {
        if (x == null) {
            return;
        }
        if (x.left != null) {
            map.put(x.left, x);
            addParentToMap(x.left, map);
        }
        if (x.right != null) {
            map.put(x.right, x);
            addParentToMap(x.right, map);
        }
    }

    public static class TestClass {
        @Test
        public void test1() {
            TreeNode root = TreeConvert.convert(ArrayUtil.randomIntegerArray(122, 1, 30));
            Solution solution = new Solution();
            List<TreeNode> ans = solution.getAllKDistanceNodes(root, root.left.left.left, 3);
            root.left.left.left.val = 888;
            for (TreeNode an : ans) {
                an.val = 999;
            }
            PrintTreeNodeUtil.printTree(root);
        }
    }

}
