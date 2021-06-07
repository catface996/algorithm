package com.algorithm.question.class7.非负数组中任意两个数字按位与的最大值.code;

/**
 * @author by catface
 * @date 2021/6/4 5:42 下午
 */
public class SolutionForce {

    public int maxAnd(int[] arr) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                max = Math.max(max, arr[i] & arr[j]);
            }
        }
        return max;
    }
}
