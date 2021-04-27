package leetcode.滑动窗口.面试题17_18最短超串;

//假设你有两个数组，一个长一个短，短的元素均不相同。找到长数组中包含短数组所有的元素的最短子数组，其出现顺序无关紧要。
//
// 返回最短子数组的左端点和右端点，如有多个满足条件的子数组，返回左端点最小的一个。若不存在，返回空数组。
//
// 示例 1:
//
// 输入:
//big = [7,5,9,0,2,1,3,5,7,9,1,1,5,8,8,9,7]
//small = [1,5,9]
//输出: [7,10]
//
// 示例 2:
//
// 输入:
//big = [1,2,3]
//small = [4]
//输出: []
//
// 提示：
//
//
// big.length <= 100000
// 1 <= small.length <= 100000
//
// Related Topics Sliding Window
// 👍 27 👎 0

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
@Slf4j
class Solution {

    // 9:18 下午	info
    //				解答成功:
    //				执行耗时:44 ms,击败了44.27% 的Java用户
    //				内存消耗:57.8 MB,击败了10.09% 的Java用户

    public int[] shortestSeq(int[] big, int[] small) {
        if (big == null || big.length <= 0 || small == null || small.length <= 0) {
            return new int[0];
        }
        int minLength = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        LinkedList<Integer> queue = new LinkedList<>();
        Count count = new Count(small);
        int i = 0;
        while (i < big.length) {
            while (!count.isFull() && i < big.length) {
                if (count.canAdd(big[i])) {
                    queue.addLast(i);
                    count.add(big[i]);
                }
                i++;
            }
            while (count.isFull()) {
                if (queue.peekLast() - queue.peekFirst() < minLength) {
                    minLength = queue.peekLast() - queue.peekFirst();
                    start = queue.peekFirst();
                    end = queue.peekLast();
                }
                int leftIndex = queue.pollFirst();
                count.remove(big[leftIndex]);
            }
        }
        if (end == 0) {
            return new int[0];
        }
        return new int[] {start, end};
    }

    public static class Count {
        Map<Integer, Integer> target;
        Set<Integer> source;
        int size;

        public Count(int[] small) {
            this.size = small.length;
            target = new HashMap<>();
            source = new HashSet<>(small.length);
            for (int i : small) {
                source.add(i);
            }
        }

        public boolean canAdd(int value) {
            return source.contains(value);
        }

        public void add(int value) {
            int count = target.getOrDefault(value, 0);
            target.put(value, count + 1);
        }

        public void remove(int value) {
            if (target.containsKey(value)) {
                int count = target.get(value);
                if (count == 1) {
                    target.remove(value);
                } else {
                    target.put(value, count - 1);
                }
            }
        }

        public boolean isFull() {
            return target.size() == size;
        }
    }

    public static class TestClass {
        // 输入:
        //big = [7,5,9,0,2,1,3,5,7,9,1,1,5,8,8,9,7]
        //small = [1,5,9]
        //输出: [7,10]
        @Test
        public void test() {
            int[] big = {7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7};
            int[] small = {1, 5, 9};
            int[] ans = new Solution().shortestSeq(big, small);
            log.info("ans:{},leftValue:{}->rightValue:{}", ans, big[ans[0]], big[ans[1]]);
        }

        //Count解答失败:
        //Count测试用例:[521704, 897261, 279103, 381783, 668374, 934085, 254258, 726184, 496153, 804155]
        //Count[897261, 9385, 381783, 496153]
        //Count测试结果:[1,8]
        //Count期望结果:[]

        @Test
        public void test2() {
            int[] big = {521704, 897261, 279103, 381783, 668374, 934085, 254258, 726184, 496153, 804155};
            int[] small = {897261, 9385, 381783, 496153};
            int[] ans = new Solution().shortestSeq(big, small);
            if (ans.length > 0) {
                log.info("ans:{},leftValue:{}->rightValue:{}", ans, big[ans[0]], big[ans[1]]);
            } else {
                log.info("ans:{}", ans);
            }
        }

        // big [842, 336, 777, 112, 789, 801, 922, 874, 634, 121, 390, 614, 179, 565, 740, 672, 624, 130, 555, 714,
        // 9, 950, 887, 375, 312, 862, 304, 121, 360, 579, 937, 148, 614, 885, 836, 842, 505, 187, 210, 536, 763,
        // 880, 652, 64, 272, 675, 33, 341, 101, 673, 995, 485, 16, 434, 540, 284, 567, 821, 994, 174, 634, 597, 919,
        // 547, 340, 2, 512, 433, 323, 895, 965, 225, 702, 387, 632, 898, 297, 351, 936, 431, 468, 694, 287, 671,
        // 190, 496, 80, 110, 491, 365, 504, 681, 672, 825, 277, 138, 778, 851, 732, 176]
        // small [950, 885, 702, 101, 312, 652, 555, 936, 842, 33, 634, 851, 174, 210, 287, 672, 994, 614, 732, 919,
        // 504, 778, 340, 694, 880, 110, 777, 836, 365, 375, 536, 547, 887, 272, 995, 121, 225, 112, 740, 567, 898,
        // 390, 579, 505, 351, 937, 825, 323, 874, 138, 512, 671, 297, 179, 277, 304]
        //期望结果:[2,98]
        @Test
        public void test3() {
            int[] big = {842, 336, 777, 112, 789, 801, 922, 874, 634, 121, 390, 614, 179, 565, 740, 672, 624, 130, 555,
                714, 9, 950, 887, 375, 312, 862, 304, 121, 360, 579, 937, 148, 614, 885, 836, 842, 505, 187, 210, 536,
                763, 880, 652, 64, 272, 675, 33, 341, 101, 673, 995, 485, 16, 434, 540, 284, 567, 821, 994, 174, 634,
                597, 919, 547, 340, 2, 512, 433, 323, 895, 965, 225, 702, 387, 632, 898, 297, 351, 936, 431, 468, 694,
                287, 671, 190, 496, 80, 110, 491, 365, 504, 681, 672, 825, 277, 138, 778, 851, 732, 176};
            int[] small = {950, 885, 702, 101, 312, 652, 555, 936, 842, 33, 634, 851, 174, 210, 287, 672, 994, 614, 732,
                919, 504, 778, 340, 694, 880, 110, 777, 836, 365, 375, 536, 547, 887, 272, 995, 121, 225, 112, 740, 567,
                898, 390, 579, 505, 351, 937, 825, 323, 874, 138, 512, 671, 297, 179, 277, 304};
            int[] ans = new Solution().shortestSeq(big, small);
            if (ans.length > 0) {
                log.info("ans:{},leftValue:{}->rightValue:{}", ans, big[ans[0]], big[ans[1]]);
            } else {
                log.info("ans:{}", ans);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

