package course.kmp.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.StringUtil;

/**
 * @author by catface
 * @date 2021/5/19 11:11 上午
 */
@Slf4j
public class Solution6 {

    public int kmpIndexOf(String str1, String str2) {
        if (str1 == null || str1.length() < 1 || str2 == null || str2.length() < 1 || str1.length() < str2.length()) {
            return -1;
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

    private int[] buildNext(String s) {
        if (s.length() == 1) {
            return new int[] {-1};
        }
        int[] next = new int[s.length()];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < s.length()) {
            if (s.charAt(i - 1) == s.charAt(cn)) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                //TODO 记住此处,是cn大于0,不是next[cn]>0
                // cn>0的意思是,当前位置,发现的最长且相等的前缀和后缀的长度大于0
                // 自然可以在 说明cn位置往左直到0位置 和 i-1位置往左cn+1个字符是匹配的
                // 如果cn位置的最长且相等的前后缀存在,假设长度是cn`,则可以从cn`位置与i位置继续向后匹配是否一致.
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
            Solution6 solution6 = new Solution6();
            int[] next = solution6.buildNext(s);
            log.info("next:{}", next);
            Solution5 solution5 = new Solution5();
            int[] next2 = solution5.buildNext(s);
            log.info("next2:{}", next2);
        }

        @Test
        public void testIndexOf() {
            String str1 = "785136574212522116218547772836";
            String str2 = "384";
            Solution6 solution6 = new Solution6();
            int index = solution6.kmpIndexOf(str1, str2);
            System.out.println(index);
        }

        @Test
        public void testForce() {
            for (int time = 0; time < 10000; time++) {
                Solution6 solution6 = new Solution6();
                String str1 = StringUtil.randomStrOfNumber(30);
                String str2 = StringUtil.randomStrOfNumber((int)(Math.random() * 8));
                int ans1 = solution6.kmpIndexOf(str1, str2);
                int ans2 = str1.indexOf(str2);
                if (ans1 != ans2) {
                    log.info("str1:{},str2:{},ans1:{},ans2:{}", str1, str2, ans1, ans2);
                }
                assert ans1 == ans2;
            }
        }
    }

}
