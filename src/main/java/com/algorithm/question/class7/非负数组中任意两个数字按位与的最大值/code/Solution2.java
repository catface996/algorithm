package com.algorithm.question.class7.非负数组中任意两个数字按位与的最大值.code;

import com.algorithm.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/6/7 10:12 上午
 */
@Slf4j
public class Solution2 {

    public int maxAndValue(int[] arr) {
        int ans = 0;
        int more = arr.length;
        int less;
        int lastMore = arr.length;
        int i;
        for (int bitPosition = 30; bitPosition >= 0; bitPosition--) {
            less = -1;
            i = 0;
            while (i < more) {
                if ((arr[i] >> bitPosition & 1) == 1) {
                    swap(arr, ++less, i++);
                } else {
                    swap(arr, --more, i);
                }
            }
            if (less == 1) {
                // 第bitPosition位上为1的数字只有两个,最大值一定是这两个数字按位与
                return arr[0] & arr[1];
            } else if (less < 1) {
                // 第bitPosition位上为1的数字少于两个,最大值肯定出现在后续位的按位与上
                // 荷兰国旗分组的右边界始终为上一次有效右边界
                more = lastMore;
            } else {
                // 在上次有效的右边界基础上,重新计算新的有效右边界
                lastMore = less + 1;
                more = lastMore;
            }
        }
        if (lastMore > 1) {
            return arr[0] & arr[1];
        }
        return ans;
    }

    private void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static class TestClass {
        @Test
        public void test1() {
            Solution2 solution2 = new Solution2();
            SolutionForce solutionForce = new SolutionForce();
            int[] arr = {2, 7, 5, 13, 8, 10, 9, 17, 4, 5};
            int ans = solution2.maxAndValue(arr);
            int ans3 = solutionForce.maxAnd(arr);
            int ans2 = Code01_MaxAndValue.maxAndValue2(arr);
            log.info("ans:{},ans2:{},ans3:{},,arr:{}", ans, ans2, ans3, arr);
        }

        @Test
        public void testForce2() {
            Solution2 solution2 = new Solution2();
            for (int i = 0; i < 10000; i++) {
                int[] arr = ArrayUtil.randomIntArray(10, 1, 20);
                int[] arr2 = ArrayUtil.clone(arr);
                int ans = solution2.maxAndValue(arr);
                int ans2 = Code01_MaxAndValue.maxAndValue2(arr2);
                if (ans != ans2) {
                    log.info("first ans:{},ans2:{},arr:{}", ans, ans2, arr);
                }
            }
        }
    }
}
