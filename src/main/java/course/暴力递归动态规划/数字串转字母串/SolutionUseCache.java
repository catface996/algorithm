package course.暴力递归动态规划.数字串转字母串;

import java.util.Arrays;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/12 2:45 下午
 */
public class SolutionUseCache {

    //规定1和A对应、2和B对应、3和C对应...26和Z对应
    //那么一个数字字符串比如"111”就可以转化为:
    //"AAA"、"KA"和"AK"
    //给定一个只有数字字符组成的字符串str，返回有多少种转化结果
    // 0到9的ascii码对应的10进制为48到57

    public static final char ZERO = '0';

    public int calculateMaxType(String numberStr) {
        int[] cache = new int[numberStr.length() + 1];
        Arrays.fill(cache, -1);
        return process(numberStr, 0, cache);
    }

    public int process(String numberStr, int currentIndex, int[] cache) {

        // base case
        if (currentIndex == numberStr.length()) {
            return 1;
        }

        char currentChar = numberStr.charAt(currentIndex);
        if (currentChar == ZERO) {
            return 0;
        }

        int ans = cache[currentIndex];
        if (ans != -1) {
            return ans;
        }
        ans = 0;
        // 选择当前位置的数字转换成字符
        ans += process(numberStr, currentIndex + 1, cache);
        // 不选择当前数字转字符,选当前两位
        // 取当前数字,如果当前数字非0,可以转换成字母
        if (currentIndex + 1 < numberStr.length()) {
            char nextChar = numberStr.charAt(currentIndex + 1);
            if ((currentChar - ZERO) * 10 + (nextChar - ZERO) <= 26) {
                ans += process(numberStr, currentIndex + 2, cache);
            }
        }
        cache[currentIndex] = ans;
        return ans;
    }

    public static class TestClass {
        @Test
        public void test1() {
            String numberStr = "7210231231232031203123";
            SolutionUseCache solutionUseCache = new SolutionUseCache();
            Solution solution = new Solution();
            int maxTypes = solution.calculateMaxType(numberStr);
            int maxTypesUseCache = solutionUseCache.calculateMaxType(numberStr);
            System.out.println("转换方法数1:" + maxTypes + " 转换方法数2:" + maxTypesUseCache);
        }
    }
}
