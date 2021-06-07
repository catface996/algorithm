package com.algorithm.course.kmp.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/28 9:55 上午
 */
@Slf4j
public class Solution {

    /**
     * 在str1中查找首次出现str2的首个下标
     *
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 首次出现字符串2的首个下标位置, 如果没有出现, 返回-1
     */
    public int indexOf(String str1, String str2) {
        if (str1 == null || str2 == null || str2.length() < 1 || str1.length() < str2.length()) {
            return -1;
        }
        int x = 0;
        int y = 0;
        int[] next = buildNext(str2);
        while (x < str1.length() && y < str2.length()) {
            if (str1.charAt(x) == str2.charAt(y)) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }
        return y == str2.length() ? x - y : -1;
    }

    /**
     * 求next数组
     *
     * @param str2 待匹配的子串
     * @return next数组
     */
    private int[] buildNext(String str2) {
        if (str2.length() == 1) {
            return new int[] {-1};
        }
        int[] next = new int[str2.length()];
        next[0] = -1;
        log.info("str:{}", str2);
        log.info("next[0]=-1");
        int i = 2;
        // 表示哪个位置的字符与i-1位置的字符比,初始值 i=2,i-1为1,current为0
        int current = 0;
        while (i < str2.length()) {
            log.info("i-1={}->{},cn={}->{},match:{}", i - 1, str2.charAt(i - 1), current, str2.charAt(current),
                str2.charAt(i - 1) == str2.charAt(current));
            if (str2.charAt(i - 1) == str2.charAt(current)) {
                log.info("next[{}]={}", i, current + 1);
                next[i++] = ++current;
            } else if (current > 0) {
                log.info("current=next[{}]->{}", current, next[current]);
                current = next[current];
            } else {
                log.info("next[{}]=0", i);
                next[i++] = 0;
            }
        }
        return next;
    }

    public static class TestClass {
        @Test
        public void testNext() {
            String str2 = "abababca";
            Solution solution = new Solution();
            int[] next = solution.buildNext(str2);
            log.info("str:{},next:{}", str2, next);
        }

        @Test
        public void testNext2() {
            String str2 = "abcabck";
            Solution solution = new Solution();
            int[] next = solution.buildNext(str2);
            log.info("str:{},next:{}", str2, next);
        }

        @Test
        public void testNext3() {
            String str2 = "abckabcfabckf";
            Solution solution = new Solution();
            int[] next = solution.buildNext(str2);
            log.info("str:{},next:{}", str2, next);
        }

        @Test
        public void testIndexOf() {
            String str1 = "ababababca";
            String str2 = "abababca";
            Solution solution = new Solution();
            int firstIndex = solution.indexOf(str1, str2);
            System.out.println(firstIndex);
        }
    }
}
