package question.AOE技能打怪.code;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.ArrayUtil;

/**
 * @author by catface
 * @date 2021/5/21 2:04 下午
 */
@Slf4j
public class Solution3 {

    /**
     * 最左侧被攻击到的怪兽下标不回退
     */
    private int mostLeft;
    /**
     * 下一个要攻击的怪兽下标不回退
     */
    private int nextMostLeft;

    /**
     * 被打到的每只怪兽损失1点血量。
     *
     * @param x     x有序，x[i]表示i号怪兽在x轴上的位置；
     * @param hp    hp[i]表示i号怪兽的血量
     * @param range 表示如果法师释放技能的范围长度
     * @return 返回要把所有怪兽血量清空，至少需要释放多少次AOE技能
     */
    public int minAoe(int[] x, int[] hp, int range) {
        int minAoeTimes = 0;
        mostLeft = mostLeftLiveMonster(hp, 0);
        for (; ; ) {
            minAoeTimes += killMostLeftMonsterAoeTimes(x, hp, range);
            if (nextMostLeft == -1) {
                return minAoeTimes;
            }
            mostLeft = nextMostLeft;
        }
    }

    /**
     * 找到所有存活的怪兽中,最左侧的怪兽
     *
     * @param hp 血量
     * @return 最左侧怪兽的下标
     */
    public int mostLeftLiveMonster(int[] hp, int start) {
        for (int i = start; i < hp.length; i++) {
            if (hp[i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 以第i号怪兽为AOE技能的最左侧,释放AOE技能一次
     * <p>
     * 优化使用aoe的次数,一次计算出杀死最左侧怪兽的施法次数
     *
     * @param x     怪兽数组,有序
     * @param hp    怪兽血量数组
     * @param range aoe技能覆盖的范围
     * @return 最左侧的怪兽被杀死, 需要的攻击次数
     */
    public int killMostLeftMonsterAoeTimes(int[] x, int[] hp, int range) {

        // 本次清理掉最左侧的怪兽需要释放aoe的次数
        int aoeTime = hp[mostLeft];

        // 是否在技能范围内发现下一只要攻击的怪兽
        boolean findNext = false;

        // 首先要寻找释放aoe时的指向怪兽的位置
        int target = mostLeft;

        for (int index = mostLeft; index < x.length; index++) {
            if (x[index] - x[mostLeft] <= range) {
                target = index;
                hp[index] -= aoeTime;
                // 寻找技能指向怪兽的过程中,是否发现下一次要攻击的怪兽
                if (!findNext && hp[index] > 0) {
                    nextMostLeft = index;
                    findNext = true;
                }
            } else {
                break;
            }
        }

        // 寻找技能能覆盖到的最右侧怪兽
        int mostRight = target;
        for (int index = target + 1; index < x.length; index++) {
            if (x[index] - x[target] <= range) {
                mostRight = index;
                hp[index] -= aoeTime;
                if (!findNext && hp[index] > 0) {
                    nextMostLeft = index;
                    findNext = true;
                }
            } else {
                break;
            }
        }
        // 如果在攻击范围内发现了下一只要攻击的怪兽,,怪兽的血量要相应的减少
        // 如果未在攻击范围内发现下一只怪兽,说明下一只怪兽在攻击范围的右侧
        if (!findNext) {
            nextMostLeft = mostLeftLiveMonster(hp, mostRight + 1);
        }

        return aoeTime;
    }

    public static class TestClass {

        // 怪兽 [1,3,5,7,12,13,18]
        // 血量 [1,6,2,5,14,7,22]
        @Test
        public void test1() {
            Solution3 solution = new Solution3();
            int[] x = {1, 3, 5, 7, 12, 13, 18};
            int[] hp = {1, 6, 2, 5, 14, 7, 22};
            int[] hp2 = ArrayUtil.clone(hp);
            int range = 5;
            int minAoeTime = solution.minAoe(x, hp, range);
            System.out.println(minAoeTime);

            int ans2 = Code06_AOE.minAoe2(x, hp2, range);
            System.out.println(ans2);
        }

        @Test
        public void test2() {
            int[] x = {8913};
            int[] hp = {42};
            int range = 6;
            Solution3 solution3 = new Solution3();
            int ans = solution3.minAoe(x, hp, range);
            System.out.println(ans);
        }

        @Test
        public void test3() {
            // 375,369
            int[] x = {631, 715, 743, 1108, 1336, 2520, 3768, 3839, 4476, 5043, 5771, 5927, 8481, 8536, 9564, 9568,
                9572};
            int[] hp = {19, 25, 17, 19, 28, 2, 16, 45, 40, 23, 36, 17, 23, 24, 34, 35, 7};
            int range = 5;
            Solution3 solution3 = new Solution3();
            int ans = solution3.minAoe(x, hp, range);
            System.out.println(ans);
        }

        @Test
        public void testForce() {
            int N = 5000;
            int X = 10000;
            int H = 50;
            int R = 10;
            int time = 50000;
            System.out.println("test begin");
            Solution3 solution3 = new Solution3();
            long time1 = 0;
            long time2 = 0;

            for (int i = 0; i < time; i++) {
                // 生成测试数据
                int len = (int)(Math.random() * N) + 1;
                int[] x = Code06_AOE.randomArray(len, X);
                Arrays.sort(x);
                int[] hp = Code06_AOE.randomArray(len, H);
                int range = (int)(Math.random() * R) + 1;

                try {
                    // 复制测试数据
                    int[] x2 = Code06_AOE.copyArray(x);
                    int[] hp2 = Code06_AOE.copyArray(hp);
                    // 计算并统计时间
                    long start1 = System.currentTimeMillis();
                    int ans1 = solution3.minAoe(x2, hp2, range);
                    long end1 = System.currentTimeMillis();
                    time1 += end1 - start1;

                    //复制测试数据
                    int[] x3 = Code06_AOE.copyArray(x);
                    int[] hp3 = Code06_AOE.copyArray(hp);
                    // 计算并统计时间
                    long start3 = System.currentTimeMillis();
                    int ans3 = Code06_AOE.minAoe3(x3, hp3, range);
                    long end3 = System.currentTimeMillis();
                    time2 += end3 - start3;

                    if (ans1 != ans3) {
                        System.out.println("Oops!");
                        System.out.println(ans1 + "," + ans3);
                        log.info("x;{},hp:{},range:{}", x, hp, range);
                    }
                } catch (Exception e) {
                    log.info("x;{},hp:{},range:{}", x, hp, range);
                }
            }
            System.out.println("test end");
            System.out.println(time1);
            System.out.println(time2);
        }

        // 验证数据情况最坏的情况下,线段树的优越性
        @Test
        public void testForce2() {
            long time1 = 0;
            long time3 = 0;

            Solution3 solution3 = new Solution3();
            int[] x = new int[10000];
            int range = 1000;
            for (int i = 1; i <= x.length; i++) {
                x[i - 1] = i;
            }

            for (int time = 0; time < 1000; time++) {
                int[] hp = ArrayUtil.clone(x);
                int[] hp2 = ArrayUtil.clone(x);

                // 计算并统计时间
                long start1 = System.currentTimeMillis();
                int ans1 = solution3.minAoe(x, hp, range);
                long end1 = System.currentTimeMillis();
                time1 += end1 - start1;

                // 计算并统计时间
                long start3 = System.currentTimeMillis();
                int ans3 = Code06_AOE.minAoe3(x, hp2, range);
                long end3 = System.currentTimeMillis();
                time3 += end3 - start3;
                if (ans1 != ans3) {
                    log.info("x;{},range:{}", x, range);
                    break;
                }
            }
            System.out.println(time1);
            System.out.println(time3);
        }
    }
}
