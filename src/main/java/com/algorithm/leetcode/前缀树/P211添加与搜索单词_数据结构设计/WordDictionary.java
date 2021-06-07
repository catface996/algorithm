package com.algorithm.leetcode.前缀树.P211添加与搜索单词_数据结构设计;

//请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
//
// 实现词典类 WordDictionary ：
//
//
// WordDictionary() 初始化词典对象
// void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
// bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回 false 。word 中可能包含一些 '
//.' ，每个 . 都可以表示任何一个字母。
//
//
//
//
// 示例：
//
//
//输入：
//["WordDictionary","addWord","addWord","addWord","search","search","search","se
//arch"]
//[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
//输出：
//[null,null,null,null,false,true,true,true]
//
//解释：
//WordDictionary wordDictionary = new WordDictionary();
//wordDictionary.addWord("bad");
//wordDictionary.addWord("dad");
//wordDictionary.addWord("mad");
//wordDictionary.search("pad"); // return False
//wordDictionary.search("bad"); // return True
//wordDictionary.search(".ad"); // return True
//wordDictionary.search("b.."); // return True
//
//
//
//
// 提示：
//
//
// 1 <= word.length <= 500
// addWord 中的 word 由小写英文字母组成
// search 中的 word 由 '.' 或小写英文字母组成
// 最多调用 50000 次 addWord 和 search
//
// Related Topics 深度优先搜索 设计 字典树 回溯算法
// 👍 241 👎 0

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
class WordDictionary {

    // 11:04 上午	info
    //					解答成功:
    //					执行耗时:48 ms,击败了88.75% 的Java用户
    //					内存消耗:49.2 MB,击败了41.25% 的Java用户

    private Node root;

    /**
     * Initialize your data structure here.
     */
    public WordDictionary() {
        root = new Node();
    }

    public void addWord(String word) {
        Node cur = root;
        Node next;
        int index;
        for (int i = 0; i < word.length(); i++) {
            index = word.charAt(i) - 'a';
            next = cur.nexts[index];
            if (next == null) {
                next = new Node();
                cur.nexts[index] = next;
            }
            cur = next;
        }
        cur.end++;
    }

    public boolean search(String word) {
        return search(word, 0, root);
    }

    public boolean search(String word, int position, Node node) {
        Node cur = node;
        Node next;
        int index;
        for (int i = position; i < word.length(); i++) {
            if (word.charAt(i) == '.') {
                boolean ans;
                for (int j = 0; j < 26; j++) {
                    if (cur.nexts[j] != null) {
                        ans = search(word, i + 1, cur.nexts[j]);
                        if (ans) {
                            return true;
                        }
                    }
                }
                return false;
            } else {
                index = word.charAt(i) - 'a';
                next = cur.nexts[index];
                if (next == null) {
                    return false;
                }
                cur = next;
            }
        }
        return cur.end > 0;
    }

    public static class Node {
        private int end;
        private Node[] nexts = new Node[26];
    }

    public static class TestClass {
        // 示例：
        //
        //
        //输入：
        //["WordDictionary","addWord","addWord","addWord","search","search","search","se
        //arch"]
        //[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
        //输出：
        //[null,null,null,null,false,true,true,true]
        @Test
        public void test() {
            WordDictionary w = new WordDictionary();
            w.addWord("bad");
            w.addWord("dad");
            w.addWord("mad");
            boolean ans1 = w.search("pad");
            boolean ans2 = w.search("bad");
            boolean ans3 = w.search(".ad");
            boolean ans4 = w.search("b..");
            System.out.println(ans1);
            System.out.println(ans2);
            System.out.println(ans3);
            System.out.println(ans4);
        }

        //解答失败:
        //测试用例:["WordDictionary","addWord","addWord","search","search","search","search","search",
        //"search"]
        //[[],["a"],["a"],["."],["a"],["aa"],["a"],[".a"],["a."]]
        //期望结果:[null,null,null,true,true,false,true,false,false]
        @Test
        public void test2() {
            WordDictionary w = new WordDictionary();
            w.addWord("a");
            w.addWord("a");
            System.out.println(w.search("."));
            System.out.println(w.search("a"));
            System.out.println(w.search("aa"));
            System.out.println(w.search("a"));
            System.out.println(w.search(".a"));
            System.out.println(w.search("a."));
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such: WordDictionary obj = new WordDictionary();
 * obj.addWord(word); boolean param_2 = obj.search(word);
 */
//leetcode submit region end(Prohibit modification and deletion)

