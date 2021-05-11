package course.AC自动机.impl;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/11 4:13 下午
 */
public class Solution4 {

    public static class Node {
        private char passChar;
        private int end;
        private Node fail;
        private Node[] nexts = new Node[26];
    }

    public static class AcAutomation {
        private Node root;

        public AcAutomation() {
            root = new Node();
        }

        public void insert(String pattern) {
            Node cur = root;
            Node next;
            int index;
            for (int i = 0; i < pattern.length(); i++) {
                index = pattern.charAt(i) - 'a';
                next = cur.nexts[index];
                if (next == null) {
                    next = new Node();
                    next.passChar = (char)(index + 'a');
                    cur.nexts[index] = next;
                }
                cur = next;
            }
            cur.end++;
        }

        public void build() {
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            Node cur;
            Node next;
            Node cFail;
            while (!queue.isEmpty()) {
                cur = queue.poll();
                for (int i = 0; i < 26; i++) {
                    next = cur.nexts[i];
                    if (next != null) {
                        // fail指针默认设置为root,如果发现后续有效的fail指针,再建立连接
                        next.fail = root;
                        cFail = cur.fail;
                        while (cFail != null) {
                            if (cFail.nexts[i] != null) {
                                next.fail = cFail.nexts[i];
                                break;
                            }
                            cFail = cFail.fail;
                        }
                        queue.add(next);
                    }
                }
            }
        }

        public int containNum(String context) {
            int ans = 0;
            // 首先在前缀树某一条边上一直向下匹配,在每个节点处,都横向经过fail指针匹配
            Node cur = root;
            int index;
            Node follow;
            for (int i = 0; i < context.length(); i++) {
                index = context.charAt(i) - 'a';
                while (cur.nexts[index] == null && cur.fail != root) {
                    cur = cur.fail;
                }
                cur = cur.nexts[index] != null ? cur.nexts[index] : root;
                follow = cur;
                while (follow != root) {
                    if (follow.end > 0) {
                        ans++;
                    }
                    follow = follow.fail;
                }
            }
            return ans;
        }
    }

    public static class TestClass {
        @Test
        public void test() {
            AcAutomation acAutomation = new AcAutomation();
            acAutomation.insert("abcde");
            acAutomation.insert("bcde");
            acAutomation.insert("cde");
            acAutomation.insert("de");
            acAutomation.insert("e");
            acAutomation.build();
            int ans = acAutomation.containNum("abcde");
            System.out.println(ans);
        }

        @Test
        public void test2() {
            AcAutomation ac = new AcAutomation();
            ac.insert("wc");
            ac.insert("zwczt");
            ac.build();
            System.out.println(ac.containNum("zwczt"));
        }
    }

}
