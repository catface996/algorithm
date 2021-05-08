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
public class NumArray {

    // 10:47 上午	info
    //					解答成功:
    //					执行耗时:121 ms,击败了59.78% 的Java用户
    //					内存消耗:65.7 MB,击败了98.68% 的Java用户

    private final int maxN;
    private final int[] arr;
    private final int[] sum;
    private final int[] change;
    private final boolean[] update;

    public NumArray(int[] nums) {
        maxN = nums.length + 1;
        int maxN4 = maxN << 2;
        arr = new int[maxN];
        sum = new int[maxN4];
        change = new int[maxN4];
        update = new boolean[maxN4];
        System.arraycopy(nums, 0, arr, 1, nums.length);
        build(1, maxN - 1, 1);
    }

    public void update(int index, int val) {
        update(index + 1, index + 1, val, 1, maxN - 1, 1);
    }

    public int sumRange(int left, int right) {
        return query(left + 1, right + 1, 1, maxN - 1, 1);
    }

    /**
     * 更新指定区间的数值
     *
     * @param taskL     更新任务指定区间的左侧下标
     * @param taskR     跟新任务指定区间的右侧下标
     * @param taskValue 更新任务指定的待更新值
     * @param rtL       rt代表的区间左侧下标
     * @param rtR       rt代表的区间右侧下标
     * @param rt        代表当前区间的下标
     */
    private void update(int taskL, int taskR, int taskValue, int rtL, int rtR, int rt) {
        if (taskL <= rtL && rtR <= taskR) {
            // 任务区间完全覆盖了当前区间
            sum[rt] = taskValue * (rtR - rtL + 1);
            change[rt] = taskValue;
            update[rt] = true;
            return;
        }
        // 当前区间没有被任务区间完全覆盖
        int mid = rtL + ((rtR - rtL) >> 1);
        pushDown(rt, mid - rtL + 1, rtR - mid);
        if (taskL <= mid) {
            // 任务区间有覆盖左侧区间,对左侧区间进行递归
            update(taskL, taskR, taskValue, rtL, mid, rt << 1);
        }
        if (mid < taskR) {
            // 任务区间有覆盖右侧区间,对右侧区间进行递归
            update(taskL, taskR, taskValue, mid + 1, rtR, rt << 1 | 1);
        }
        // 左右区间至少有一个发生变化,需要重新计算累加和
        pushUp(rt);
    }

    /**
     * 查询指定区间的累加和
     *
     * @param taskL 查询任务指定区间的左侧下标
     * @param taskR 查询任务指定区间的右侧下标
     * @param rtL   rt代表的区间的左侧下标
     * @param rtR   rt代表的区间的右侧下标
     * @param rt    当前区间的下标
     * @return 当前区间的累加和
     */
    private int query(int taskL, int taskR, int rtL, int rtR, int rt) {
        if (taskL <= rtL && rtR <= taskR) {
            // 任务区间完全覆盖了当前区间,直接返回
            return sum[rt];
        }

        // 任务区间未完全覆盖当前区间,需要分左右两侧区间分别统计累加和,然后累加返回
        int mid = rtL + ((rtR - rtL) >> 1);
        pushDown(rt, mid - rtL + 1, rtR - mid);
        int ans = 0;
        if (taskL <= mid) {
            // 左侧区间有被任务区间覆盖,对左侧区间进行递归查询
            ans += query(taskL, taskR, rtL, mid, rt << 1);
        }
        if (mid < taskR) {
            ans += query(taskL, taskR, mid + 1, rtR, rt << 1 | 1);
        }
        return ans;
    }

    private void build(int rtL, int rtR, int rt) {
        if (rtL == rtR) {
            sum[rt] = arr[rtL];
            return;
        }
        int mid = rtL + ((rtR - rtL) >> 1);
        build(rtL, mid, rt << 1);
        build(mid + 1, rtR, rt << 1 | 1);
        pushUp(rt);
    }

    private void pushUp(int rt) {
        sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
    }

    private void pushDown(int rt, int ln, int rn) {
        if (update[rt]) {
            int left = rt << 1;
            int right = left + 1;
            // 下发至左侧
            sum[left] = change[rt] * ln;
            change[left] = change[rt];
            update[left] = true;
            // 下发至右侧
            sum[right] = change[rt] * rn;
            change[right] = change[rt];
            update[right] = true;
            // 清除rt的update设置
            update[rt] = false;
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
            NumArray numArray = new NumArray(new int[] {1, 3, 5});
            int ans = numArray.sumRange(0, 2);
            System.out.println(ans);
            numArray.update(1, 2);
            ans = numArray.sumRange(0, 2);
            System.out.println(ans);
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)

