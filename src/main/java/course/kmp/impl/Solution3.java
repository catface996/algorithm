package course.kmp.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/29 9:54 上午
 */
@Slf4j
public class Solution3 {

    public int indexOf(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() < str2.length() || str2.length() < 1) {
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

    private int[] buildNext(String str) {
        if (str.length() == 1) {
            return new int[] {-1};
        }
        int[] next = new int[str.length()];
        next[0] = -1;
        int i = 2;
        int cn = 0;
        while (i < str.length()) {
            if (str.charAt(i - 1) == str.charAt(cn)) {
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
        public void testNext1() {
            String s = "abcabck";
            Solution3 solution3 = new Solution3();
            int[] next = solution3.buildNext(s);
            log.info("next:{}", next);
        }

        @Test
        public void testIndexOf1() {
            String str1 = "abcabck";
            String str2 = "abck";
            Solution3 solution3 = new Solution3();
            int index1 = solution3.indexOf(str1, str2);
            int index2 = str1.indexOf(str2);
            log.info("str1:{},str2:{},index1:{},index2:{}", str1, str2, index1, index2);
        }

        @Test
        public void testIndexOf2() {
            String str1 = "abcabck";
            String str2 = "k";
            Solution3 solution3 = new Solution3();
            int index1 = solution3.indexOf(str1, str2);
            int index2 = str1.indexOf(str2);
            log.info("str1:{},str2:{},index1:{},index2:{}", str1, str2, index1, index2);
        }
    }

}
