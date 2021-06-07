package com.algorithm.leetcode.动态规划.P354俄罗斯套娃信封问题;

//给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
//
// 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
//
// 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
//
// 注意：不允许旋转信封。
//
//
// 示例 1：
//
//
//输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
//输出：3
//解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
//
// 示例 2：
//
//
//输入：envelopes = [[1,1],[1,1],[1,1]]
//输出：1
//
//
//
//
// 提示：
//
//
// 1 <= envelopes.length <= 5000
// envelopes[i].length == 2
// 1 <= wi, hi <= 104
//
// Related Topics 二分查找 动态规划
// 👍 532 👎 0

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
@Slf4j
class Solution {

    // 11:36 上午	info
    //					解答成功:
    //					执行耗时:10 ms,击败了99.47% 的Java用户
    //					内存消耗:39.3 MB,击败了67.14% 的Java用户

    public int maxEnvelopes(int[][] envelopes) {
        // 对信封按宽度从小到大排序
        quickSort(envelopes, 0, envelopes.length - 1);
        // end[i]=x的含义,长度为i的所有子序列中,结尾值最小是x
        int[] end = new int[envelopes.length + 1];
        // 设置默认值,默认长度为1的子序列的结尾最小值是第一个信封的高度
        end[1] = envelopes[0][1];
        int length = 1;
        int i = 1;
        while (i < envelopes.length) {
            // 有没有可能推高子序列最大长度??如果有,长度++,记录高度
            if (envelopes[i][1] > end[length]) {
                end[++length] = envelopes[i][1];
            } else {
                int l = 1;
                int r = length;
                int mid;
                int pos = -1;
                while (l <= r) {
                    mid = (l + r) / 2;
                    if (envelopes[i][1] > end[mid]) {
                        l = mid + 1;
                        pos = mid;
                    }
                    if (envelopes[i][1] <= end[mid]) {
                        r = mid - 1;
                    }
                }
                if (pos != -1) {
                    // 发现小于当前高度的信封,计算子序列长度
                    //if (pos+1>length){
                    //    end[++length] = i;
                    //} 这种情况不会在这个分支发生
                    end[pos + 1] = Math.min(end[pos + 1], envelopes[i][1]);
                } else {
                    // 未找到,说明当前位置的最长长度为1,更新长度为1的最小值
                    end[1] = Math.min(end[1], envelopes[i][1]);
                }
            }
            i++;
        }
        return length;
    }

    public void quickSort(int[][] envelopes, int start, int end) {
        if (start >= end) {
            return;
        }
        // 采用荷兰国旗问题的分区方式
        int less = start - 1;
        int more = end + 1;
        int[] pivot = envelopes[(start + end) / 2];
        int cur = start;
        while (cur < more) {
            if (compare(envelopes[cur], pivot) < 0) {
                swap(envelopes, ++less, cur++);
                continue;
            }
            if (compare(envelopes[cur], pivot) == 0) {
                cur++;
                continue;
            }
            if (compare(envelopes[cur], pivot) > 0) {
                swap(envelopes, cur, --more);
            }
        }
        quickSort(envelopes, start, less);
        quickSort(envelopes, more, end);
    }

    public int compare(int[] a, int[] b) {
        if (a[0] == b[0] && a[1] == b[1]) {
            return 0;
        }
        // 保证宽度相同时,高度大的在前,在宽度相同的时候,后续一定装不进前面的
        if (a[0] < b[0] || a[0] == b[0] && a[1] > b[1]) {
            return -1;
        }
        return 1;
    }

    private void swap(int[][] envelopes, int i, int j) {
        if (i == j) {
            return;
        }
        int[] temp = envelopes[i];
        envelopes[i] = envelopes[j];
        envelopes[j] = temp;
    }

    public static class TestClass {

        @Test
        public void testQuickSort() {
            int[][] envelopes = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
            Solution solution = new Solution();
            solution.quickSort(envelopes, 0, envelopes.length - 1);
            log.info("{}envelopes:{}", "", envelopes);
        }

        //
        // 示例 1：
        //
        //
        //输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
        //输出：3
        //解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
        @Test
        public void test1() {
            int[][] envelopes = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
            Solution solution = new Solution();
            int ans = solution.maxEnvelopes(envelopes);
            log.info("ans:{},envelopes:{}", ans, envelopes);
        }

        // 示例 2：
        //
        //
        //输入：envelopes = [[1,1],[1,1],[1,1]]
        //输出：1
        @Test
        public void test2() {
            int[][] envelopes = {{1, 1}, {1, 1}, {1, 1}};
            Solution solution = new Solution();
            int ans = solution.maxEnvelopes(envelopes);
            log.info("ans:{},envelopes:{}", ans, envelopes);
        }

        // 示例 2：
        //
        //
        //输入：envelopes = [[1,1],[1,1],[1,1]]
        //输出：1
        @Test
        public void test22() {
            int[][] envelopes = {{3, 3}, {3, 2}, {4, 3}};
            Solution solution = new Solution();
            int ans = solution.maxEnvelopes(envelopes);
            log.info("ans:{},envelopes:{}", ans, envelopes);
        }

        // 10:57 上午	info
        //					解答失败:
        //					测试用例:[[4,5],[4,6],[6,7],[2,3],[1,1]]
        //					测试结果:1
        //					期望结果:4
        @Test
        public void test3() {
            int[][] envelopes = {{4, 5}, {4, 6}, {6, 7}, {2, 3}, {1, 1}};
            Solution solution = new Solution();
            int ans = solution.maxEnvelopes(envelopes);
            log.info("ans:{},envelopes:{}", ans, envelopes);
        }

        // 11:23 上午	info
        //					解答失败:
        //					测试用例:[[1,3],[3,5],[6,7],[6,8],[8,4],[9,5]]
        //					测试结果:4
        //					期望结果:3
        @Test
        public void test4() {
            int[][] envelopes = {{1, 3}, {3, 5}, {6, 7}, {6, 8}, {8, 4}, {9, 5}};
            Solution solution = new Solution();
            int ans = solution.maxEnvelopes(envelopes);
            log.info("ans:{},envelopes:{}", ans, envelopes);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)


