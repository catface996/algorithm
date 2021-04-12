package course.暴力递归动态规划.最长公共子序列;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/12 6:50 下午
 */
public class Solution {

    //给定两个字符串str1和str2，
    //返回这两个字符串的最长公共子序列长度
    //
    //比如 ： str1 = “a12b3c456d”,str2 = “1ef23ghi4j56k”
    //最长公共子序列是“123456”，所以返回长度6

    public int maxCommonSubSeq(String str1, String str2) {
        if (str1 == null || str1.length() <= 0 || str2 == null || str2.length() <= 0) {
            return 0;
        }
        return process(str1, 0, str2, 0);
    }

    /**
     * 求解最大公共子序列的长度
     *
     * @param str1   字符串1
     * @param start1 开始位置1
     * @param str2   字符串2
     * @param start2 开始位置2
     * @return 最大公共子序列长度
     */
    private int process(String str1, int start1, String str2, int start2) {

        // 两个字符串中的任何一个已遍历结束,另外一个未结束,公共子序列长度为0
        if (start1 == str1.length() && start2 <= str2.length() - 1) {
            return 0;
        }
        if (start1 <= str1.length() && start2 == str2.length() - 1) {
            return 0;
        }

        int maxLength = 0;
        // 第一个字符选不保留前位置,第二个字符串保留当前位置
        maxLength = Math.max(maxLength, process(str1, start1 + 1, str2, start2));
        // 第一个字符串保留当前位置,第二个字符串不保留当前位置
        maxLength = Math.max(maxLength, process(str1, start1, str2, start2 + 1));
        // 第一个字符串和第二个字符串均不保留当前位置
        maxLength = Math.max(maxLength, process(str1, start1 + 1, str2, start2 + 1));
        // 第一个字符串和第二个字符串均保留当前位置,且当前位置字符串相同
        if (str1.charAt(start1) == str2.charAt(start2)) {
            maxLength = Math.max(maxLength, 1 + process(str1, start1 + 1, str2, start2 + 1));
        }
        return maxLength;
    }

    public static class TestClass {
        //比如 ： str1 = “”,str2 = “”
        @Test
        public void test1() {
            String str1 = "";
            String str2 = "";
            Solution solution = new Solution();
            int maxLength = solution.maxCommonSubSeq(str1, str2);
            System.out.println(maxLength);
            assert maxLength == 0;
        }

        //比如 ： str1 = “a12b3c456d”,str2 = “1ef23ghi4j56k”
        //最长公共子序列是“123456”，所以返回长度6
        @Test
        public void test2() {
            String str1 = "a12b3c456d";
            String str2 = "1ef23ghi4j56k";
            Solution solution = new Solution();
            int maxLength = solution.maxCommonSubSeq(str1, str2);
            System.out.println(maxLength);
            assert maxLength == 6;
        }
    }
}
