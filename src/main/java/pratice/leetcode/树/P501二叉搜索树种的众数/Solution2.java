package pratice.leetcode.树.P501二叉搜索树种的众数;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by catface
 * @date 2021/4/1 2:55 下午
 */
public class Solution2 {

    public int[] findMode(TreeNode root) {
        Map<Integer, Integer> valueTimes = new HashMap<>();
        process(root, valueTimes);
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

    private void process(TreeNode x, Map<Integer, Integer> valueTimes) {
        if (x == null) {
            return;
        }
        if (valueTimes.containsKey(x.val)) {
            valueTimes.put(x.val, valueTimes.get(x.val) + 1);
        } else {
            valueTimes.put(x.val, 1);
        }
        process(x.left, valueTimes);
        process(x.right, valueTimes);
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
