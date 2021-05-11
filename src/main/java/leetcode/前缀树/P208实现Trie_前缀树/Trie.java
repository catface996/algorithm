package leetcode.前缀树.P208实现Trie_前缀树;

//Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼
//写检查。
//
// 请你实现 Trie 类：
//
//
// Trie() 初始化前缀树对象。
// void insert(String word) 向前缀树中插入字符串 word 。
// boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false
// 。
// boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否
//则，返回 false 。
//
//
//
//
// 示例：
//
//
//输入
//["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
//[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
//输出
//[null, null, true, false, true, null, true]
//
//解释
//Trie trie = new Trie();
//trie.insert("apple");
//trie.search("apple");   // 返回 True
//trie.search("app");     // 返回 False
//trie.startsWith("app"); // 返回 True
//trie.insert("app");
//trie.search("app");     // 返回 True
//
//
//
//
// 提示：
//
//
// 1 <= word.length, prefix.length <= 2000
// word 和 prefix 仅由小写英文字母组成
// insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次
//
// Related Topics 设计 字典树
// 👍 751 👎 0

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
@Slf4j
public class Trie {

    // 10:27 上午	info
    //					解答成功:
    //					执行耗时:40 ms,击败了80.57% 的Java用户
    //					内存消耗:47.1 MB,击败了89.50% 的Java用户

    private Node root;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        root = new Node();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        Node cur = root;
        int index;
        for (int i = 0; i < word.length(); i++) {
            index = word.charAt(i) - 'a';
            Node next = cur.nexts[index];
            if (next == null) {
                next = new Node();
                next.pass = 1;
                cur.nexts[index] = next;
            } else {
                cur.pass += 1;
            }
            cur = next;
        }
        cur.end += 1;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        Node cur = root;
        Node next;
        int index;
        for (int i = 0; i < word.length(); i++) {
            index = word.charAt(i) - 'a';
            next = cur.nexts[index];
            if (next == null) {
                return false;
            }
            cur = next;
        }
        return cur.end > 0;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        Node cur = root;
        Node next;
        int index;
        for (int i = 0; i < prefix.length(); i++) {
            index = prefix.charAt(i) - 'a';
            next = cur.nexts[index];
            if (next == null) {
                return false;
            }
            cur = next;
        }
        return cur.pass > 0;
    }

    public static class Node {
        private int pass;
        private int end;
        private Node[] nexts = new Node[26];
    }

    public static class TestClass {
        // 示例：
        //
        //
        //输入
        //["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
        //[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
        //输出
        //[null, null, true, false, true, null, true]
        @Test
        public void testSearch() {
            Trie trie = new Trie();
            trie.insert("apple");
            boolean ans1 = trie.search("apple");
            System.out.println(ans1);
            boolean ans2 = trie.search("app");
            System.out.println(ans2);
            boolean ans3 = trie.startsWith("app");
            System.out.println(ans3);
            trie.insert("app");
            boolean ans4 = trie.search("app");
            System.out.println(ans4);
        }
    }
}

/**
 * Your Trie object will be instantiated and called as such: Trie obj = new Trie(); obj.insert(word); boolean param_2 =
 * obj.search(word); boolean param_3 = obj.startsWith(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)
