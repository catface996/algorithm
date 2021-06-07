package com.algorithm.course.kmp.impl;

import com.algorithm.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/8 10:49 上午
 */
@Slf4j
public class Solution5 {

    private int kmpIndexOf(String str1, String str2) {
        if (str1 == null || str1.length() < 1 || str2 == null || str2.length() < 1 || str1.length() < str2.length()) {
            return 0;
        }
        int[] next = buildNext(str2);
        int i = 0;
        int j = 0;
        while (i < str1.length() && j < str2.length()) {
            if (str1.charAt(i) == str2.charAt(j)) {
                i++;
                j++;
            } else if (next[j] != -1) {
                j = next[j];
            } else {
                i++;
            }
        }
        return j == str2.length() ? i - j : -1;
    }

    public int[] buildNext(String s) {
        if (s.length() == 1) {
            return new int[] {-1};
        }
        int[] next = new int[s.length()];
        next[0] = -1;
        int i = 2, cn = 0;
        while (i < s.length()) {
            if (s.charAt(i - 1) == s.charAt(cn)) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                i++;
            }
        }
        return next;
    }

    public static class TestClass {
        @Test
        public void testBuildNext() {
            String s = "abcabck";
            Solution5 solution5 = new Solution5();
            int[] next = solution5.buildNext(s);
            log.info("next:{}", next);
        }

        @Test
        public void testKmpIndexOf() {
            String str1 = "abcabck";
            String str2 = "cab";
            Solution5 solution5 = new Solution5();
            int index = solution5.kmpIndexOf(str1, str2);
            log.info("index:{}", index);
        }

        @Test
        public void testForce() {
            for (int time = 0; time < 10000; time++) {
                Solution5 solution5 = new Solution5();
                String str1 = StringUtil.randomStrOfNumber(30);
                String str2 = StringUtil.randomStrOfNumber((int)(Math.random() * 8));
                int ans1 = solution5.kmpIndexOf(str1, str2);
                int ans2 = str1.indexOf(str2);
                if (ans1 != ans2) {
                    log.info("str1:{},str2:{},ans1:{},ans2:{}", str1, str2, ans1, ans2);
                }
                assert ans1 == ans2;
            }
        }
    }
}
