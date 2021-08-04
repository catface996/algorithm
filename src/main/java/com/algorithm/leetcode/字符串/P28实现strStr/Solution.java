package com.algorithm.leetcode.字符串.P28实现strStr;

//实现 strStr() 函数。
//
// 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如
//果不存在，则返回 -1 。
//
//
//
// 说明：
//
// 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
//
// 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。
//
//
//
// 示例 1：
//
//
//输入：haystack = "hello", needle = "ll"
//输出：2
//
//
// 示例 2：
//
//
//输入：haystack = "aaaaa", needle = "bba"
//输出：-1
//
//
// 示例 3：
//
//
//输入：haystack = "", needle = ""
//输出：0
//
//
//
//
// 提示：
//
//
// 0 <= haystack.length, needle.length <= 5 * 104
// haystack 和 needle 仅由小写英文字符组成
//
// Related Topics 双指针 字符串 字符串匹配
// 👍 979 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int strStr(String haystack, String needle) {
        if (haystack.equals(needle)){
            return 0;
        }
        return 0;
    }

    private int[] next(String needle){
        int[] next = new int[needle.length()];
        next[0] = -1;
        int cur = 0;
        int i = 1;
        while (i < needle.length()) {
            if (needle.charAt(cur + 1) == needle.charAt(i)) {
                next[i++] = ++cur;
            } else if (next[cur] != -1) {
                cur = next[cur];
            } else {
                i++;
            }
        }
        return next;
    }


}
//leetcode submit region end(Prohibit modification and deletion)

