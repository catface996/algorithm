package com.algorithm.course.other;

import org.junit.Test;

/**
 * @author by catface
 * @date 2020/11/11
 */
public class FrogJump {

    private int[] tempArray;

    private int mod = 1000000007;

    public int numWays(int n) {
        tempArray = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            switch (i) {
                case 0:
                case 1:
                    tempArray[i] = 1;
                    break;
                case 2:
                    tempArray[i] = 2;
                    break;
                case 3:
                    tempArray[i] = 3;
                    break;
                default:
                    int tempResult = tempArray[i - 1] + tempArray[i - 2];
                    if (tempResult > mod) {
                        tempResult = tempResult % mod;
                    }
                    tempArray[i] = tempResult;
                    break;
            }
        }
        return tempArray[n];
    }

    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            int result = numWays(i);
            System.out.println("n=" + i + ":result=" + result);
        }
    }

}
