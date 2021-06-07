package com.algorithm.course.kmp.impl;

import com.algorithm.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/6 3:34 下午
 */
@Slf4j
public class Solution4 {

    public int kmpIndexOf(String str1, String str2) {
        if (str1 == null || str1.length() < 1 || str2 == null || str2.length() < 1 || str1.length() < str2.length()) {
            return -1;
        }
        // i位置的字符是否匹配
        int[] next = buildNextForce(str2);
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

    public int[] buildNextForce(String s) {
        int[] next = new int[s.length()];
        next[0] = -1;
        for (int i = 2; i < s.length(); i++) {
            String subStr = s.substring(0, i);
            for (int length = 1; length < subStr.length(); length++) {
                String prefix = subStr.substring(0, length);
                String suffix = subStr.substring(subStr.length() - length);
                if (prefix.equals(suffix)) {
                    next[i] = length;
                }
            }
        }
        return next;
    }

    public int[] buildNext(String s) {
        if (s.length() == 1) {
            return new int[] {-1};
        }
        int[] next = new int[s.length()];
        next[0] = -1;
        int i = 2;
        int cur = 0;
        while (i < s.length()) {
            if (s.charAt(i - 1) == s.charAt(cur)) {
                next[i++] = ++cur;
            } else if (cur > 0) {
                cur = next[cur];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static class TestClass {
        @Test
        public void testNext1() {
            String s = "abcabck";
            Solution3 solution3 = new Solution3();
            int[] next1 = solution3.buildNext(s);
            log.info("next1:{}", next1);
            Solution4 solution4 = new Solution4();
            int[] next2 = solution4.buildNextForce(s);
            log.info("next2:{}", next2);
        }

        @Test
        public void testKmpIndexOf() {
            String str1 = "abcabck";
            String str2 = "abck";
            Solution4 solution4 = new Solution4();
            int index1 = solution4.kmpIndexOf(str1, str2);
            int index2 = str1.indexOf(str2);
            log.info("str1:{},str2:{},index1:{},index2:{}", str1, str2, index1, index2);
        }

        @Test
        public void testKmpIndexOf2() {
            String str1 = "abcabck";
            String str2 = "k";
            Solution4 solution4 = new Solution4();
            int index1 = solution4.kmpIndexOf(str1, str2);
            int index2 = str1.indexOf(str2);
            log.info("str1:{},str2:{},index1:{},index2:{}", str1, str2, index1, index2);
        }

        @Test
        public void testNext() {
            for (int time = 0; time < 1000000; time++) {
                String s = StringUtil.randomStrOfNumber(20);
                Solution4 solution4 = new Solution4();
                int[] next1 = solution4.buildNext(s);
                int[] next2 = solution4.buildNextForce(s);
                for (int i = 0; i < next1.length; i++) {
                    assert next1[i] == next2[i];
                }
            }
            System.out.println("Good,you success!");
        }
    }
}
