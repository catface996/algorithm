package question.class3_能力差值为K的两人可比赛.code;

import java.util.Arrays;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/26 11:02 上午
 */
public class Solution {
    /**
     * 最多有 arr.length/2 场比赛同时进行
     */

    public int maxGameNum(int[] arr, int k) {
        if (arr == null || arr.length < 2 || k < 0) {
            return 0;
        }
        Arrays.sort(arr);
        boolean[] use = new boolean[arr.length];
        int cur = 0;
        int matchCur = 1;
        int ans = 0;
        while (cur < arr.length && matchCur < arr.length) {
            if (use[matchCur]) {
                matchCur++;
                continue;
            }
            if (use[cur]) {
                cur++;
                continue;
            }
            if (cur == matchCur) {
                matchCur++;
                continue;
            }
            if (arr[matchCur] - arr[cur] < k) {
                matchCur++;
            } else if (arr[matchCur] - arr[cur] == k) {
                ans++;
                use[cur] = true;
                use[matchCur] = true;
                cur++;
                matchCur++;
            } else {
                cur++;
            }
        }
        return ans;
    }

    public static class TestClass {

        @Test
        public void test1() {
            int[] arr = {0, 4, 2, 3, 19, 6};
            int k = 2;
            Solution solution = new Solution();
            int ans = solution.maxGameNum(arr, k);
            System.out.println(ans);
        }

        @Test
        public void test2() {
            int[] arr = {1, 1, 14, 15, 8};
            int k = 0;
            Solution solution = new Solution();
            int ans = solution.maxGameNum(arr, k);
            System.out.println(ans);
        }

        @Test
        public void testForce() {
            int maxLen = 10;
            int maxValue = 20;
            int maxK = 5;
            int testTime = 1000;
            Solution solution = new Solution();
            System.out.println("功能测试开始");
            for (int i = 0; i < testTime; i++) {
                int N = (int)(Math.random() * (maxLen + 1));
                int[] arr = Code04_MaxPairNumber.randomArray(N, maxValue);
                int[] arr1 = Code04_MaxPairNumber.copyArray(arr);
                int[] arr2 = Code04_MaxPairNumber.copyArray(arr);
                int k = (int)(Math.random() * (maxK + 1));
                int ans1 = solution.maxGameNum(arr1, k);
                int ans2 = Code04_MaxPairNumber.maxPairNum2(arr2, k);
                if (ans1 != ans2) {
                    System.out.println("Oops!");
                    Code04_MaxPairNumber.printArray(arr);
                    System.out.println(k);
                    System.out.println(ans1);
                    System.out.println(ans2);
                    break;
                }
            }
            System.out.println("功能测试结束");
        }
    }

}
