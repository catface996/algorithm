package com.algorithm.leetcode.排序.面试题17_08马戏团人塔;

//有个马戏团正在设计叠罗汉的表演节目，一个人要站在另一人的肩膀上。出于实际和美观的考虑，在上面的人要比下面的人矮一点且轻一点。已知马戏团每个人的身高和体重，请
//编写代码计算叠罗汉最多能叠几个人。
//
// 示例：
//
//
//输入：height = [65,70,56,75,60,68] weight = [100,150,90,190,95,110]
//输出：6
//解释：从上往下数，叠罗汉最多能叠 6 层：(56,90), (60,95), (65,100), (68,110), (70,150), (75,190)
//
//
// 提示：
//
//
// height.length == weight.length <= 10000
//
// Related Topics 排序 二分查找 动态规划
// 👍 66 👎 0

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
@Slf4j
public class Solution {

    public int bestSeqAtIndex(int[] height, int[] weight) {
        List<Person> people = convertPerson(height, weight);
        people.sort((o1, o2) -> {
            int result = Integer.compare(o1.height, o2.height);
            if (result == 0) {
                return Integer.compare(o2.weight, o1.weight);
            }
            return result;
        });
        int[] weightAfterSortHeight = new int[weight.length];
        for (int i = 0; i < weight.length; i++) {
            weightAfterSortHeight[i] = people.get(i).weight;
        }
        return lengthOfLIS(weightAfterSortHeight);
    }

    public List<Person> convertPerson(int[] height, int[] weight) {
        List<Person> personArr = new ArrayList<>(height.length);
        for (int i = 0; i < height.length; i++) {
            Person person = new Person(i, height[i], weight[i]);
            personArr.add(person);
        }
        return personArr;
    }

    /**
     * 最长增长序列
     *
     * @param nums 体重数组
     * @return 最长增增序列长度
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxLength = 1;
        for (int cur = 1; cur < nums.length; cur++) {
            int dpMax = 0;
            for (int i = 0; i < cur; i++) {
                int curValue = nums[cur];
                if (nums[i] < curValue) {
                    dpMax = Math.max(dpMax, dp[i]);
                }
            }
            dpMax++;
            dp[cur] = dpMax;
            maxLength = Math.max(maxLength, dpMax);
        }
        return maxLength;
    }

    public int lengthOfLIS2(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                int l = 1, r = len, pos = 0;
                // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }

    public static class Person {
        int id;
        int height;
        int weight;

        public Person(int id, int height, int weight) {
            this.id = id;
            this.height = height;
            this.weight = weight;
        }
    }

    public static class TestClass {
        //输入：height = [65,70,56,75,60,68] weight = [100,150,90,190,95,110]
        //输出：6
        //解释：从上往下数，叠罗汉最多能叠 6 层：(56,90), (60,95), (65,100), (68,110), (70,150), (75,190)
        @Test
        public void test1() {
            int[] height = new int[] {65, 70, 56, 75, 60, 68};
            int[] weight = new int[] {100, 150, 90, 190, 95, 110};
            Solution solution = new Solution();
            int maxLevel = solution.bestSeqAtIndex(height, weight);
            System.out.println("最大层高:" + maxLevel);
        }

        @Test
        public void test2() {
            int[] height = new int[] {1, 2, 3, 4};
            int[] weight = new int[] {4, 3, 2, 1};
            Solution solution = new Solution();
            int maxLevel = solution.bestSeqAtIndex(height, weight);
            System.out.println("最大层高:" + maxLevel);
        }

        @Test
        public void test3() {
            int[] height = new int[] {8378, 8535, 8998, 3766, 648, 6184, 5506, 5648, 3907, 6773};
            int[] weight = new int[] {9644, 849, 3232, 3259, 5229, 314, 5593, 9600, 6695, 4340};
            Solution solution = new Solution();
            int maxLevel = solution.bestSeqAtIndex(height, weight);
            System.out.println("最大层高:" + maxLevel);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

