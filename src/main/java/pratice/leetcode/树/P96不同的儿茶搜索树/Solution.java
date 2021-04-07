package pratice.leetcode.树.P96不同的儿茶搜索树;

/**
 * @author by catface
 * @date 2021/3/26 9:18 下午
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int result = solution.numTrees(3);
        System.out.println(result);
    }

    public int numTrees(int n) {
        int[] rangeTreeNum = new int[n];
        for (int i = 0; i < n; i++) {
            rangeTreeNum[i] = -1;
        }
        return process(1, n, rangeTreeNum);
    }

    public int process(int start, int end, int[] rangeTreeNum) {
        if (start > end) {
            return 1;
        }
        int range = end - start;
        if (rangeTreeNum[range] != -1) {
            return rangeTreeNum[range];
        }
        int total = 0;
        for (int i = start; i <= end; i++) {
            int leftTrees = process(start, i - 1, rangeTreeNum);
            int rightTrees = process(i + 1, end, rangeTreeNum);
            total += leftTrees * rightTrees;
        }
        rangeTreeNum[range] = total;
        return total;
    }
}
