package question.class1_AOE技能打怪.code;

import java.util.Arrays;
import java.util.LinkedList;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.ArrayUtil;

/**
 * @author by catface
 * @date 2021/5/22 4:22 下午
 */
@Slf4j
public class Solution6 {

    public int minAoeNums(int[] x, int[] hp, int range) {
        int ans = 0;
        int monsterNums = x.length;
        //当前攻击范围的左侧怪兽的下标
        int currentLeftI = 0;
        // 当前攻击范围的右侧怪兽下标
        int targetI = 0;
        //当前技能指向的怪兽下标
        int currentRightI = 0;
        //清空窗口内的怪兽,需要释放的技能次数
        int windowSum = 0;
        // hp窗口,记录某位置进入窗口后,要出窗口,作为最左侧未死亡怪兽时要承受的技能次数
        LinkedList<Integer> hpWindow = new LinkedList<>();
        // 对应hp窗口的怪兽坐标,用于获取下一个最左侧未死亡怪兽的坐标
        LinkedList<Integer> iWindow = new LinkedList<>();
        do {
            if (hpWindow.isEmpty()) {
                hpWindow.addLast(hp[currentLeftI]);
                iWindow.addLast(currentLeftI);
                windowSum += hp[currentLeftI];
                continue;
            }
            targetI = Math.max(currentLeftI, targetI) + 1;
            while (targetI < monsterNums && x[targetI] - x[currentLeftI] <= range) {
                // 假设要清空窗口中的怪物,targetI位置的怪物会被附带减伤windowSum的伤害
                int targetRestHp = hp[targetI] - windowSum;
                if (targetI > currentRightI && targetRestHp > 0) {
                    hpWindow.addLast(targetRestHp);
                    iWindow.addLast(targetI);
                    windowSum += targetRestHp;
                }
                targetI++;
            }
            targetI--;
            currentRightI = Math.max(targetI, currentRightI) + 1;
            while (currentRightI < monsterNums && x[currentRightI] - x[targetI] <= range) {
                int rightRestHp = hp[currentRightI] - windowSum;
                if (rightRestHp > 0) {
                    hpWindow.addLast(rightRestHp);
                    iWindow.addLast(currentRightI);
                    windowSum += rightRestHp;
                }
                currentRightI++;
            }
            // 快速结束,也可以去掉这个if判断
            if (currentRightI >= monsterNums) {
                ans += windowSum;
                return ans;
            }
            currentRightI--;
            // 施法范围的左侧怪兽死亡,结算需要释放的aoe次数
            if (!hpWindow.isEmpty()) {
                int leftMonsterDieHp = hpWindow.pollFirst();
                ans += leftMonsterDieHp;
                windowSum -= leftMonsterDieHp;
            }
            // 左侧怪兽下标调整,窗口为空,继续选择下一个怪兽,否则,从窗口中选择一个
            iWindow.pollFirst();
            if (iWindow.isEmpty()) {
                currentLeftI = currentRightI + 1;
            } else {
                currentLeftI = iWindow.peekFirst();
            }
        } while (currentLeftI < monsterNums);
        return ans;
    }

    public static class MyQueue {
        int[] values;
        private int first = 0;
        private int last = -1;

        public MyQueue(int size) {
            values = new int[size];
        }

        public void reset() {
            first = 0;
            last = -1;
        }

        public boolean isEmpty() {
            return first > last;
        }

        public int pollFirst() {
            return values[first++];
        }

        public int peekFirst() {
            return values[first];
        }

        public void addLast(int value) {
            values[++last] = value;
        }
    }

    public static class TestClass {
        // 怪兽 [1,3,5,7,12,13,18]
        // 血量 [1,6,2,5,14,7,22]
        @Test
        public void test1() {
            Solution4 solution4 = new Solution4();
            int[] x = {1, 3, 5, 7, 12, 13, 18};
            int[] hp = {1, 6, 2, 5, 14, 7, 22};
            int[] hp1 = ArrayUtil.clone(hp);
            int[] hp2 = ArrayUtil.clone(hp);

            int range = 5;
            int ans1 = solution4.minAoeTimes(x, hp1, range);
            System.out.println(ans1);

            Solution6 solution5 = new Solution6();
            int ans2 = solution5.minAoeNums(x, hp2, range);
            System.out.println(ans2);
        }

        @Test
        public void test3() {
            int[] x = {1574, 3230, 6406, 7490};
            int[] hp = {48, 19, 25, 8};
            int range = 7;
            Solution4 solution4 = new Solution4();
            int ans = solution4.minAoeTimes(x, hp, range);
            System.out.println(ans);

            Solution6 solution5 = new Solution6();
            int ans2 = solution5.minAoeNums(x, hp, range);
            System.out.println(ans2);
        }

        @Test
        public void testForce() {
            int X = 10000;
            int H = 50;
            int R = 10;
            int time = 50000;
            for (int N = 1000; N < 10000; N++) {
                System.out.println("test begin");
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
                        Solution6 solution5 = new Solution6();
                        long start1 = System.currentTimeMillis();
                        int ans1 = solution5.minAoeNums(x2, hp2, range);
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

        }

        // 验证数据情况最坏的情况下,线段树的优越性
        @Test
        public void testForce2() {
            long time1 = 0;
            long time3 = 0;

            for (int range = 10; range < 1000000000; range *= 10) {
                int[] x = new int[range];
                for (int i = 1; i <= x.length; i++) {
                    x[i - 1] = i;
                }
                Solution6 solution5 = new Solution6();
                for (int time = 0; time < 10; time++) {
                    // 计算并统计时间
                    long start1 = System.currentTimeMillis();
                    int ans1 = solution5.minAoeNums(x, x, range);
                    long end1 = System.currentTimeMillis();
                    time1 += end1 - start1;
                    log.info("O(N):{}", time1);
                    // 计算并统计时间
                    long start3 = System.currentTimeMillis();
                    int ans3 = Code06_AOE.minAoe3(x, x, range);
                    long end3 = System.currentTimeMillis();

                    time3 += end3 - start3;
                    if (ans1 != ans3) {
                        log.info("x;{},range:{}", x, range);
                        break;
                    }
                }
                if (time1 > 0) {
                    log.info("range:{},O(N);{},log(N):{},倍数:{}", range, time1, time3, time3 / time1);
                }
            }
        }

        // 验证数据情况最坏的情况下,线段树的优越性
        //18:29:31.896 [main] INFO question.AOE技能打怪.code.Solution5 - range:10,O(N):3
        //18:29:31.902 [main] INFO question.AOE技能打怪.code.Solution5 - range:100,O(N):4
        //18:29:31.904 [main] INFO question.AOE技能打怪.code.Solution5 - range:1000,O(N):6
        //18:29:31.919 [main] INFO question.AOE技能打怪.code.Solution5 - range:10000,O(N):21
        //18:29:31.977 [main] INFO question.AOE技能打怪.code.Solution5 - range:100000,O(N):77
        //18:29:32.407 [main] INFO question.AOE技能打怪.code.Solution5 - range:1000000,O(N):504
        //18:29:35.733 [main] INFO question.AOE技能打怪.code.Solution5 - range:10000000,O(N):3809
        //18:30:04.399 [main] INFO question.AOE技能打怪.code.Solution5 - range:100000000,O(N):32413
        @Test
        public void testON() {
            long duration = 0;

            for (int range = 10; range < 1000000000; range *= 10) {
                int[] x = new int[range];
                for (int i = 1; i <= x.length; i++) {
                    x[i - 1] = i;
                }
                Solution6 solution5 = new Solution6();
                for (int time = 0; time < 100; time++) {
                    // 计算并统计时间
                    long start1 = System.currentTimeMillis();
                    solution5.minAoeNums(x, x, range);
                    long end1 = System.currentTimeMillis();
                    duration += end1 - start1;
                }
                log.info("range:{},O(N):{}", range, duration);
            }
        }

        @Test
        public void testMyQueue() {
            MyQueue myQueue = new MyQueue(10);
            System.out.println(myQueue.isEmpty());
            myQueue.addLast(1);
            System.out.println(myQueue.isEmpty());
            System.out.println(myQueue.peekFirst());
            System.out.println(myQueue.pollFirst());
            System.out.println(myQueue.isEmpty());
        }
    }
}
