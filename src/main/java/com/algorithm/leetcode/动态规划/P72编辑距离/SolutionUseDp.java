package com.algorithm.leetcode.动态规划.P72编辑距离;

//给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
//
// 你可以对一个单词进行如下三种操作：
//
//
// 插入一个字符
// 删除一个字符
// 替换一个字符
//
//
//
//
// 示例 1：
//
//
//输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
//
//
// 示例 2：
//
//
//输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
//
//
//
//
// 提示：
//
//
// 0 <= word1.length, word2.length <= 500
// word1 和 word2 由小写英文字母组成
//
// Related Topics 字符串 动态规划
// 👍 1637 👎 0

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
@Slf4j
public class SolutionUseDp {


    // 10:01 下午	info
    //					解答成功:
    //					执行耗时:7 ms,击败了41.99% 的Java用户
    //					内存消耗:38.5 MB,击败了53.87% 的Java用户

    public int minDistance(String word1, String word2) {
        return process(word1, word2);
    }

    private int process(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int l1 = 0; l1 <= word1.length(); l1++) {
            // 目标串为0时,只能通过删除l1数量的字符来构成目标串
            dp[l1][0] = l1;
        }
        for (int l2 = 0; l2 <= word2.length(); l2++) {
            // 源串长度为0,只能通过添加字符来构成l2长度的目标串
            dp[0][l2] = l2;
        }
        for (int l1 = 1; l1 <= word1.length(); l1++) {
            for (int l2 = 1; l2 <= word2.length(); l2++) {
                int minCost = Integer.MAX_VALUE;
                // 样本对应模型,以末尾的字符来讨论,有以下四种情况
                // 1.通过在源串末尾添加字符来构成目标串,则需要源串的l1构成l2-1
                minCost = Math.min(minCost, dp[l1][l2 - 1] + 1);
                // 2.通过删除源串末尾的字符来构成目标串,则需要l1-1构成目标串l2长度
                minCost = Math.min(minCost, dp[l1 - 1][l2] + 1);
                if (word1.charAt(l1 - 1) == word2.charAt(l2 - 1)) {
                    // 3.如果末尾字符相同,通过保留末尾构成目标字符串,则需要l1-1构成l2-1
                    minCost = Math.min(minCost, dp[l1 - 1][l2 - 1]);
                } else {
                    // 4.如果末尾字符不相同,通过替换末尾字符来构成目标字符串
                    minCost = Math.min(minCost, dp[l1 - 1][l2 - 1] + 1);
                }
                dp[l1][l2] = minCost;
            }
        }
        return dp[word1.length()][word2.length()];
    }

    public static class TestClass {
        // 示例 1：
        //
        //
        //输入：word1 = "horse", word2 = "ros"
        //输出：3
        //解释：
        //horse -> rorse (将 'h' 替换为 'r')
        //rorse -> rose (删除 'r')
        //rose -> ros (删除 'e')
        //
        @Test
        public void test1() {
            String word1 = "horse";
            String word2 = "ros";
            SolutionUseDp solution = new SolutionUseDp();
            int minCost = solution.minDistance(word1, word2);
            log.info("编辑距离:{}", minCost);
        }

        // 示例 2：
        //
        //
        //输入：word1 = "intention", word2 = "execution"
        //输出：5
        //解释：
        //intention -> inention (删除 't')
        //inention -> enention (将 'i' 替换为 'e')
        //enention -> exention (将 'n' 替换为 'x')
        //exention -> exection (将 'n' 替换为 'c')
        //exection -> execution (插入 'u')
        @Test
        public void test2() {
            String word1 = "intention";
            String word2 = "execution";
            SolutionUseDp solution = new SolutionUseDp();
            int minCost = solution.minDistance(word1, word2);
            log.info("编辑距离:{}", minCost);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

