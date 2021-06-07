package com.algorithm.question.class2.class2_按顺序打印随机到来的信息流;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/24 10:11 上午
 */
public class Solution {

    public void printRandomArriveNumber(int[][] randomArr) {
        int head = 1;
        Map<Integer, LinkedList<Integer>> headToList = new HashMap<>();
        Map<Integer, LinkedList<Integer>> tailToList = new HashMap<>();
        for (int[] nums : randomArr) {
            for (int number : nums) {
                if (!headToList.containsKey(number + 1) && !tailToList.containsKey(number - 1)) {
                    LinkedList<Integer> list = new LinkedList<>();
                    list.addLast(number);
                    headToList.put(number, list);
                    tailToList.put(number, list);
                    head = print(headToList, tailToList, head, number);
                    continue;
                }

                // 如果到来的数字比已经存在的头部小1.并且不比已经存在的数字大1,只需要将新到来的数字作为新头部即可
                if (headToList.containsKey(number + 1) && !tailToList.containsKey(number - 1)) {
                    LinkedList<Integer> queue = headToList.get(number + 1);
                    queue.addFirst(number);
                    headToList.put(number, queue);
                    headToList.remove(number + 1);
                    head = print(headToList, tailToList, head, number);
                    continue;
                }

                // 到来的数字不比已经存在的头部小1,但是比已经存在的尾部大1
                if (!headToList.containsKey(number + 1) && tailToList.containsKey(number - 1)) {
                    LinkedList<Integer> queue = tailToList.get(number - 1);
                    queue.addLast(number);
                    tailToList.put(number, queue);
                    tailToList.remove(number - 1);
                    head = print(headToList, tailToList, head, number);
                    continue;
                }

                // number 比已经存在的头部小1,并且比已经存在的尾部大1,需要合并
                if (headToList.containsKey(number + 1) && tailToList.containsKey(number - 1)) {
                    LinkedList<Integer> rightQueue = headToList.get(number + 1);
                    LinkedList<Integer> leftQueue = tailToList.get(number - 1);
                    leftQueue.addLast(number);
                    while (!rightQueue.isEmpty()) {
                        leftQueue.addLast(rightQueue.pollFirst());
                    }
                    headToList.remove(number + 1);
                    tailToList.remove(number - 1);
                    headToList.put(leftQueue.peekFirst(), leftQueue);
                    tailToList.put(leftQueue.peekLast(), leftQueue);
                    head = print(headToList, tailToList, head, number);
                }
            }
        }
    }

    private int print(Map<Integer, LinkedList<Integer>> headToList, Map<Integer, LinkedList<Integer>> tailToList,
                      int head, int number) {
        if (headToList.containsKey(number) && head == number) {
            LinkedList<Integer> queue = headToList.get(number);
            if (!queue.isEmpty()) {
                int tail = queue.peekLast();
                while (!queue.isEmpty()) {
                    System.out.print(queue.pollFirst());
                    System.out.print(',');
                }
                System.out.println();
                headToList.remove(number);
                tailToList.remove(tail);
                head = tail + 1;
            }
        }
        return head;

    }

    public static class TestClass {
        // {{3,5,7},{1,2,4},{6,8,9},{10.12,11}}
        @Test
        public void test() {
            int[][] randomArr = {{3, 5, 7}, {1, 2, 4}, {6, 8, 9}, {10, 12, 11}};
            Solution solution = new Solution();
            solution.printRandomArriveNumber(randomArr);
        }
    }
}
