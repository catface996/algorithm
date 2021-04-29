package course.manacher.impl;

import org.junit.Test;
import util.StringUtil;

/**
 * @author by catface
 * @date 2021/4/28 4:24 下午
 */
public class SolutionForce {

    public int maxPalindromForce(String str) {
        if (str == null) {
            return 0;
        }
        char[] preChars = StringUtil.preProcessForPalindrome(str);
        int max = 0;
        for (int i = 0; i < preChars.length; i++) {
            int l = i;
            int r = i;
            while (l >= 0 && r < preChars.length) {
                if (preChars[l] == preChars[r]) {
                    l--;
                    r++;
                } else {
                    max = Math.max(max, (r - l) / 2 - 1);
                    break;
                }
            }
        }
        return max;
    }

    public static class TestClass {
        @Test
        public void testForce1() {
            String str = "dadjsfla!abcdefedcba!lkjl";
            SolutionForce solutionForce = new SolutionForce();
            int max = solutionForce.maxPalindromForce(str);
            System.out.println(max);
        }

        @Test
        public void testForce2() {
            String str = "axdefedby";
            SolutionForce solutionForce = new SolutionForce();
            int max = solutionForce.maxPalindromForce(str);
            System.out.println(max);
        }
    }

}
