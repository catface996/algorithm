package pratice.leetcode.排序.P75颜色分类;

//给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
//
// 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
//
//
//
//
//
//
// 示例 1：
//
//
//输入：nums = [2,0,2,1,1,0]
//输出：[0,0,1,1,2,2]
//
//
// 示例 2：
//
//
//输入：nums = [2,0,1]
//输出：[0,1,2]
//
//
// 示例 3：
//
//
//输入：nums = [0]
//输出：[0]
//
//
// 示例 4：
//
//
//输入：nums = [1]
//输出：[1]
//
//
//
//
// 提示：
//
//
// n == nums.length
// 1 <= n <= 300
// nums[i] 为 0、1 或 2
//
//
//
//
// 进阶：
//
//
// 你可以不使用代码库中的排序函数来解决这道题吗？
// 你能想出一个仅使用常数空间的一趟扫描算法吗？
//
// Related Topics 排序 数组 双指针
// 👍 838 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
public class Solution {

    /*
    荷兰国旗问题
    https://www.jianshu.com/p/356604b8903f
    11:25 上午	info
					解答成功:
					执行耗时:0 ms,击败了100.00% 的Java用户
					内存消耗:37.1 MB,击败了31.61% 的Java用户
     */
    public void sortColors(int[] nums) {
        partition(nums, 0, nums.length - 1, 1);
    }

    private int[] partition(int[] arr, int L, int R, int p) {
        int less = L - 1;
        int more = R + 1;
        while (L < more) {
            if (arr[L] < p) {
                swap(arr, ++less, L++);
            } else if (arr[L] > p) {
                swap(arr, --more, L);
            } else {
                L++;
            }
        }
        return new int[] {less + 1, more - 1};
    }

    private void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static class Test1 {
        //输入：nums = [2,0,2,1,1,0]
        //输出：[0,0,1,1,2,2]
        public static void main(String[] args) {
            int[] values = new int[] {2, 0, 2, 1, 1, 0};
            Solution solution = new Solution();
            solution.sortColors(values);
            System.out.println("");
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

