package question.class5_s2删除至少多少个字符变成s1的子串.code;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/6/2 3:32 下午
 */
@Slf4j
public class Solution2 {

    public int minCostToSubSeq(String s1, String s2) {
        if (s1.length() == 0 || s2.length() == 0) {
            return s2.length();
        }
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
        return process(s1, s1.length(), s2, s2.length());
    }

    private int process(String s1, int l1, String s2, int l2) {
        int minCost = Integer.MAX_VALUE;
        if (l1 == 0) {
            return l2;
        }
        if (l2 < l1) {
            // 由于只能通过删除来构成目标串,如果源串长度已经小于目标串,一定无法构成
            return minCost;
        }
        // 讨论s2的结尾位置,只有两种情况
        // 1.保留
        if (s1.charAt(l1 - 1) == s2.charAt(l2 - 1)) {
            minCost = Math.min(minCost, process(s1, l1 - 1, s2, l2 - 1));
        }
        // 2.删除
        int preCost = process(s1, l1, s2, l2 - 1);
        if (preCost != Integer.MAX_VALUE) {
            minCost = Math.min(minCost, preCost + 1);
        }
        return minCost;
    }

    public static class TestClass {

        @Test
        public void test0() {
            String s1 = "abc", s2 = "axbc";
            Solution2 solution2 = new Solution2();
            int minCost = solution2.minDeleteCost(s1, s2);
            log.info("minCost:{}", minCost);
        }

        @Test
        public void test1() {
            String s1 = "abcde", s2 = "axbc";
            Solution2 solution2 = new Solution2();
            int minCost = solution2.minCostToSubSeq(s1, s2);
            log.info("minCost:{}", minCost);
        }
    }

}
