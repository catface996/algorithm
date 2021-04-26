package course.滑动窗口.子数组中的最大值减最小值小于等于指定值;

import java.util.LinkedList;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.ArrayUtil;

/**
 * @author by catface
 * @date 2021/4/22 2:05 下午
 */
@Slf4j
public class SubArrMaxMinInRange2 {

    //给定一个整型数组arr，和一个整数num
    //某个arr中的子数组sub，如果想达标，必须满足：
    //sub中最大值 – sub中最小值 <= num，
    //返回arr中达标子数组的数量

    // 暴力的对数器方法
    public int subArrMaxMinInRange(int[] arr, int num) {
        LinkedList<Integer> maxQ = new LinkedList<>();
        LinkedList<Integer> minQ = new LinkedList<>();
        int count = 0;
        int left = 0;
        int right = 0;
        while (left < arr.length) {
            while (right < arr.length) {
                while (!maxQ.isEmpty() && arr[maxQ.peekLast()] <= arr[right]) {
                    maxQ.pollLast();
                }
                maxQ.addLast(right);
                while (!minQ.isEmpty() && arr[minQ.peekLast()] >= arr[right]) {
                    minQ.pollLast();
                }
                minQ.addLast(right);
                // 返现最大值减最小值不满足条件时,退出
                if (arr[maxQ.peekFirst()] - arr[minQ.peekFirst()] > num) {
                    break;
                } else {
                    right++;
                }
            }
            // 累加子数组数量,滑动窗口的右侧下标减左侧下标即为子数组的数量(已左侧为起点的子数组)
            // 当滑动窗口划过left后,会重新计算下一个以left为起点的子数组
            count += right - left;
            if (left == maxQ.peekFirst()) {
                maxQ.pollFirst();
            }
            if (left == minQ.peekFirst()) {
                minQ.pollFirst();
            }
            left++;
        }
        return count;
    }

    public static class TestClass {
        @Test
        public void test1() {
            for (int i = 0; i < 1000; i++) {
                int[] arr = ArrayUtil.randomIntArray(10, 1, 40);
                SubArrMaxMinInRange2 subArrMaxMinInRange2 = new SubArrMaxMinInRange2();
                SubArrMaxMinInRange subArrMaxMinInRange1 = new SubArrMaxMinInRange();
                int ans1 = subArrMaxMinInRange2.subArrMaxMinInRange(arr, 10);
                int ans2 = subArrMaxMinInRange1.num(arr, 10);
                if (ans1 != ans2) {
                    log.info("arr:{},ans1:{},ans2:{}", arr, ans1, ans2);
                }
                assert ans1 == ans2;
            }
        }

        @Test
        public void test2() {
            int[] arr = {39, 34, 14, 10, 6, 12, 8, 11, 33, 4};
            int num = 10;
            SubArrMaxMinInRange2 subArrMaxMinInRange = new SubArrMaxMinInRange2();
            int ans1 = subArrMaxMinInRange.subArrMaxMinInRange(arr, num);
            SubArrMaxMinInRange subArrMaxMinInRange1 = new SubArrMaxMinInRange();
            int ans2 = subArrMaxMinInRange1.getSubArrInRange(arr, num);
            log.info("ans1:{},ans2:{}", ans1, ans2);
        }
    }
}
