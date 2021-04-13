package course.暴力递归动态规划.最长公共子序列;

/**
 * @author by catface
 * @date 2021/4/13 2:01 下午
 */
public class SolutionForLeetCode {

    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text1.length() <= 0 || text2 == null || text2.length() <= 0) {
            return 0;
        }
        char[] str1Arr = text1.toCharArray();
        char[] str2Arr = text2.toCharArray();
        if (str1Arr.length < str2Arr.length) {
            return process(str2Arr, str1Arr);
        } else {
            return process(str1Arr, str2Arr);
        }
    }

    private int process(char[] str1Arr, char[] str2Arr) {
        int[] dp = new int[str2Arr.length + 1];
        int colValue;
        int colNextValue;
        int rowNextColNext;
        int newColValue;
        for (int row = str1Arr.length - 1; row >= 0; row--) {
            rowNextColNext = 0;
            for (int col = str2Arr.length - 1; col >= 0; col--) {
                colValue = dp[col];
                colNextValue = dp[col + 1];
                newColValue = Math.max(colValue, colNextValue);
                if (str1Arr[row] == str2Arr[col]) {
                    newColValue = Math.max(newColValue, rowNextColNext + 1);
                }
                rowNextColNext = colValue;
                if (newColValue > colValue) {
                    dp[col] = newColValue;
                }
            }
        }
        return dp[0];
    }
}
