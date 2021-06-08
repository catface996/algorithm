package com.algorithm.leetcode.动态规划.P139单词拆分;

//给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
//
// 说明：
//
//
// 拆分时可以重复使用字典中的单词。
// 你可以假设字典中没有重复的单词。
//
//
// 示例 1：
//
// 输入: s = "leetcode", wordDict = ["leet", "code"]
//输出: true
//解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
//
//
// 示例 2：
//
// 输入: s = "applepenapple", wordDict = ["apple", "pen"]
//输出: true
//解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
//     注意你可以重复使用字典中的单词。
//
//
// 示例 3：
//
// 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
//输出: false
//
// Related Topics 动态规划
// 👍 1014 👎 0

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
@Slf4j
public class SolutionUseDp {


    // 10:54 上午	info
    //					解答成功:
    //					执行耗时:10 ms,击败了36.03% 的Java用户
    //					内存消耗:38.7 MB,击败了57.60% 的Java用户
    //TODO 需要继续优化

    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || wordDict == null || wordDict.isEmpty()) {
            return false;
        }
        Set<String> wordSet = new HashSet<>(wordDict);
        return process(s, wordSet);
    }

    public boolean process(String s, Set<String> wordSet) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[s.length()] = true;
        boolean ans;
        for (int index = s.length() - 1; index >= 0; index--) {
            ans = false;
            for (int end = index; end < s.length(); end++) {
                String prefix = s.substring(index, end + 1);
                if (wordSet.contains(prefix)) {
                    if (dp[end + 1]) {
                        ans = true;
                        break;
                    }
                }
            }
            dp[index] = ans;
        }
        return dp[0];
    }

    public static class TestClass {
        // 示例 1：
        //
        // 输入: s = "leetcode", wordDict = ["leet", "code"]
        //输出: true
        //解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
        @Test
        public void test1() {
            String s = "leetcode";
            List<String> wordDict = Arrays.asList("leet", "code");
            SolutionUseDp solution = new SolutionUseDp();
            boolean ans = solution.wordBreak(s, wordDict);
            log.info("ans:{}", ans);
        }

        // 示例 2：
        //
        // 输入: s = "applepenapple", wordDict = ["apple", "pen"]
        //输出: true
        //解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
        //     注意你可以重复使用字典中的单词。
        @Test
        public void test2() {
            String s = "applepenapple";
            List<String> wordDict = Arrays.asList("apple", "pen");
            SolutionUseDp solution = new SolutionUseDp();
            boolean ans = solution.wordBreak(s, wordDict);
            log.info("ans:{}", ans);
        }

        // 示例 3：
        //
        // 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
        //输出: false
        @Test
        public void test3() {
            String s = "catsandog";
            List<String> wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat");
            SolutionUseDp solution = new SolutionUseDp();
            boolean ans = solution.wordBreak(s, wordDict);
            log.info("ans:{}", ans);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

