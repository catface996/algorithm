package com.algorithm.course.AC自动机.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/11 11:29 上午
 */
@Slf4j
public class Solution3 {

    private static final char START_CHAR = 'a';

    public static class Node {
        // 是否是有效的终点,代表某个模式串的结束位置
        private int end;
        // 达到此处的长度
        private int length;
        // 模式串在模式串列表中的下标
        private int patternPosition;
        // fail指针,假设匹配到当前节点失败,在内容文本中,已当前节点为最末字符且能匹配上前缀树中最长字符的位置
        private Node fail;
        // 只支持英文小写
        private Node[] nexts = new Node[26];
    }

    public static class AcAutomation {

        private Node root;
        private int patternNum;

        public AcAutomation(String[] patterns) {
            root = new Node();
            patternNum = patterns.length;
            insert(patterns);
            build();
        }

        private void insert(String[] patterns) {
            Node cur;
            Node next;
            int index;
            for (int position = 0; position < patterns.length; position++) {
                cur = root;
                String pattern = patterns[position];
                for (int i = 0; i < pattern.length(); i++) {
                    index = pattern.charAt(i) - START_CHAR;
                    next = cur.nexts[index];
                    if (next == null) {
                        next = new Node();
                        cur.nexts[index] = next;
                    }
                    cur = next;
                }
                cur.end++;
                cur.length = pattern.length();
                cur.patternPosition = position;
            }
        }

        public void build() {
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            Node cur;
            Node cfail;
            while (!queue.isEmpty()) {
                cur = queue.poll(); // 父
                for (int i = 0; i < 26; i++) { // 下级所有的路
                    if (cur.nexts[i] != null) { // 该路下有子节点
                        cur.nexts[i].fail = root; // 初始时先设置一个值
                        cfail = cur.fail;
                        while (cfail != null) { // cur不是头节点
                            if (cfail.nexts[i] != null) {
                                cur.nexts[i].fail = cfail.nexts[i];
                                break;
                            }
                            cfail = cfail.fail;
                        }
                        queue.add(cur.nexts[i]);
                    }
                }
            }
        }

        public List<List<Integer>> containNum(String content) {
            List<List<Integer>> ans = new ArrayList<>();
            for (int i = 0; i < patternNum; i++) {
                ans.add(new ArrayList<>());
            }
            char[] str = content.toCharArray();
            Node cur = root;
            Node follow;
            int index;
            for (int i = 0; i < str.length; i++) {
                index = str[i] - START_CHAR;
                while (cur.nexts[index] == null && cur != root) {
                    cur = cur.fail;
                }
                cur = cur.nexts[index] != null ? cur.nexts[index] : root;
                follow = cur;
                while (follow != root) {
                    if (follow.end > 0) {
                        List<Integer> patternCourIndexList = ans.get(follow.patternPosition);
                        patternCourIndexList.add(i - follow.length + 1);
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
            AcAutomation acAutomation = new AcAutomation(patterns);
            List<List<Integer>> ans = acAutomation.containNum(context);
            log.info("ans:{}", ans);
        }
    }
}
