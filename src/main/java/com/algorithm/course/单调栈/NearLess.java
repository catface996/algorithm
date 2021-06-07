package com.algorithm.course.单调栈;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/26 9:34 上午
 */
@Slf4j
public class NearLess {

    public int[][] force(int[] arr) {
        int[][] ans = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            int leftLess = -1;
            int rightLess = -1;
            for (int left = i - 1; left >= 0; left--) {
                if (arr[left] < arr[i]) {
                    leftLess = left;
                    break;
                }
            }
            for (int right = i + 1; right < arr.length; right++) {
                if (arr[right] < arr[i]) {
                    rightLess = right;
                    break;
                }
            }
            ans[i][0] = leftLess;
            ans[i][1] = rightLess;
        }
        return ans;
    }

    public int[][] getNearLess(int[] arr) {

        int[][] ans = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {

            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {

                List<Integer> popNumbers = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (Integer popNumber : popNumbers) {
                    ans[popNumber][0] = leftLessIndex;
                    ans[popNumber][1] = i;
                }
            }

            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(i);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }

        while (!stack.isEmpty()) {
            List<Integer> popIs = stack.pop();
            int leftIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (Integer popi : popIs) {
                ans[popi][0] = leftIndex;
                ans[popi][1] = -1;
            }
        }
        return ans;

    }

    public int[][] nearLess2(int[] arr) {
        // 待返回的结果,ans[i][0] 小于i位置值,且位于i左侧的最靠近i的下标,ans[i][1] 小于i位置值,且位于i右侧,最靠近i位置的下标
        int[][] ans = new int[arr.length][2];
        // List 是相同value的不同下标,为了解决值相同,下标不同的问题
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                // 如果要入栈的数字比栈顶的小,弹出栈顶,此时,比弹出栈顶的数字小且在数组左侧的第一个数字是将要进栈的数字,比要进展的数字小且在数组左侧的数字是弹出后的栈顶
                List<Integer> biggerNumberIndexList = stack.pop();
                for (int index : biggerNumberIndexList) {
                    int rightLess = arr[i];
                    int leftLess;
                    if (stack.isEmpty()) {
                        leftLess = -1;
                    } else {
                        // list中最后一个加入的,是在数组中左侧,最靠近弹出下标的下标值,因为相同的value共用同一个下标list
                        leftLess = arr[stack.peek().get(stack.peek().size() - 1)];
                    }
                    ans[index][0] = leftLess;
                    ans[index][1] = rightLess;
                }
            }
            // 入栈时,判断是否与栈顶元素大小相同,如果相同,合并到栈顶的下标列表,否则作为新列表进栈
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                // 合并栈顶
                stack.peek().add(i);
            } else {
                List<Integer> indexList = new ArrayList<>();
                indexList.add(i);
                stack.push(indexList);
            }
        }

        // 循环结束后,栈中的所有下标,右侧均无小于当前数的下标,统一为-1
        while (!stack.isEmpty()) {
            List<Integer> biggerNumberIndexList = stack.pop();
            for (int index : biggerNumberIndexList) {
                int leftLess;
                if (stack.isEmpty()) {
                    leftLess = -1;
                } else {
                    // list中最后一个加入的,是在数组中左侧,最靠近弹出下标的下标值,因为相同的value共用同一个下标list,所以与stack.peek().get(0)有相同的效果
                    leftLess = arr[stack.peek().get(stack.peek().size() - 1)];
                }
                ans[index][0] = leftLess;
                ans[index][1] = -1;
            }
        }
        return ans;
    }

    public static class TestClass {

        // arr = [ 3, 1, 2, 3]
        //         0  1  2  3
        //  [
        //     0 : [-1,  1]
        //     1 : [-1, -1]
        //     2 : [ 1, -1]
        //     3 : [ 2, -1]
        //  ]
        @Test
        public void test() {
            int[] arr = new int[] {3, 1, 2, 3};
            NearLess nearLess = new NearLess();
            int[][] ans = nearLess.getNearLess(arr);
            System.out.println("x");
        }

        @Test
        public void test2() {
            int[] arr = new int[] {3, 1, 2, 3};
            NearLess nearLess = new NearLess();
            int[][] ans = nearLess.nearLess2(arr);
            log.info("arr:{},ans:{}", arr, ans);
        }
    }
}
