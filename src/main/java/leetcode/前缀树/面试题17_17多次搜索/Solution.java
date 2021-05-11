package leetcode.前缀树.面试题17_17多次搜索;

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
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
public class Solution {

    // 2:43 下午	info
    //				解答成功:
    //				执行耗时:75 ms,击败了61.42% 的Java用户
    //				内存消耗:60.6 MB,击败了33.50% 的Java用户

    private static final char START_CHAR = 'a';

    public int[][] multiSearch(String big, String[] smalls) {
        AcAutomation acAutomation = new AcAutomation(smalls);
        List<List<Integer>> ans = acAutomation.containNum(big);
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
}
//leetcode submit region end(Prohibit modification and deletion)
