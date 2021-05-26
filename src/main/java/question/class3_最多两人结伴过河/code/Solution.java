package question.class3_最多两人结伴过河.code;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.ArrayUtil;

/**
 * @author by catface
 * @date 2021/5/26 2:22 下午
 */
@Slf4j
public class Solution {

    /**
     * 最少使用的船数量不会低于人数的一一半
     *
     * @param arr   体重数组
     * @param limit 船的载重限制
     * @return 使用的船数
     */
    public int minBoot(int[] arr, int limit) {
        // 将体重数组排序,分左右两部分
        Arrays.sort(arr);
        // 单个人的最大体重超过船的载重,无法全部过河
        if (arr[arr.length - 1] > limit) {
            return -1;
        }
        int mid = 0;
        int halfLimit = limit >> 1;
        while (mid < arr.length) {
            // 此处要小于等于,等于一半时,左侧区间的两个人可以同乘一条船,保证右侧区间无法两人同乘同一条船
            if (arr[mid] <= halfLimit) {
                mid++;
            } else {
                break;
            }
        }
        int l = mid - 1;
        int r = mid;
        int two = 0;
        while (l >= 0 && r < arr.length) {
            if (arr[l] + arr[r] <= limit) {
                two++;
                l--;
                r++;
                continue;
            }
            l--;
        }

        if (l < 0) {
            // 右侧先耗尽
            return two + ((mid - two + 1) >> 1) + (arr.length - r);
        }

        // 答案为 左侧有跟右侧匹配过河的人数 + 左侧剩余人数/2(向上取证) + 左侧剩余人数
        return two + ((mid - two + 1) >> 1);
    }

    public static class TestClass {

        // arr = {1,2,3,4,5,6,7,8,9};
        // limit = 10;
        // ans = 5;
        @Test
        public void test1() {
            int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
            int limit = 10;
            Solution solution = new Solution();
            int ans = solution.minBoot(arr, limit);
            int ans2 = Code05_MinBoat.minBoat(arr, limit);
            log.info("最少的船数,ans:{},ans2:{}", ans, ans2);
        }

        // arr = {1,2,3,4,5,6,7,8,9};
        // limit = 8;
        // ans = 6;
        @Test
        public void test2() {
            int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 8};
            int limit = 8;
            Solution solution = new Solution();
            int ans = solution.minBoot(arr, limit);
            int ans2 = Code05_MinBoat.minBoat(arr, limit);
            log.info("最少的船数,ans:{},ans2:{}", ans, ans2);
        }

        // arr = {1,2,3,3,3,6,7,8,9};
        // limit = 8;
        // ans = -1;
        @Test
        public void test3() {
            int[] arr = {1, 2, 3, 3, 3, 6, 7, 8, 9};
            int limit = 8;
            Solution solution = new Solution();
            int ans = solution.minBoot(arr, limit);
            int ans2 = Code05_MinBoat.minBoat(arr, limit);
            log.info("最少的船数,ans:{},ans2:{}", ans, ans2);
        }

        // arr =  {1, 2, 3, 3, 3, 3, 5, 5, 5};
        // limit = 8;
        // ans = 5;
        @Test
        public void test4() {
            int[] arr = {1, 2, 3, 3, 3, 3, 5, 5, 5};
            int limit = 8;
            Solution solution = new Solution();
            int ans = solution.minBoot(arr, limit);
            int ans2 = Code05_MinBoat.minBoat(arr, limit);
            log.info("最少的船数,ans:{},ans2:{}", ans, ans2);
        }

        @Test
        public void test5() {
            int[] arr = {2, 5, 5, 6, 6};
            int limit = 11;
            Solution solution = new Solution();
            int ans = solution.minBoot(arr, limit);
            int ans2 = Code05_MinBoat.minBoat(arr, limit);
            log.info("最少的船数,ans:{},ans2:{}", ans, ans2);
        }

        @Test
        public void testForce() {
            Solution solution = new Solution();
            for (int i = 0; i < 10000; i++) {
                int[] arr = ArrayUtil.randomIntArray(20, 1, 20);
                int limit = (int)(Math.random() * 25);
                int ans = solution.minBoot(arr, limit);
                int ans2 = Code05_MinBoat.minBoat(arr, limit);
                if (ans != ans2) {
                    log.info("最少的船数,ans:{},ans2:{},arr;{},limit:{}", ans, ans2, arr, limit);
                }
            }
            log.info("Good,you success!");
        }

    }
}
