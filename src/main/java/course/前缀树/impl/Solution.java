package course.前缀树.impl;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/10 3:32 下午
 */
public class Solution {

    public static class Node {
        /**
         * 经过当前节点的字符数量
         */
        private int pass;
        /**
         * 已当前字符为节点的字符数量
         */
        private int end;
        /**
         * 后续的节点
         */
        private Map<Character, Node> nexts = new HashMap<>();
    }

    public static class Trie {
        private Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String s) {
            Node cur = root;
            for (int i = 0; i < s.length(); i++) {
                Node next = cur.nexts.get(s.charAt(i));
                if (next == null) {
                    next = new Node();
                    next.pass = 1;
                    cur.nexts.put(s.charAt(i), next);
                } else {
                    next.pass += 1;
                }
                cur = next;
            }
            cur.end += 1;
        }

        public int queryExactMatch(String s) {
            if (s == null || s.length() < 1) {
                return -1;
            }
            Node cur = root;
            for (int i = 0; i < s.length(); i++) {
                cur = cur.nexts.get(s.charAt(i));
                if (cur == null) {
                    return 0;
                }
            }
            return cur.end;
        }

        public int queryPrefixMatch(String prefix) {
            if (prefix == null || prefix.length() < 1) {
                return -1;
            }
            Node cur = root;
            for (int i = 0; i < prefix.length(); i++) {
                cur = cur.nexts.get(prefix.charAt(i));
                if (cur == null) {
                    return 0;
                }
            }
            return cur.pass;
        }
    }

    public static class TestClass {

        @Test
        public void testQueryExactMatch() {
            String[] arr = {"ab", "abc", "abcd", "x", "xy", "xyz", "ab"};
            Trie trie = new Trie();
            for (String s : arr) {
                trie.insert(s);
            }
            int ans = trie.queryExactMatch("ab");
            int ans2 = trie.queryPrefixMatch("ab");
            System.out.println(ans);
            System.out.println(ans2);
            int ans3 = trie.queryPrefixMatch("xy");
            System.out.println(ans3);
        }

        @Test
        public void testQueryPrefixMatch() {

        }

    }
}
