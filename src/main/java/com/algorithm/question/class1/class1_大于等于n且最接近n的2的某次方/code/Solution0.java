package com.algorithm.question.class1.class1_大于等于n且最接近n的2的某次方.code;

/**
 * @author by catface
 * @date 2021/5/20 3:18 下午
 */
public class Solution0 {

    public int celling2Pow(int n) {
        int count = 0;
        while (n > 0) {
            count++;
            n = n / 2;
        }
        // pow 可以用快速幂积的方式计算,此处偷个懒
        return (int)Math.pow(2, count);
    }
}
