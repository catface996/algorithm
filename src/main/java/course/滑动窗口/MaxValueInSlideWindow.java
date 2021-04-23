package course.滑动窗口;

import java.util.LinkedList;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.ArrayUtil;

/**
 * @author by catface
 * @date 2021/4/22 9:59 上午
 */
@Slf4j
public class MaxValueInSlideWindow {

    //假设一个固定大小为W的窗口，依次划过arr，
    //返回每一次滑出状况的最大值
    //例如，arr = [4,3,5,4,3,3,6,7], W = 3
    //返回：[5,5,5,4,6,7]

    public int[] right(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        int N = arr.length;
        int[] res = new int[N - w + 1];
        int index = 0;
        int L = 0;
        int R = w - 1;
        while (R < N) {
            int max = arr[L];
            for (int i = L + 1; i <= R; i++) {
                max = Math.max(max, arr[i]);
            }
            res[index++] = max;
            L++;
            R++;
        }
        return res;
    }

    public int[] getMaxInSlideWindowForce(int[] arr, int w) {
        int[] temp = new int[w];
        int[] ans = new int[arr.length - w + 1];
        for (int i = 0; i <= arr.length - w; i++) {
            System.arraycopy(arr, i, temp, 0, w);
            int max = 0;
            for (int j = 0; j < temp.length; j++) {
                max = Math.max(max, temp[j]);
            }
            ans[i] = max;
        }
        return ans;
    }

    public int[] getMaxInSlideWindow(int[] arr, int w) {
        if (arr == null || arr.length <= w || w <= 0) {
            return null;
        }
        LinkedList<Integer> maxQueue = new LinkedList<>();
        int index = 0;
        int[] ans = new int[arr.length - w + 1];
        for (int rightIndex = 0; rightIndex < arr.length; rightIndex++) {
            while (!maxQueue.isEmpty() && arr[maxQueue.peekLast()] <= arr[rightIndex]) {
                maxQueue.pollLast();
            }
            maxQueue.addLast(rightIndex);
            if (maxQueue.peekFirst() == rightIndex - w) {
                maxQueue.pollFirst();
            }
            if (rightIndex >= w - 1) {
                ans[index++] = arr[maxQueue.peekFirst()];
            }
        }
        return ans;
    }

    public int[] getMinInSlideWindow(int[] arr, int w) {
        if (arr == null || arr.length < w || w <= 0) {
            return null;
        }
        int index = 0;
        int[] ans = new int[arr.length - w + 1];
        LinkedList<Integer> minQueue = new LinkedList<>();
        for (int rightIndex = 0; rightIndex < arr.length; rightIndex++) {
            while (!minQueue.isEmpty() && arr[minQueue.peekLast()] >= arr[rightIndex]) {
                minQueue.pollLast();
            }
            minQueue.addLast(rightIndex);
            if (minQueue.peekFirst() == rightIndex - w) {
                minQueue.pollFirst();
            }
            if (rightIndex >= w - 1) {
                ans[index++] = arr[minQueue.peekFirst()];
            }
        }
        return ans;
    }

    public static class TestClass {

        @Test
        public void test0() {
            int[] arr = new int[] {4, 3, 5, 4, 3, 3, 6, 7};
            int w = 3;
            MaxValueInSlideWindow maxValueInSlideWindow = new MaxValueInSlideWindow();
            int[] ans = maxValueInSlideWindow.getMaxInSlideWindow(arr, w);
            int[] ans2 = maxValueInSlideWindow.getMaxInSlideWindowForce(arr, w);
            log.info("arr:{},ans:{},ans2:{}", arr, ans, ans2);
        }

        @Test
        public void test1() {
            int[] arr = ArrayUtil.randomIntArray(10, 1, 40);
            MaxValueInSlideWindow maxValueInSlideWindow = new MaxValueInSlideWindow();
            int[] ans = maxValueInSlideWindow.getMaxInSlideWindow(arr, 3);
            log.info("arr:{},ans:{}", arr, ans);
        }

        @Test
        public void test2() {
            int[] arr = new int[] {4, 3, 5, 7, 4, 9, 6, 7};
            int w = 3;
            MaxValueInSlideWindow maxValueInSlideWindow = new MaxValueInSlideWindow();
            int[] ans = maxValueInSlideWindow.getMinInSlideWindow(arr, w);
            log.info("arr:{},ans:{}", arr, ans);
        }

        @Test
        public void test3() {
            for (int times = 0; times < 10000; times++) {
                int[] arr = ArrayUtil.randomIntArray(100, 1, 300);
                int w = 5;
                MaxValueInSlideWindow maxValueInSlideWindow = new MaxValueInSlideWindow();
                int[] ans1 = maxValueInSlideWindow.getMaxInSlideWindowForce(arr, w);
                int[] ans2 = maxValueInSlideWindow.getMaxInSlideWindow(arr, w);
                for (int i = 0; i < ans1.length; i++) {
                    if (ans2[i] != ans1[i]) {
                        System.out.println("Error");
                        return;
                    }
                }
            }
        }
    }

}
