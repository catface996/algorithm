package pratice.leetcode.排序.剑指Offer45把数组排成最小的数;

//输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
//
//
//
// 示例 1:
//
// 输入: [10,2]
//输出: "102"
//
// 示例 2:
//
// 输入: [3,30,34,5,9]
//输出: "3033459"
//
//
//
// 提示:
//
//
// 0 < nums.length <= 100
//
//
// 说明:
//
//
// 输出结果可能非常大，所以你需要返回一个字符串而不是整数
// 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
//
// Related Topics 排序
// 👍 192 👎 0

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public String minNumber(int[] nums) {
        String[] strArr = sortNumByStr(nums);
        // 字符串拼接若 x+y>y+x 则 x>y  比如  x = 30,y=3  30+3 -> 303  3+30->330 30 在 3 之前
        // 反之 x+y < y+x 则 x<y
        Arrays.sort(strArr, (o1, o2) -> (o1 + o2).compareTo(o2 + o1));
        StringBuilder sb = new StringBuilder();
        for (String str : strArr) {
            sb.append(str);
        }
        return sb.toString();
    }

    public String[] sortNumByStr(int[] nums) {
        String[] strArr = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strArr[i] = String.valueOf(nums[i]);
        }
        return strArr;
    }

    public static class Test1 {
        //输入: [10,2]
        //输出: "102"
        public static void main(String[] args) {
            int[] nums = new int[] {10, 2};
            Solution solution = new Solution();
            String minValueStr = solution.minNumber(nums);
            System.out.println(minValueStr);
        }
    }

    // 输入: [3,30,34,5,9]
    //输出: "3033459"
    public static class Test2 {
        public static void main(String[] args) {
            int[] nums = new int[] {3, 30, 34, 5, 9};
            Solution solution = new Solution();
            String minValueStr = solution.minNumber(nums);
            System.out.println(minValueStr);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

