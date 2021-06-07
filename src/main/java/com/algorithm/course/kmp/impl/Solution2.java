package com.algorithm.course.kmp.impl;

import com.algorithm.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/28 2:34 下午
 */
@Slf4j
public class Solution2 {

    public int indexOf(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() < str2.length() || str2.length() < 1) {
            return -1;
        }
        int[] next = buildNext(str2);
        int i = 0, j = 0;
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

    public int[] buildNext(String str) {
        if (str.length() == 1) {
            return new int[] {-1};
        }
        int[] next = new int[str.length()];
        // 第0个位置的字符,之前无字符,默认设置为-1
        next[0] = -1;
        // 第1个位置之前的字符只有0位置的字符,前缀或者后缀不包括字符串本身,所以前缀和后缀的数量均为0,最大长度也为0
        // 生成第i个位置的最长且相等的前缀后缀的长度最大值,i需要从2开始,i-1则为1
        int i = 2;
        // [y,y,y,y], ? ,[y,y,y,y], i-1, i
        // [       ]     [       ]   4
        // 在i-1位置最长且相同的前后缀最大长度为7的前提下,如果?位置的字符和i-1位置的字符相同,那么i位置的值即为4+1=5
        int cn = 0;
        while (i < str.length()) {
            if (str.charAt(i - 1) == str.charAt(cn)) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                // 发现不匹配,cn 大于0,cn回退到上一次匹配的位置
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public int[] getNextArray(String str2) {
        if (str2.length() == 1) {
            return new int[] {-1};
        }
        int[] next = new int[str2.length()];
        next[0] = -1;
        next[1] = 0;
        int i = 2; // 目前在哪个位置上求next数组的值
        int cn = 0; // 当前是哪个位置的值再和i-1位置的字符比较
        while (i < next.length) {
            if (str2.charAt(i - 1) == str2.charAt(cn)) { // 配成功的时候
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static class TestClass {

        @Test
        public void test() {
            String str = "abcfabck";
            Solution2 solution2 = new Solution2();
            int[] next1 = solution2.buildNext(str);
            log.info("next1:{}", next1);
        }

        @Test
        public void testIndexOf1() {
            String str1 = "abcfabckabcf";
            String str2 = "abck";
            Solution2 solution2 = new Solution2();
            int index = solution2.indexOf(str1, str2);
            log.info("index:{}", index);
        }

        @Test
        public void test2() {
            for (int i = 0; i < 100000; i++) {
                String str = StringUtil.randomStrOfNumber(16);
                Solution2 solution2 = new Solution2();
                int[] next1 = solution2.buildNext(str);
                int[] next2 = solution2.getNextArray(str);
                for (int j = 0; j < next1.length; j++) {
                    if (next1[j] != next2[j]) {
                        log.info("str:{}", str);
                        log.info("next1:{}", next1);
                        log.info("next2:{}", next2);
                        assert false;
                    }
                }

            }

        }

    }
}
