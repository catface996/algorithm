package com.algorithm.experiment.加倍博弈;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/6/11 10:03 上午
 */
@Slf4j
public class Solution {

    /**
     * 简单博弈,胜负概率各为50%,可用金额不受限制
     * <p>
     * 胜,赌注从1块重新开始
     * <p>
     * 负,赌注加倍
     *
     * @param times 博弈次数
     * @return 赢得的金额
     */
    public int winMoney(int times) {
        int winMoney = 0;
        int betMoney = 1;
        for (int i = 0; i < times; i++) {
            if (Double.compare(Math.random(), 0.5) <= 0) {
                winMoney -= betMoney;
                betMoney = betMoney << 1;
            } else {
                winMoney += betMoney;
                betMoney = 1;
            }
        }
        return winMoney;
    }

    public static class TestClass {

        @Test
        public void testHundred() {
            int betTimes = 100;
            Solution solution = new Solution();
            int winMoney = solution.winMoney(betTimes);
            log.info("博弈次数:{},获得奖金:{}", betTimes, winMoney);
        }

        @Test
        public void testThousand() {
            int betTimes = 1000;
            Solution solution = new Solution();
            int winMoney = solution.winMoney(betTimes);
            log.info("博弈次数:{},获得奖金:{}", betTimes, winMoney);
        }

    }
}
