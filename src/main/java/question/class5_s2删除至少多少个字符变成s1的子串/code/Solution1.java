package question.class5_s2删除至少多少个字符变成s1的子串.code;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/6/2 10:24 上午
 */
@Slf4j
public class Solution1 {

    // 解题思路,获取s2的所有子序列(注意不是子串)按长度从大到小排序,逐个做kmp

    public int minDeleteToSubStr(String s1, String s2) {
        List<String> subs = getAllSubSeq(s2);
        subs.sort((o1, o2) -> o2.length() - o1.length());
        for (String sub : subs) {
            if (kmpIndexOf(s1, sub) >= 0) {
                return s2.length() - sub.length();
            }
        }
        return -1;
    }

    private List<String> getAllSubSeq(String s2) {
        List<String> subs = new ArrayList<>();
        process(s2, "", 0, subs);
        return subs;
    }

    private void process(String s2, String pre, int index, List<String> subs) {
        if (index >= s2.length()) {
            subs.add(pre);
            return;
        }
        // 当前位置不选择为子序列的元素
        process(s2, pre, index + 1, subs);
        // 当前位置选择为子序列的元素
        process(s2, pre + s2.charAt(index), index + 1, subs);
    }

    private int kmpIndexOf(String s1, String s2) {
        int[] next = buildNext(s2);
        int i = 0;
        int j = 0;
        while (i < s1.length() && j < s2.length()) {
            if (s1.charAt(i) == s2.charAt(j)) {
                i++;
                j++;
            } else if (next[j] != -1) {
                j = next[j];
            } else {
                i++;
            }
        }
        return j == s2.length() ? i - j : -1;
    }

    private int[] buildNext(String s2) {
        if (s2.length() <= 1) {
            return new int[] {-1};
        }
        int[] next = new int[s2.length()];
        next[0] = -1;
        next[1] = 0;
        int cn = 0;
        int i = 2;
        while (i < s2.length() - 1) {
            if (s2.charAt(cn) == s2.charAt(i)) {
                next[i] = ++cn;
                i++;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                i++;
            }
        }
        return next;
    }

    private int[] buildNextForce(String s2) {
        if (s2.length() <= 1) {
            return new int[] {-1};
        }
        int[] next = new int[s2.length()];
        next[0] = -1;
        next[1] = 0;
        for (int i = 2; i < s2.length(); i++) {
            int l = 0;
            for (int length = i; length >= 1; length--) {
                if (s2.substring(0, length).equals(s2.substring(i - length + 1, i + 1))) {
                    l = length;
                    break;
                }
            }
            next[i] = l;
        }
        return next;
    }

    public static class TestClass {
        @Test
        public void test() {
            String s1 = "abcde", s2 = "axbc";
            Solution1 solution1 = new Solution1();
            int minCount = solution1.minDeleteToSubStr(s1, s2);
            System.out.println(minCount);
        }

        @Test
        public void testBuildNext() {
            String s2 = "abcabck";
            Solution1 solution1 = new Solution1();
            int[] next = solution1.buildNext(s2);
            int[] next2 = solution1.buildNextForce(s2);
            log.info("next:{}", next);
            log.info("next2:{}", next2);
        }

        @Test
        public void testKmpIndexOf1() {
            String s1 = "abcabck";
            String s2 = "abck";
            Solution1 solution1 = new Solution1();
            int index = solution1.kmpIndexOf(s1, s2);
            log.info("kmp index:{}", index);
        }

        @Test
        public void testKmpIndexOf2() {
            String s1 = "abcabck";
            String s2 = "abc";
            Solution1 solution1 = new Solution1();
            int index = solution1.kmpIndexOf(s1, s2);
            log.info("kmp index:{}", index);
        }
    }

}
