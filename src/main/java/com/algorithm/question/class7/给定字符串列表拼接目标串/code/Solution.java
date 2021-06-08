package com.algorithm.question.class7.给定字符串列表拼接目标串.code;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.algorithm.question.class7.给定字符串列表拼接目标串.code.Code05_WorldBreak.RandomSample;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/6/7 6:02 下午
 */
public class Solution {

    public int contactWays(String s, String[] words) {
        Set<String> wordSet = new HashSet<>();
        Collections.addAll(wordSet, words);
        return process(s, 0, wordSet);
    }

    /**
     * 有多少种方法拼接处从index位置到结尾的字符串
     *
     * @param s     目标字符串
     * @param index 开始位置
     * @return 拼接的方法数
     */
    public int process(String s, int index, Set<String> wordSet) {
        if (index == s.length()) {
            return 1;
        }
        int ways = 0;
        for (int end = index; end < s.length(); end++) {
            String prefix = s.substring(index, end + 1);
            if (wordSet.contains(prefix)) {
                ways += process(s, end + 1, wordSet);
            }
        }
        return ways;
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
            Solution solution = new Solution();
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
