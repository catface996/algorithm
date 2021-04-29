package course.manacher.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.StringUtil;

/**
 * @author by catface
 * @date 2021/4/28 5:30 下午
 */
@Slf4j
public class Solution {

    public int manacher(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        char[] preStr = StringUtil.preProcessForPalindrome(s);
        int[] radiusArr = new int[preStr.length];
        radiusArr[0] = 1;
        int c = 0;
        int maxR = 0;
        int i = 0;
        int ans = 1;
        while (i < preStr.length - 1) {
            i++;
            // 如果要左右延伸探测时的起点
            int left = i - 1;
            int right = i + 1;
            if (i < maxR) {
                // 当i位置落在最大右回文边界以内,必定存在以c为中心的对称位置i',检查i'位置记录的回文半径,判断i'位置的回文半径是否超过左侧的L
                int leftI = (c << 1) - i;
                int radius = radiusArr[leftI];
                if (i + radius - 1 < maxR) {
                    // i位置的回文半径最右侧仍旧在最大回文右边界以内,i位置扩展出的回文无需再左右扩展探测
                    radiusArr[i] = radiusArr[leftI];
                    continue;
                }
                // 更改已i位置为中心时,左右延伸探测的起点
                left = i * 2 - maxR - 1;
                right = maxR + 1;
            }

            // i> 最大回文右边界 或者 i<=最大回文右边界且i位置的回文半径右边界超过了最大回文右边界
            while (left >= 0 && right < preStr.length) {
                if (preStr[left] == preStr[right]) {
                    left--;
                    right++;
                } else {
                    break;
                }
            }

            // 计算新的最大回文右边界,计算新的中心点,填充i位置的回文半径,计算新的最大回文直径
            maxR = right - 1;
            c = i;
            radiusArr[i] = maxR - c + 1;
            ans = Math.max(ans, (maxR - c) * 2 + 1);
        }
        // 原始字符的最大回文直径为ans/2;
        return ans / 2;
    }

    public static class TestClass {
        @Test
        public void test1() {
            String s = "";
            Solution solution = new Solution();
            int ans = solution.manacher(s);
            assert ans == 0;
        }

        @Test
        public void test12() {
            String s = null;
            Solution solution = new Solution();
            int ans = solution.manacher(s);
            assert ans == 0;
        }

        @Test
        public void test3() {
            String s = "a";
            Solution solution = new Solution();
            int ans = solution.manacher(s);
            assert ans == 1;
        }

        @Test
        public void test4() {
            String s = "ab";
            Solution solution = new Solution();
            int ans = solution.manacher(s);
            assert ans == 1;
        }

        @Test
        public void test5() {
            String s = "aba";
            Solution solution = new Solution();
            int ans = solution.manacher(s);
            assert ans == 3;
        }

        @Test
        public void test6() {
            String s = "abaaba";
            Solution solution = new Solution();
            int ans = solution.manacher(s);
            assert ans == 6;
        }

        @Test
        public void test7() {
            String s = "85274658432433811226";
            Solution solution = new Solution();
            int ans = solution.manacher(s);
            assert ans == 2;
        }

        @Test
        public void testForce1() {
            int max = 0;
            for (int i = 0; i < 10000; i++) {
                String s = StringUtil.randomStrOfNumber(20);
                Solution solution = new Solution();
                int ans1 = solution.manacher(s);
                int ans2 = Code01_Manacher.manacher(s);
                max = Math.max(max, ans1);
                if (ans1 != ans2) {
                    log.info("s:{},ans1:{},ans2:{}", s, ans1, ans2);
                }
                assert ans1 == ans2;
            }
            log.info("最大回文长度:{}", max);
        }
    }
}
