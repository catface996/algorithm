package com.algorithm.course.树;

/**
 * @author by catface
 * @date 2021/3/22 3:11 下午
 */
public class Trie {

    public static class Node {
        public Node[] next;
        private int pass;
        private int end;

        public Node() {
            pass = 0;
            end = 0;
            next = new Node[26];
        }
    }

    public static class Tree {
        private Node root;

        public Tree() {
            root = new Node();
        }

        public void insert(String word) {
            if (word == null || "".equals(word)) {
                return;
            }
            char[] str = word.toCharArray();
            Node node = root;
            node.pass++;
            int path = 0;
            for (int i = 0; i < str.length; i++) {
                path = str[i] - 'a';
                if (node.next[path] == null) {
                    node.next[path] = new Node();
                }
                node = node.next[path];
                node.pass++;
            }
            node.end++;
        }

        public void delete(String word) {
            if (search(word) <= 0) {
                return;
            }
            char[] str = word.toCharArray();
            Node node = root;
            node.pass--;
            int path = 0;
            for (int i = 0; i < str.length; i++) {
                path = str[i] - 'a';
                if (--node.next[path].pass == 0) {
                    node.next[path] = null;
                    return;
                }
                node = node.next[path];
            }
            node.end--;
        }

        public int search(String word) {
            if (word == null || "".equals(word)) {
                return 0;
            }
            char[] str = word.toCharArray();
            Node node = root;
            int path = 0;
            for (char c : str) {
                path = c - 'a';
                if (node.next[path] == null) {
                    return 0;
                }
                node = node.next[path];
            }
            return node.pass;
        }

        public int prefixNumber(String prefix) {
            if (prefix == null || "".equals(prefix)) {
                return 0;
            }
            char[] str = prefix.toCharArray();
            Node currentNode = root;
            int path = 0;
            for (int i = 0; i < str.length; i++) {
                path = str[i] - 'a';
                if (currentNode.next[path] == null) {
                    return 0;
                }
                currentNode = currentNode.next[path];
            }
            return currentNode.pass;
        }
    }
}
