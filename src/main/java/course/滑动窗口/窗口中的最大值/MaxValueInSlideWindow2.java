package course.滑动窗口.窗口中的最大值;

import java.util.LinkedList;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/22 9:07 下午
 */
@Slf4j
public class MaxValueInSlideWindow2 {

    public int[] getMaxArr(int[] arr, int w) {
        if (arr == null || arr.length < w || w <= 0) {
            return null;
        }

        LinkedList<Integer> maxValueIndexQueue = new LinkedList<>();
        int[] ans = new int[arr.length - w + 1];
        for (int right = 0, left = -w, ansIndex = -w + 1; right < arr.length; right++, left++, ansIndex++) {
            while (!maxValueIndexQueue.isEmpty() && arr[maxValueIndexQueue.peekLast()] <= arr[right]) {
                maxValueIndexQueue.pollLast();
            }
            maxValueIndexQueue.addLast(right);
            if (left == maxValueIndexQueue.peekFirst()) {
                maxValueIndexQueue.pollFirst();
            }
            if (ansIndex >= 0) {
                ans[ansIndex] = arr[maxValueIndexQueue.peekFirst()];
            }
        }
        return ans;
    }

    @Test
    public void test2() {
        int[] arr = new int[] {4, 3, 5, 4, 3, 3, 6, 7};
        int w = 3;
        MaxValueInSlideWindow2 maxValueInSlideWindow2 = new MaxValueInSlideWindow2();
        int[] ans = maxValueInSlideWindow2.getMaxArr(arr, w);
        log.info("arr:{},ans:{}", arr, ans);
    }
}
