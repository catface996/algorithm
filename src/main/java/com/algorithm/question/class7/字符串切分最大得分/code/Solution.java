package com.algorithm.question.class7.字符串切分最大得分.code;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/6/8 2:14 下午
 */
@Slf4j
public class Solution {

    /**
     * 将字符串拆分成k个部分,每个部分必须出现在parts中,parts中的字符串对应的得分在record中
     *
     * @param s      待拆分的字符串
     * @param k      要拆成的部分
     * @param parts  符合要求的拆分部分
     * @param record 符合要求的部分的得分
     * @return 最大得分
     */
    public int maxScoreSplitString(String s, int k, String[] parts, int[] record) {
        Map<String, Integer> partScoreMap = new HashMap<>();
        for (int i = 0; i < parts.length; i++) {
            partScoreMap.put(parts[i], record[i]);
        }
        return process(s, partScoreMap, 0, k);
    }

    /**
     * 将指定的字符串,拆分成k部分,最高得分
     *
     * @param s            指定字符串
     * @param partScoreMap 拆分部分对应的得分
     * @param index        字符串的起始位置
     * @param k            拆分成的部分数
     * @return 最大得分
     */
    public int process(String s, Map<String, Integer> partScoreMap, int index, int k) {
        if (index == s.length()) {
            // 到结束位置时,正好拆分了k个部分
            if (k == 0) {
                return 0;
            }
            // 小于或超过k个拆分,不达标
            return -1;
        }
        int maxScore = 0;
        for (int end = index; end < s.length(); end++) {
            String prefix = s.substring(index, end + 1);
            if (partScoreMap.containsKey(prefix)) {
                int leftMaxScore = process(s, partScoreMap, end + 1, k - 1);
                if (leftMaxScore != -1) {
                    maxScore = Math.max(maxScore, partScoreMap.get(prefix) + leftMaxScore);
                }
            }
        }
        return maxScore;
    }

    public static class TestClass {
        @Test
        public void test1() {
            String str = "abcdefg";
            int K = 3;
            String[] parts = {"abc", "def", "g", "ab", "cd", "efg", "defg"};
            int[] record = {1, 1, 1, 3, 3, 3, 2};
            Solution solution = new Solution();
            int ans1 = Code06_SplitStringMaxValue.maxRecord1(str, K, parts, record);
            int ans2 = solution.maxScoreSplitString(str, K, parts, record);
            log.info("ans1:{},ans2:{}", ans1, ans2);
        }
    }

}
