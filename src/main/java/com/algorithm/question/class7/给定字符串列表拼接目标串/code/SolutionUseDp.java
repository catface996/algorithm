package com.algorithm.question.class7.给定字符串列表拼接目标串.code;

/**
 * @author by catface
 * @date 2021/6/8 10:20 上午
 */

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.algorithm.question.class7.给定字符串列表拼接目标串.code.Code05_WorldBreak.RandomSample;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/6/7 6:02 下午
 */
public class SolutionUseDp {

    public int contactWays(String s, String[] words) {
        Set<String> wordSet = new HashSet<>();
        Collections.addAll(wordSet, words);
        return process(s, wordSet);
    }

    /**
     * 有多少种方法拼接处从index位置到结尾的字符串
     *
     * @param s       目标字符串
     * @param wordSet 单词集合
     * @return 拼接的方法数
     */
    public int process(String s, Set<String> wordSet) {
        int[] dp = new int[s.length() + 1];
        dp[s.length()] = 1;
        int ways;
        for (int index = s.length() - 1; index >= 0; index--) {
            ways = 0;
            for (int end = s.length() - 1; end >= index; end--) {
                String prefix = s.substring(index, end + 1);
                if (wordSet.contains(prefix)) {
                    ways += dp[end + 1];
                }
            }
            dp[index] = ways;
        }
        return dp[0];
    }

    public static class TestClass {
        @Test
        public void test1() {
            char[] candidates = {'a', 'b'};
            int num = 20;
            int len = 4;
            int joint = 5;
            int testTimes = 30000;
            boolean testResult = true;
            SolutionUseDp solution = new SolutionUseDp();
            for (int i = 0; i < testTimes; i++) {
                RandomSample sample = Code05_WorldBreak.generateRandomSample(candidates, num, len, joint);
                int ans1 = Code05_WorldBreak.ways1(sample.str, sample.arr);
                int ans2 = solution.contactWays(sample.str, sample.arr);
                if (ans1 != ans2) {
                    testResult = false;
                }
            }
            System.out.println(testTimes + "次随机测试是否通过：" + testResult);
        }
    }

}
