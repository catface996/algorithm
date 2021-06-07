package com.algorithm.question.class1.class1_大于等于n且最接近n的2的某次方.code;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/20 3:10 下午
 */
@Slf4j
public class Solution1 {

    public int celling2Pow(int n) {
        while (n != (n & -n)) {
            n += n & -n;
        }
        return n;
    }

    public static class TestClass {
        @Test
        public void test1() {
            Solution0 solution0 = new Solution0();
            Solution1 solution1 = new Solution1();
            Solution2 solution2 = new Solution2();
            for (int i = 0; i < 10000; i++) {
                int n = (int)(Math.random() * Integer.MAX_VALUE / 2);
                int ans0 = solution0.celling2Pow(n);
                int ans1 = solution1.celling2Pow(n);
                int ans2 = solution2.celling2Pow(n);
                if (ans0 != ans1 || ans1 != ans2) {
                    log.info("n:{},ans0:{},ans1:{},ans2:{}", n, ans0, ans1, ans2);
                    assert false;
                }
            }
            log.info("Good,you success.");
        }
    }
}
