package com.algorithm.course.单调栈;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.algorithm.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/26 2:51 下午
 */
@Slf4j
public class NearLess2 {

    public int[][] getNearLess(int[] arr) {
        // ans[i][0] i位置的左侧子数组中,最后一个小于i位置值的下标(在左侧最靠近i位置且小于i位置值的下标)
        // ans[i][1] i位置的右侧子数组中,第一个小于i位置值的下标(在右侧最靠近i位置且小于i位置值的下标)
        int[][] ans = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek().get(0)]) {
                // 待进栈的数字小于栈顶数字,栈顶弹出,弹出为x,ans[x][1] = i; ans[x][0] = stack.peek().get(0);
                List<Integer> biggerNumberIndexList = stack.pop();
                for (int biggerIndex : biggerNumberIndexList) {
                    int rightLessIndex = i;
                    int leftLessIndex;
                    if (stack.isEmpty()) {
                        leftLessIndex = -1;
                    } else {
                        // 最后一个合并到list中的,是最靠近右侧的
                        int mostRightInLeftIndex = stack.peek().size() - 1;
                        leftLessIndex = stack.peek().get(mostRightInLeftIndex);
                    }
                    ans[biggerIndex][0] = leftLessIndex;
                    ans[biggerIndex][1] = rightLessIndex;
                }
            }
            // 待入栈的数字大于等于栈顶数字时,判断是否与栈顶数字相等,如果相等,合并到栈顶的list,否则,加入新的list入栈
            if (!stack.isEmpty() && arr[i] == arr[stack.peek().get(0)]) {
                stack.peek().add(i);
            } else {
                List<Integer> indexList = new ArrayList<>();
                indexList.add(i);
                stack.push(indexList);
            }
        }

        // 遍历结束后,对仍旧在栈中的数据进行处理
        // 此时栈中元素在数组右侧,无小于自身的值,统一设置为-1,在左侧且小于自身的下标被自身压在栈中
        while (!stack.isEmpty()) {
            List<Integer> biggerNumberIndexList = stack.pop();
            for (int bigNumberIndex : biggerNumberIndexList) {
                int leftLessIndex;
                if (stack.isEmpty()) {
                    leftLessIndex = -1;
                } else {
                    int mostRightInLeft = stack.peek().size() - 1;
                    leftLessIndex = stack.peek().get(mostRightInLeft);
                }
                ans[bigNumberIndex][0] = leftLessIndex;
                ans[bigNumberIndex][1] = -1;
            }
        }
        return ans;
    }

    @Test
    public void test2() {
        int[] arr = ArrayUtil.randomIntArray(6,1,10);
        NearLess2 nearLess2 = new NearLess2();
        int[][] ans = nearLess2.getNearLess(arr);
        log.info("arr:{},ans:{}", arr, ans);
    }

    @Test
    public void test3() {
        for (int times = 0; times < 10000; times++) {
            for (int size = 10; size < 100; size++) {
                int[] arr = ArrayUtil.randomIntArray(size, 1, 100);
                NearLess nearLess = new NearLess();
                NearLess2 nearLess2 = new NearLess2();
                int[][] ans1 = nearLess.force(arr);
                int[][] ans2 = nearLess2.getNearLess(arr);
                for (int i = 0; i < ans1.length; i++) {
                    if (ans1[i][0] != ans2[i][0] || ans1[i][1] != ans2[i][1]) {
                        log.info("arr:{},ans1:{},ans2:{}", arr, ans1, ans2);
                        assert false;
                    }
                }
            }
        }
        System.out.println("Good,you success!");
    }
}
