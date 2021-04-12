package course.暴力递归动态规划.数字串转字母串;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/12 2:45 下午
 */
public class SolutionUseDp {

    //规定1和A对应、2和B对应、3和C对应...26和Z对应
    //那么一个数字字符串比如"111”就可以转化为:
    //"AAA"、"KA"和"AK"
    //给定一个只有数字字符组成的字符串str，返回有多少种转化结果
    // 0到9的ascii码对应的10进制为48到57

    public static final char ZERO = '0';

    public int calculateMaxType(String numberStr) {
        return process2(numberStr);
    }

    public int process(String numberStr) {

        int[] dp = new int[numberStr.length() + 1];

        for (int currentIndex = numberStr.length(); currentIndex >= 0; currentIndex--) {

            // base case
            if (currentIndex == numberStr.length()) {
                dp[currentIndex] = 1;
                continue;
            }

            // base cae
            char currentChar = numberStr.charAt(currentIndex);
            if (currentChar == ZERO) {
                dp[currentIndex] = 0;
                continue;
            }

            // base case
            // 选择当前位置的数字转换成字符
            int ans = dp[currentIndex + 1];
            // 不选择当前数字转字符,选当前两位
            // 取当前数字,如果当前数字非0,可以转换成字母
            if (currentIndex + 1 < numberStr.length()) {
                char nextChar = numberStr.charAt(currentIndex + 1);
                if ((currentChar - ZERO) * 10 + (nextChar - ZERO) <= 26) {
                    ans += dp[currentIndex + 2];
                }
            }
            dp[currentIndex] = ans;
        }
        return dp[0];
    }

    public int process2(String numberStr) {

        int[] dp = new int[numberStr.length() + 1];
        dp[numberStr.length()] = 1;

        // 先填充最后两位
        char currentChar = numberStr.charAt(numberStr.length() - 1);
        if (currentChar == ZERO) {
            dp[numberStr.length() - 1] = 0;
        } else {
            dp[numberStr.length() - 1] = dp[numberStr.length()];
        }

        // 填充剩余
        for (int currentIndex = numberStr.length() - 2; currentIndex >= 0; currentIndex--) {
            currentChar = numberStr.charAt(currentIndex);
            if (currentChar == ZERO) {
                dp[currentIndex] = 0;
            }else{
                // 选择当前位置的数字转换成字符
                int ans = dp[currentIndex + 1];
                // 不选择当前数字转字符,选当前两位
                // 取当前数字,如果当前数字非0,可以转换成字母
                char nextChar = numberStr.charAt(currentIndex + 1);
                if ((currentChar - ZERO) * 10 + (nextChar - ZERO) <= 26) {
                    ans += dp[currentIndex + 2];
                }
                dp[currentIndex] = ans;
            }
        }
        return dp[0];
    }

    public static class TestClass {
        @Test
        public void test1() {
            String numberStr = "7210231231232031203123";
            Solution solution = new Solution();
            SolutionUseCache solutionUseCache = new SolutionUseCache();
            SolutionUseDp solutionUseDp = new SolutionUseDp();
            int maxTypes = solution.calculateMaxType(numberStr);
            int maxTypesUseCache = solutionUseCache.calculateMaxType(numberStr);
            int maxTypesUseDp = solutionUseDp.calculateMaxType(numberStr);
            System.out.println("转换方法数1:" + maxTypes + " 转换方法数2:" + maxTypesUseCache + " 转换方法数3:" + maxTypesUseDp);
        }
    }
}
