package leetcode.线段树.P307区域和检索_数组可修改;

//给你一个数组 nums ，请你完成两类查询，其中一类查询要求更新数组下标对应的值，另一类查询要求返回数组中某个范围内元素的总和。
//
// 实现 NumArray 类：
//
//
//
//
// NumArray(int[] nums) 用整数数组 nums 初始化对象
// void update(int index, int val) 将 nums[index] 的值更新为 val
// int sumRange(int left, int right) 返回子数组 nums[left, right] 的总和（即，nums[left] +
//nums[left + 1], ..., nums[right]）
//
//
//
//
// 示例：
//
//
//输入：
//["NumArray", "sumRange", "update", "sumRange"]
//[[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
//输出：
//[null, 9, null, 8]
//
//解释：
//NumArray numArray = new NumArray([1, 3, 5]);
//numArray.sumRange(0, 2); // 返回 9 ，sum([1,3,5]) = 9
//numArray.update(1, 2);   // nums = [1,2,5]
//numArray.sumRange(0, 2); // 返回 8 ，sum([1,2,5]) = 8
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 3 * 104
// -100 <= nums[i] <= 100
// 0 <= index < nums.length
// -100 <= val <= 100
// 0 <= left <= right < nums.length
// 最多调用 3 * 104 次 update 和 sumRange 方法
//
//
//
// Related Topics 树状数组 线段树
// 👍 258 👎 0

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
public class NumArray2 {

    // 5:55 下午	info
    //				解答成功:
    //				执行耗时:87 ms,击败了87.25% 的Java用户
    //				内存消耗:69 MB,击败了51.20% 的Java用户

    IndexTree indexTree;

    public NumArray2(int[] nums) {
        indexTree = new IndexTree(nums.length);
        for (int i = 0; i < nums.length; i++) {
            indexTree.add(i + 1, nums[i]);
        }
    }

    public void update(int index, int val) {
        index++;
        int sumIndex = indexTree.sum(index);
        int ansIndex1 = indexTree.sum(index - 1);
        int originValue = sumIndex - ansIndex1;
        int range = val - originValue;
        indexTree.add(index, range);
    }

    /**
     * 从left到right,包含left,包含right
     *
     * @param left  左侧下标
     * @param right 右侧下标
     * @return [left, right] 区间上的累加和
     */
    public int sumRange(int left, int right) {
        // left+1后才是indexTree的弃用0位置下标,此时做差值,不包含left位置,所以left+1后要-1
        int leftSum = indexTree.sum(left);
        int rightSum = indexTree.sum(right + 1);
        return rightSum - leftSum;
    }

    public static class IndexTree {

        private final int n;
        private final int[] tree;

        public IndexTree(int size) {
            n = size;
            tree = new int[n + 1];
        }

        public void add(int index, int value) {
            // 需要计算index位置被tree中的那些位置累加,首先一定被tree[index]中累加,假如index位置为1,那么1位置的值将被,2,4,8,16,...2^x<=n位置的记录累加和
            // 如果index=5,则被5,6,8,...,
            // 抽象成 对应的二进制为0000 0101, 0000 0110, 0000 1000,...
            // 通项公式为 index; index+ = index&-index, 即 index+index只保留最右侧的1
            while (index <= n) {
                tree[index] += value;
                index += index & -index;
            }
        }

        /**
         * 查询1到index范围的累加和
         *
         * @param index 累加和下标,弃用0位置,从1开始
         * @return 范围累加和
         */
        public int sum(int index) {
            // 假设index=8,8位置负责累计了1~8位置的累加和,直接返回
            // 假设index=7,7位置只累计了7位置的值,6位置累计了5~6位置的累加和,4位置累计了1~4位置的累加和,所以只需要返回tree[7]+tree[6]+tree[4]
            // 8转成二进制 0000 1000,
            // 7,6,4 分别转成二进制 0000 0111, 0000 0110, 0000 0100
            // 通项公式为 index,index-=index&-index, 即 index - index只保留最右侧i
            int ans = 0;
            while (index > 0) {
                ans += tree[index];
                index -= index & -index;
            }
            return ans;
        }

    }

    public static class TestClass {
        // 示例：
        //
        //
        //输入：
        //["NumArray", "sumRange", "update", "sumRange"]
        //[[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
        //输出：
        //[null, 9, null, 8]
        @Test
        public void test1() {
            NumArray2 numArray = new NumArray2(new int[] {1, 3, 5});
            int ans = numArray.sumRange(0, 2);
            System.out.println(ans);
            numArray.update(1, 2);
            ans = numArray.sumRange(0, 2);
            System.out.println(ans);
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)

