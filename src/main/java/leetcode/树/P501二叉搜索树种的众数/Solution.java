package leetcode.树.P501二叉搜索树种的众数;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by catface
 * @date 2021/4/1 10:18 上午
 */
public class Solution {

    public int[] findMode(TreeNode root) {
        Map<Integer, Integer> valueTimes = process(root);
        int maxTimes = 0;
        for (Integer times : valueTimes.values()) {
            maxTimes = Math.max(maxTimes, times);
        }
        List<Integer> values = new ArrayList<>();
        for (Integer key : valueTimes.keySet()) {
            if (valueTimes.get(key) == maxTimes) {
                values.add(key);
            }
        }
        int[] result = new int[values.size()];
        for (int i = 0; i < values.size(); i++) {
            result[i] = values.get(i);
        }
        return result;
    }

    private Map<Integer, Integer> process(TreeNode x) {
        if (x == null) {
            return new HashMap<>(0);
        }

        Map<Integer, Integer> leftInfo = process(x.left);
        Map<Integer, Integer> rightInfo = process(x.right);

        // 重新计算当前节点值出现的次数
        int equalParentTimes = 1;
        if (leftInfo.containsKey(x.val)) {
            equalParentTimes += leftInfo.get(x.val);
            leftInfo.remove(x.val);
        }
        if (rightInfo.containsKey(x.val)) {
            equalParentTimes += rightInfo.get(x.val);
        }
        rightInfo.put(x.val, equalParentTimes);

        for (Integer leftKey : leftInfo.keySet()) {
            if (rightInfo.containsKey(leftKey)) {
                rightInfo.put(leftKey, leftInfo.get(leftKey) + rightInfo.get(leftKey));
            } else {
                rightInfo.put(leftKey, leftInfo.get(leftKey));
            }
        }

        return rightInfo;
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
