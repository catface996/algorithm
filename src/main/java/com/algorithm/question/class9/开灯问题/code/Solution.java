package com.algorithm.question.class9.开灯问题.code;

import com.algorithm.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/6/25 2:31 下午
 */
@Slf4j
public class Solution {

    public static int minPress(int[] lights) {
        // 第一个位置,可以选择按,也可以选择不按
        // 如果选择不按
        int p1 = process(lights, lights[0], lights[1], 1);
        // 如果选择按
        int p2 = process(lights, 1 - lights[0], 1 - lights[1], 1);
        if (p2 != Integer.MAX_VALUE) {
            p2++;
        }
        // 取最小值
        return Math.min(p1, p2);
    }

    public static int process(int[] lights, int lastBright, int curBright, int cur) {
        if (cur == lights.length - 1) {
            if (lastBright != curBright) {
                return Integer.MAX_VALUE;
            } else {
                if (lastBright == 0) {
                    return 1;
                }
                return 0;
            }
        }
        if (lastBright == 0) {
            int tempCount;
            // 前一个灯不亮
            if (curBright == 1) {
                // P1 |x|*|  前一个灯不亮,当前灯亮,必须按下当前位置,次数+1,否则无法点亮上一个灯,如果当前位置是最后一个,不能地啊你浪全部
                tempCount = process(lights, 0, 1 - lights[cur + 1], cur + 1);
            } else {
                // P2 |x|x|  前一个灯不亮,必须按下当前位置,次数+1,继续判断下一个位置
                tempCount = process(lights, 1, 1 - lights[cur + 1], cur + 1);
            }
            if (tempCount == Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            return tempCount + 1;
        }

        // 前一个灯亮
        // P3 |*|*|  前一个灯亮,当前灯亮,不能按下当前,继续下一个位置判断,次数+0
        if (curBright == 1) {
            return process(lights, 1, lights[cur + 1], cur + 1);
        }
        // P4 |*|x|  前一个灯亮,当前灯不亮,不能按下当前位置,如果当前位置是最后一个,不能点亮全部.
        return process(lights, 0, lights[cur + 1], cur + 1);
    }

    public static class TestClass {

        @Test
        public void test1() {
            int[] lights = {1, 0, 0, 1, 0, 1, 1, 0, 0};
            int minPressNum1 = Solution.minPress(lights);
            int minPressNum2 = Code01_LightProblem.noLoopRight(lights);
            System.out.println(minPressNum1);
            System.out.println(minPressNum2);
        }

        @Test
        public void testForce() {
            for (int i = 0; i < 100; i++) {
                int[] lights = randomLight(10);
                int[] lights1 = ArrayUtil.clone(lights);
                int[] lights2 = ArrayUtil.clone(lights);
                int minPressNum1 = Solution.minPress(lights1);
                int minPressNum2 = Code01_LightProblem.noLoopRight(lights2);
                if (minPressNum1 != minPressNum2) {
                    log.info("ans1:{},ans2:{},lights:{}", minPressNum1, minPressNum2, lights);
                }
            }
        }

        public int[] randomLight(int num) {
            int[] lights = new int[num];
            for (int i = 0; i < num; i++) {
                lights[i] = Math.random() > 0.5 ? 1 : 0;
            }
            return lights;
        }

    }

}
