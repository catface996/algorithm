package question.绳子压住最多的点.code;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.ArrayUtil;

/**
 * @author by catface
 * @date 2021/5/20 2:33 下午
 */
@Slf4j
public class Solution3 {

    /**
     * 仍旧使用滑动窗口
     * <p>
     * 不使用队列,使用最左侧和最右侧下标即可
     *
     * @param arr 数轴上有序排列的点
     * @param k   绳子的长度
     * @return 绳子能覆盖的最大点数
     */
    public int maxPointNum(int[] arr, int k) {
        int left = 0;
        int right = 0;
        int ans = 0;
        while (right < arr.length) {
            if (arr[right] - arr[left] <= k) {
                right++;
            } else {
                // 后续发现的区间如果符合要求,一定不会小于当前的最大值,所以left和right可以一起++,即窗口变大后,窗口长度不回退
                ans = Math.max(ans, right - left);
                left++;
                right++;
            }
        }
        ans = Math.max(ans, right - left);
        return ans;
    }

    public static class TestClass {

        @Test
        public void test() {
            for (int i = 0; i < 10000; i++) {
                int[] arr = ArrayUtil.randomSortedNotRepeat(10, 1, 5);
                int k = 7;
                SolutionForce solutionForce = new SolutionForce();
                Solution3 solution = new Solution3();
                int ans1 = solutionForce.maxPointNum(arr, k);
                int ans2 = solution.maxPointNum(arr, k);
                if (ans1 != ans2) {
                    log.info("forceAns:{},ans:{},arr:{},k:{}", ans1, ans2, arr, k);
                    assert false;
                }
            }
            log.info("Good,you success!");
        }

        @Test
        public void test2() {
            int[] arr = {1, 5, 8, 11, 15, 20, 21, 23, 25, 27};
            int k = 7;
            SolutionForce solutionForce = new SolutionForce();
            Solution3 solution = new Solution3();
            int forceAns = solutionForce.maxPointNum(arr, k);
            int ans = solution.maxPointNum(arr, k);
            System.out.println(ans);
        }
    }

}
