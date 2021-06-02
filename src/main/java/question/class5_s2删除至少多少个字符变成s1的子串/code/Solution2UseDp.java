package question.class5_s2删除至少多少个字符变成s1的子串.code;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/6/2 3:32 下午
 */
@Slf4j
public class Solution2UseDp {

    public int minCostToSubSeq(String s1, String s2) {
        // 生成s1的所有子串,长度小于等于s2,因为无法通过对s2的删除,构成成比s2的长度还要长的字符串
        int minCost = Integer.MAX_VALUE;
        for (int length = s2.length(); length >= 0; length--) {
            for (int start = 0, end = start + length; end <= s1.length(); start++, end++) {
                String s1Sub = s1.substring(start, end);
                minCost = Math.min(minCost, minDeleteCost(s1Sub, s2));
            }
            if (minCost != Integer.MAX_VALUE) {
                return minCost;
            }
        }
        return minCost;
    }

    /**
     * s2字符串,通过删除构成字符串s1的最小代价
     *
     * @param s1 目标字符串
     * @param s2 源字符串
     * @return 最小代价
     */
    private int minDeleteCost(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) {
            return s2.length();
        }
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int[] ints : dp) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }
        for (int l2 = 0; l2 < s2.length(); l2++) {
            dp[0][l2] = l2;
        }
        for (int l1 = 1; l1 <= s1.length(); l1++) {
            for (int l2 = 1; l2 <= s2.length(); l2++) {
                int minCost = Integer.MAX_VALUE;
                // 讨论s2的结尾位置,只有两种情况
                // 1.保留
                if (s1.charAt(l1 - 1) == s2.charAt(l2 - 1)) {
                    minCost = Math.min(minCost, dp[l1 - 1][l2 - 1]);
                }
                // 2.删除
                int preCost = dp[l1][l2 - 1];
                if (preCost != Integer.MAX_VALUE) {
                    minCost = Math.min(minCost, preCost + 1);
                }
                dp[l1][l2] = minCost;
            }
        }
        return dp[s1.length()][s2.length()];
    }

    public static class TestClass {

        @Test
        public void test0() {
            String s1 = "abc", s2 = "axbc";
            Solution2UseDp solution2 = new Solution2UseDp();
            int minCost = solution2.minDeleteCost(s1, s2);
            log.info("minCost:{}", minCost);
        }

        @Test
        public void test1() {
            String s1 = "abcde", s2 = "axbc";
            Solution2UseDp solution2 = new Solution2UseDp();
            int minCost = solution2.minCostToSubSeq(s1, s2);
            log.info("minCost:{}", minCost);
        }
    }

}
