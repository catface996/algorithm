package com.algorithm.leetcode.回溯算法.P17电话号码的字母组合;

//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
//
//
//
//
//
// 示例 1：
//
//
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
//
//
// 示例 2：
//
//
//输入：digits = ""
//输出：[]
//
//
// 示例 3：
//
//
//输入：digits = "2"
//输出：["a","b","c"]
//
//
//
//
// 提示：
//
//
// 0 <= digits.length <= 4
// digits[i] 是范围 ['2', '9'] 的一个数字。
//
//
// Related Topics 哈希表 字符串 回溯 👍 2101 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;


public class Solution {

  /**
   * 递归方式
   * 解答成功:
   * 执行耗时:5 ms,击败了15.37% 的Java用户
   * 内存消耗:41.9 MB,击败了5.01% 的Java用户
   *
   * @param digits
   * @return
   */
  public List<String> letterCombinations(String digits) {
    if (digits == null || "".equals(digits)) {
      return new ArrayList<>();
    }
    char[] cdg = new char[]{'a', 'd', 'g', 'j', 'm', 'p', 't', 'w'};
    int[] dig = new int[]{3, 3, 3, 3, 3, 4, 3, 4};
    List<StringBuilder> tmpAns = progress(cdg, dig, digits, 0);
    List<String> ans = new ArrayList<>();
    for (StringBuilder tmpAn : tmpAns) {
      ans.add(tmpAn.toString());
    }
    return ans;
  }

  public List<StringBuilder> progress(char[] cdg, int[] dig, String digStr, int p) {
    if (p >= digStr.length()) {
      return new ArrayList<>();
    }
    int curChar = digStr.charAt(p) - '2';
    List<StringBuilder> last = progress(cdg, dig, digStr, p + 1);
    List<StringBuilder> ans = new ArrayList<>();
    if (last.isEmpty()) {
      for (int i = 0; i < dig[curChar]; i++) {
        StringBuilder sb = new StringBuilder();
        sb.append((char) (cdg[curChar] + i));
        ans.add(sb);
      }
    } else {
      for (int i = 0; i < dig[curChar]; i++) {
        for (StringBuilder s : last) {
          StringBuilder sb = new StringBuilder();
          sb.append((char) (cdg[curChar] + i));
          sb.append(s);
          ans.add(sb);
        }
      }
    }

    return ans;
  }


  public static class TestClass {

    @Test
    public void test_1() {
      List<String> target = Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf");
      Solution solution = new Solution();
      List<String> ans = solution.letterCombinations("23");
      assert target.containsAll(ans) && ans.containsAll(target);
    }

    @Test
    public void test_2() {
      List<String> target = Arrays.asList("a", "b", "c");
      Solution solution = new Solution();
      List<String> ans = solution.letterCombinations("2");
      assert target.containsAll(ans) && ans.containsAll(target);
    }
  }

}
