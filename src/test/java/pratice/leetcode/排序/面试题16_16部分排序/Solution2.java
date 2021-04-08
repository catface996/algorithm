package pratice.leetcode.排序.面试题16_16部分排序;

//给定一个整数数组，编写一个函数，找出索引m和n，只要将索引区间[m,n]的元素排好序，整个数组就是有序的。注意：n-m尽量最小，也就是说，找出符合条件的最短
//序列。函数返回值为[m,n]，若不存在这样的m和n（例如整个数组是有序的），请返回[-1,-1]。
// 示例：
// 输入： [1,2,4,7,10,11,7,12,6,7,16,18,19]
//输出： [3,9]
//
// 提示：
//
// 0 <= len(array) <= 1000000
//
// Related Topics 排序 数组
// 👍 65 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
class Solution2 {

    /*
        解题思路:
        从左往右(从小到大)找到最后一小于最大值的拐点,即为乱序区间的右侧下标
        从右往左(从左到小)找到最后一次大于最小值的拐点,即为乱序区间的左侧下边
     */
    public int[] subSort(int[] array) {
        if (array == null || array.length < 1) {
            return new int[] {-1, -1};
        }
        // 从左往右找右端点,能找到从right到end=array.length这段右侧区间为递增区间
        int max = array[0];
        int right = -1;
        int n = array.length;
        for (int i = 1; i < n; i++) {
            if (array[i] >= max) {
                max = array[i];
            } else {
                // 最后一次出现小于最大值的情况,说明出现了拐点
                right = i;
            }
        }
        if (right == -1) {
            return new int[] {-1, -1};
        }
        // 从右往左找左端点,能找到从start=0到left这段为递增区间,或者是反向从 left到start为递减区间
        int min = array[n - 1];
        int left = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (array[i] <= min) {
                min = array[i];
            } else {
                // 最后一次出现大于最小值,说明出现了拐点
                left = i;
            }
        }
        return new int[] {left, right};
    }

    public static class Test1 {
        // 输入： [1,2,4,7,10,11,7,12,6,7,16,18,19]
        //输出： [3,9]
        public static void main(String[] args) {
            int[] arr = new int[] {1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};
            Solution2 solution = new Solution2();
            int[] ans = solution.subSort(arr);
            System.out.println(ans);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

