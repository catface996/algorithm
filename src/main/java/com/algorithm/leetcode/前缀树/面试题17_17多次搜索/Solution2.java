package com.algorithm.leetcode.前缀树.面试题17_17多次搜索;

//给定一个较长字符串big和一个包含较短字符串的数组smalls，设计一个方法，根据smalls中的每一个较短字符串，对big进行搜索。输出smalls中的字
//符串在big里出现的所有位置positions，其中positions[i]为smalls[i]出现的所有位置。
//
// 示例：
//
// 输入：
//big = "mississippi"
//smalls = ["is","ppi","hi","sis","i","ssippi"]
//输出： [[1,4],[8],[],[3],[1,4,7,10],[5]]
//
//
// 提示：
//
//
// 0 <= len(big) <= 1000
// 0 <= len(smalls[i]) <= 1000
// smalls的总字符数不会超过 100000。
// 你可以认为smalls中没有重复字符串。
// 所有出现的字符均为英文小写字母。
//
// Related Topics 字典树 字符串
// 👍 22 👎 0

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/11 5:07 下午
 */
@Slf4j
public class Solution2 {

    public int[][] multiSearch(String big, String[] smalls) {
        AcAutomation acAutomation = new AcAutomation(smalls);
        List<List<Integer>> ans = acAutomation.contain(big);
        int[][] result = new int[ans.size()][];
        for (int i = 0; i < ans.size(); i++) {
            int[] temp = new int[ans.get(i).size()];
            List<Integer> tempArr = ans.get(i);
            for (int j = 0; j < tempArr.size(); j++) {
                temp[j] = tempArr.get(j);
            }
            result[i] = temp;
        }
        return result;
    }

    public static class Node {
        /**
         * 经过前序节点,到达当前节点,并且以当前节点为结尾的模式串数量
         */
        private int end;
        /**
         * 达到当前节点经过的字符
         */
        private char passChar;
        /**
         * 假设匹配到当前节点失败,在已经匹配的目标文本中,以当前节点为结尾的所有后缀字符串中,能从root匹配到当前字符的长的前缀树分支.
         */
        private Node fail;
        /**
         * 模式串下标
         */
        private int patternIndex;
        /**
         * 模式串长度
         */
        private int patternLength;
        /**
         * 当前节点后续的所有分支
         */
        private Map<Character, Node> nextMap = new HashMap<>();
    }

    public static class AcAutomation {
        /**
         * 前缀树的根节点
         */
        private final Node root;
        private final int patternNum;

        public AcAutomation(String[] patterns) {
            root = new Node();
            patternNum = patterns.length;
            for (int i = 0; i < patternNum; i++) {
                insert(patterns[i], i);
            }
            build();
        }

        public void insert(String pattern, int patternIndex) {
            Node cur = root;
            Node next;
            for (int i = 0; i < pattern.length(); i++) {
                char symbol = pattern.charAt(i);
                next = cur.nextMap.get(symbol);
                if (next == null) {
                    next = new Node();
                    next.passChar = symbol;
                    cur.nextMap.put(symbol, next);
                }
                cur = next;
            }
            cur.patternIndex = patternIndex;
            cur.patternLength = pattern.length();
            cur.end++;
        }

        public void build() {
            Node cur;
            Node next;
            Node cFail;
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            root.fail = null;
            while (!queue.isEmpty()) {
                cur = queue.poll();
                for (Character symbol : cur.nextMap.keySet()) {
                    next = cur.nextMap.get(symbol);
                    // 默认fail指针指向root,发现有效fail时,指向有效的fail
                    next.fail = root;
                    cFail = cur.fail;
                    while (cFail != null) {
                        if (cFail.nextMap.containsKey(symbol)) {
                            next.fail = cFail.nextMap.get(symbol);
                            break;
                        }
                        cFail = cFail.fail;
                    }
                    queue.add(next);
                }
            }
        }

        public List<List<Integer>> contain(String context) {
            List<List<Integer>> ans = new ArrayList<>();
            for (int i = 0; i < patternNum; i++) {
                ans.add(new ArrayList<>());
            }
            if (context == null || "".equals(context)) {
                return ans;
            }
            Node cur = root;
            Node follow;
            for (int i = 0; i < context.length(); i++) {
                char matchSymbol = context.charAt(i);
                while (!cur.nextMap.containsKey(matchSymbol) && cur != root) {
                    cur = cur.fail;
                }
                cur = cur.nextMap.getOrDefault(matchSymbol, root);
                follow = cur;
                while (follow != root) {
                    if (follow.end > 0) {
                        ans.get(follow.patternIndex).add(i - follow.patternLength + 1);
                    }
                    follow = follow.fail;
                }
            }
            return ans;
        }
    }

    public static class TestClass {
        // 示例：
        //
        // 输入：
        //big = "mississippi"
        //smalls = ["is","ppi","hi","sis","i","ssippi"]
        //输出： [[1,4],[8],[],[3],[1,4,7,10],[5]]
        @Test
        public void test() {
            String[] patterns = {"is", "ppi", "hi", "sis", "i", "ssippi"};
            String context = "mississippi";
            com.algorithm.course.AC自动机.impl.Solution5 solution5 = new com.algorithm.course.AC自动机.impl.Solution5();
            int[][] ans = solution5.multiSearch(context, patterns);
            log.info("ans:{}", ans);
        }

        @Test
        public void test2() {
            String[] patterns = {"a", "b", "c"};
            String context = "";
            com.algorithm.course.AC自动机.impl.Solution5 solution5 = new com.algorithm.course.AC自动机.impl.Solution5();
            int[][] ans = solution5.multiSearch(context, patterns);
            log.info("ans:{}", ans);
        }
    }
}
