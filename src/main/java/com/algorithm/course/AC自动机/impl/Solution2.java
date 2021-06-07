package com.algorithm.course.AC自动机.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/10 4:58 下午
 */
public class Solution2 {

    public static class AcAutomation {
        private Node root;

        public AcAutomation() {
            root = new Node();
        }

        public void insert(String s) {
            Node cur = root;
            Node next;
            for (int i = 0; i < s.length(); i++) {
                char symbol = s.charAt(i);
                next = cur.nexts.get(symbol);
                if (next == null) {
                    next = new Node();
                    cur.nexts.put(symbol, next);
                }
                cur = next;
            }
            cur.end++;
        }

        public void build() {
            Node cur;
            // 头结点的fail->null
            root.fail = null;
            Queue<Node> queue = new LinkedList<>();
            for (Node next : root.nexts.values()) {
                // 头节点的所有第一层子节点的fail指针指向root
                next.fail = root;
                queue.add(next);
            }
            while (!queue.isEmpty()) {
                cur = queue.poll();
                for (Entry<Character, Node> next : cur.nexts.entrySet()) {
                    queue.add(next.getValue());
                    // 默认设置所有子节点的fail为root,如果发现有效的fail,则替换即可
                    next.getValue().fail = root;
                    // 如果父节点的fail指针不为空,当前节点经过字符 x 到达next,则检查cur的fail节点,是否有经过字符 x 向下的节点 failNext,如果有,
                    // next的fail指针指向failNext
                    char x = next.getKey();
                    Node curFail = cur.fail;
                    while (curFail != null) {
                        if (curFail.nexts.containsKey(x)) {
                            next.getValue().fail = curFail.nexts.get(x);
                            break;
                        } else {
                            curFail = curFail.fail;
                        }
                    }
                }
            }
        }

        public int containNum(String context) {
            Node cur = root;
            // 用于在前缀树中某一条边中向下匹配
            Node follow;
            // 记录匹配的关键字的数量
            int ans = 0;
            for (int i = 0; i < context.length(); i++) {
                char x = context.charAt(i);
                while (!cur.nexts.containsKey(x) && cur != root) {
                    // 匹配失败,并且当前节点不是根节点,从fail指针处继续匹配,直到fail指针的下一节点能匹配或者fail指针指向了根节点,跳出从fail指针上的遍历
                    cur = cur.fail;
                }
                // 从fail指针跳出后,如果当前节点(可能是有效的fail节点,也可能是root节点)经过待匹配的符号 x 能找到下一节点
                // 当前节点指向下一节点,否则,指向头节点
                cur = cur.nexts.containsKey(x) ? cur.nexts.get(x) : root;
                //if (cur.nexts.containsKey(x)) {
                //    cur = cur.nexts.get(x);
                //}
                follow = cur;
                // 只有当follow不是root节点时,才向下继续匹配
                while (follow != root) {
                    if (follow.end == -1) {
                        // 非结束节点,未完全匹配当前分支,需要下一个字符继续向下匹配
                        break;
                    }
                    {
                        // 在此处可以实现不同的业务诉求,比如不论关键字出现几次,都值作为出现一次,则可以修改end值,或者通过其他手段来去重
                        ans += follow.end;
                        // 当前节点已经成功匹配,设置为-1,避免下一次匹配到继续累加,统计的时出现的敏感词的种数
                        follow.end = -1;
                    }
                    // 当前字符陪陪成功后,继续通过fail指针向下匹配.
                    follow = follow.fail;
                }
            }
            return ans;
        }
    }

    public static class Node {
        /**
         * 可以通过end来实现不同的业务诉求
         * <p>
         * 在前缀树种,end代表出现了多少个相同的字符串
         */
        private int end;
        private Node fail;
        private Map<Character, Node> nexts = new HashMap<>();
    }

    public static class TestClass {
        @Test
        public void test() {
            AcAutomation acAutomation = new AcAutomation();
            acAutomation.insert("无耻");
            acAutomation.insert("真无耻之徒");
            acAutomation.insert("王八");
            acAutomation.insert("超级王八蛋");
            acAutomation.insert("x");
            acAutomation.insert("xy");
            acAutomation.insert("xyz");
            acAutomation.build();
            int ans = acAutomation.containNum("真无耻之徒");
            System.out.println(ans);
        }

        @Test
        public void test2() {
            AcAutomation acAutomation = new AcAutomation();
            acAutomation.insert("wc");
            acAutomation.insert("zwczt");
            acAutomation.build();
            int ans = acAutomation.containNum("zwcztzwczt");
            System.out.println(ans);
        }
    }
}
