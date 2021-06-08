package com.algorithm.leetcode.动态规划.P140单词拆分II;

//给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的
//句子。
//
// 说明：
//
//
// 分隔时可以重复使用字典中的单词。
// 你可以假设字典中没有重复的单词。
//
//
// 示例 1：
//
// 输入:
//s = "catsanddog"
//wordDict = ["cat", "cats", "and", "sand", "dog"]
//输出:
//[
//  "cats and dog",
//  "cat sand dog"
//]
//
//
// 示例 2：
//
// 输入:
//s = "pineapplepenapple"
//wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
//输出:
//[
//  "pine apple pen apple",
//  "pineapple pen apple",
//  "pine applepen apple"
//]
//解释: 注意你可以重复使用字典中的单词。
//
//
// 示例 3：
//
// 输入:
//s = "catsandog"
//wordDict = ["cats", "dog", "sand", "and", "cat"]
//输出:
//[]
//
// Related Topics 动态规划 回溯算法
// 👍 463 👎 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
@Slf4j
public class SolutionUseDp {

    // 11:33 上午	info
    //					解答成功:
    //					执行耗时:6 ms,击败了23.27% 的Java用户
    //					内存消耗:37 MB,击败了27.32% 的Java用户
    //TODO 需要继续优化

    public List<String> wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() < 1 || wordDict == null || wordDict.isEmpty()) {
            return new ArrayList<>();
        }
        Set<String> wordSet = new HashSet<>(wordDict);
        return process(s, wordSet);
    }

    public List<String> process(String s, Set<String> wordSet) {
        List<List<String>> dp = new ArrayList<>(s.length() + 1);
        for (int i = 0; i < s.length(); i++) {
            dp.add(null);
        }
        dp.add(Collections.singletonList(""));
        List<String> ans;
        for (int index = s.length() - 1; index >= 0; index--) {
            ans = new ArrayList<>();
            for (int end = index; end < s.length(); end++) {
                String prefix = s.substring(index, end + 1);
                if (wordSet.contains(prefix)) {
                    List<String> temp = dp.get(end + 1);
                    for (String s1 : temp) {
                        if (!"".equals(s1)) {
                            ans.add(prefix + " " + s1);
                        } else {
                            ans.add(prefix);
                        }
                    }
                }
            }
            dp.set(index, ans);
        }
        return dp.get(0);
    }

    public static class TestClass {
        // 示例 1：
        //
        // 输入:
        //s = "catsanddog"
        //wordDict = ["cat", "cats", "and", "sand", "dog"]
        //输出:
        //[
        //  "cats and dog",
        //  "cat sand dog"
        //]
        @Test
        public void test1() {
            String s = "catsanddog";
            List<String> wordDict = Arrays.asList("cat", "cats", "and", "sand", "dog");
            SolutionUseDp solution = new SolutionUseDp();
            List<String> ans = solution.wordBreak(s, wordDict);
            log.info("ans:{}", ans);
        }

        // 示例 2：
        //
        // 输入:
        //s = "pineapplepenapple"
        //wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
        //输出:
        //[
        //  "pine apple pen apple",
        //  "pineapple pen apple",
        //  "pine applepen apple"
        //]
        //解释: 注意你可以重复使用字典中的单词。
        @Test
        public void test2() {
            String s = "pineapplepenapple";
            List<String> wordDict = Arrays.asList("apple", "pen", "applepen", "pine", "pineapple");
            SolutionUseDp solution = new SolutionUseDp();
            List<String> ans = solution.wordBreak(s, wordDict);
            log.info("ans:{}", ans);
        }

        // 示例 3：
        //
        // 输入:
        //s = "catsandog"
        //wordDict = ["cats", "dog", "sand", "and", "cat"]
        //输出:
        //[]
        @Test
        public void test3() {
            String s = "catsandog";
            List<String> wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat");
            SolutionUseDp solution = new SolutionUseDp();
            List<String> ans = solution.wordBreak(s, wordDict);
            log.info("ans:{}", ans);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

