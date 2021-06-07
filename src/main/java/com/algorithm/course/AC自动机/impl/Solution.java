package com.algorithm.course.AC自动机.impl;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/10 2:14 下午
 */
public class Solution {

    public static class ACAutomation {

        private Node root;

        public ACAutomation() {
            root = new Node();
        }

        /**
         * 插入需要匹配的串
         *
         * @param s 待匹配的串
         */
        public void insert(String s) {
            Node cur = root;
            int index;
            for (int i = 0; i < s.length(); i++) {
                index = s.charAt(i) - 'a';
                if (cur.nexts[index] == null) {
                    Node next = new Node();
                    cur.nexts[index] = next;
                }
                cur = cur.nexts[index];
            }
            cur.end++;
        }

        public void build() {
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            Node cur;
            Node cFail;
            while (!queue.isEmpty()) {
                cur = queue.poll();
                for (int i = 0; i < 26; i++) {
                    if (cur.nexts[i] != null) {
                        cur.nexts[i].fail = root;
                        cFail = cur.fail;
                        while (cFail != null) {
                            if (cFail.nexts[i] != null) {
                                cur.nexts[i].fail = cFail.nexts[i];
                                break;
                            }
                            cFail = cFail.fail;
                        }
                        queue.add(cur.nexts[i]);
                    }
                }
            }
        }

        public int containNum(String content) {
            Node cur = root;
            Node follow;
            int index = 0;
            int ans = 0;
            for (int i = 0; i < content.length(); i++) {
                index = content.charAt(i) - 'a';
                // 当前节点没有向下的路,并且当前节点不是root
                //1.现在来到的节点是可以向下匹配的
                //2.现在来到的节点是根节点了
                while (cur.nexts[index] == null && cur != root) {
                    cur = cur.fail;
                }
                cur = cur.nexts[index] != null ? cur.nexts[index] : root;
                follow = cur;
                while (follow != root) {
                    if (follow.end == -1) {
                        break;
                    }
                    ans += follow.end;
                    follow.end = -1;
                    follow = follow.fail;
                }
            }
            return ans;
        }

        public static class Node {
            public int end;
            public Node fail;
            public Node[] nexts;

            public Node() {
                end = 0;
                fail = null;
                nexts = new Node[26];
            }
        }
    }

    public static class TestClass {
        @Test
        public void test() {
            ACAutomation acAutomation = new ACAutomation();
            acAutomation.insert("fuck");
            acAutomation.insert("shit");
            acAutomation.build();
            int ans = acAutomation.containNum("gofuckyourself");
            System.out.println(ans);
        }
    }
}
