package leetcode.并查集.P128最长连续序列;

//给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
//
//
//
// 进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？
//
//
//
// 示例 1：
//
//
//输入：nums = [100,4,200,1,3,2]
//输出：4
//解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
//
// 示例 2：
//
//
//输入：nums = [0,3,7,2,5,8,4,6,0,1]
//输出：9
//
//
//
//
// 提示：
//
//
// 0 <= nums.length <= 104
// -109 <= nums[i] <= 109
//
// Related Topics 并查集 数组
// 👍 761 👎 0

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 2:46 下午	info
    //				解答成功:
    //				执行耗时:4 ms,击败了88.55% 的Java用户
    //				内存消耗:39.1 MB,击败了41.10% 的Java用户
    //

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        UnionFind unionFind = new UnionFind();
        for (int num : nums) {
            unionFind.join(num);
        }
        return unionFind.maxLength;
    }

    public static class UnionFind {

        /**
         * 下标代表数组中的数字,value代表自己的代表
         * <p>
         * 例如: parnt[23]=65 是指,23这个数在65为代表的集合中
         * <p>
         * 下标范围为为-109 到 109,加109,数字从0开始到218,需要分配219个存储空间,加110 数字从 1到 219,弃用0位置和最后位置,需要221个存储空间
         */
        private final Map<Integer, Integer> parent = new HashMap<>();
        private final Map<Integer, Integer> parentSize = new HashMap<>();
        private int maxLength = 0;

        public void join(int num) {
            Integer parentValue = parent.get(num);
            if (parentValue != null) {
                return;
            }

            // 如果自己的父亲为空,检查左右两侧的父亲是否存在,如果都不存在,自己做自己的父亲
            Integer leftParent = parent.get(num - 1);
            Integer rightParent = parent.get(num + 1);

            // [1]
            if (leftParent == null && rightParent == null) {
                // 左右两侧都不存在
                parentValue = num;
                parent.put(num, parentValue);
                int size = parentSize.getOrDefault(parentValue, 0);
                size++;
                parentSize.put(parentValue, size);
                maxLength = Math.max(maxLength, size);
                return;
            }

            // [2]
            if (leftParent != null && rightParent != null) {

                // 左右两侧都存在,选择较大一侧合并
                parentValue = parentSize.get(leftParent) >= parentSize.get(rightParent) ? leftParent : rightParent;
                int count = 0;

                // 向左合并
                if (!parentValue.equals(leftParent)) {
                    int index = num - 1;
                    while (leftParent.equals(parent.get(index))) {
                        parent.put(index, parentValue);
                        count++;
                        index--;
                    }
                }

                // 先右侧合并
                if (!parentValue.equals(rightParent)) {
                    int index = num + 1;
                    while (rightParent.equals(parent.get(index))) {
                        parent.put(index, parentValue);
                        count++;
                        index++;
                    }
                }

                // 中间合并
                parent.put(num, parentValue);
                count++;

                // 累加新集合的数量
                int size = parentSize.getOrDefault(parentValue, 0);
                size += count;
                parentSize.put(parentValue, size);
                maxLength = Math.max(maxLength, size);
                return;
            }

            // [3]
            if (leftParent != null && rightParent == null) {
                // 合并到左侧
                parentValue = leftParent;
                parent.put(num, parentValue);
                int size = parentSize.getOrDefault(parentValue, 0);
                size++;
                parentSize.put(parentValue, size);
                maxLength = Math.max(maxLength, size);
                return;
            }

            if (leftParent == null && rightParent != null) {
                // 合并到右侧
                parentValue = rightParent;
                parent.put(num, parentValue);
                int size = parentSize.getOrDefault(parentValue, 0);
                size++;
                parentSize.put(parentValue, size);
                maxLength = Math.max(maxLength, size);
            }
        }
    }

    public static class TestClass {
        // 示例 1：
        //
        //
        //输入：nums = [100,4,200,1,3,2]
        //输出：4
        //解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
        @Test
        public void test1() {
            int[] nums = {100, 4, 200, 1, 3, 2};
            Solution solution = new Solution();
            int maxSize = solution.longestConsecutive(nums);
            System.out.println(maxSize);
        }

        //
        // 示例 2：
        //
        //
        //输入：nums = [0,3,7,2,5,8,4,6,0,1]
        //输出：9
        @Test
        public void test2() {
            int[] nums = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
            Solution solution = new Solution();
            int maxSize = solution.longestConsecutive(nums);
            System.out.println(maxSize);
        }

        //10:33 上午	info
        //解答失败:
        //测试用例:[9,1,4,7,3,-1,0,5,8,-1,6]
        //期望结果:7
        @Test
        public void test3() {
            int[] nums = {9, 1, 4, 7, 3, -1, 0, 5, 8, -1, 6};
            Solution solution = new Solution();
            int maxSize = solution.longestConsecutive(nums);
            System.out.println(maxSize);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

