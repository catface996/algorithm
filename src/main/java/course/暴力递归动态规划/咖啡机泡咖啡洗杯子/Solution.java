package course.暴力递归动态规划.咖啡机泡咖啡洗杯子;

import java.util.Comparator;
import java.util.PriorityQueue;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/13 5:56 下午
 */
@Slf4j
public class Solution {

    // 原始题目描述:
    // 给定一个数组arr，arr[i]代表第i号咖啡机泡一杯咖啡的时间
    //给定一个正数N，表示N个人等着咖啡机泡咖啡，每台咖啡机只能轮流泡咖啡
    //只有一台咖啡机，一次只能洗一个杯子，时间耗费a，洗完才能洗下一杯
    //每个咖啡杯也可以自己挥发干净，时间耗费b，咖啡杯可以并行挥发
    //假设所有人拿到咖啡之后立刻喝干净，
    //返回从开始等到所有咖啡机变干净的最短时间
    //三个参数：int[] arr、int N，int a、int b

    // 题目
    // 数组arr代表每一个咖啡机冲一杯咖啡的时间，每个咖啡机只能串行的制造咖啡。
    // 现在有n个人需要喝咖啡，只能用咖啡机来制造咖啡。
    // 认为每个人喝咖啡的时间非常短，冲好的时间即是喝完的时间。
    // 每个人喝完之后咖啡杯可以选择洗或者自然挥发干净，只有一台洗咖啡杯的机器，只能串行的洗咖啡杯。
    // 洗杯子的机器洗完一个杯子时间为a，任何一个杯子自然挥发干净的时间为b。
    // 四个参数：arr, n, a, b
    // 假设时间点从0开始，返回所有人喝完咖啡并洗完咖啡杯的全部过程结束后，至少来到什么时间点。

    private static final PriorityQueue<CoffeeMachine> MACHINES = new PriorityQueue<>(new Comparator<CoffeeMachine>() {
        @Override
        public int compare(CoffeeMachine o1, CoffeeMachine o2) {
            return (o1.availableTime + o1.workTime) - (o2.availableTime + o2.workTime);
        }
    });

    /**
     * 一个杯子的时间
     */
    private int washPeriod;
    /**
     * 蒸发一个杯子的时间
     */
    private int evaporatePeriod;

    /**
     * 计算咖啡杯全部变干净的最早时间
     *
     * @param coffeeMachine   咖啡机
     * @param peopleNum       排队人数
     * @param washPeriod      洗一个杯子的时间
     * @param evaporatePeriod 蒸发一个杯子的时间
     * @return 咖啡杯全部变干净的最早时间
     */
    public int calculateEarliestTime(int[] coffeeMachine, int peopleNum, int washPeriod, int evaporatePeriod) {
        // 初始化咖啡机
        initCoffeeMachine(coffeeMachine);
        // 初始化洗碗机
        intiWashMachine(washPeriod, evaporatePeriod);
        // 获取所有人喝到咖啡(即喝完咖啡)的最优时间列表
        int[] drinkOffArr = lineUp(coffeeMachine, peopleNum);
        // 计算所有杯子都变干净的最早时间
        return process(drinkOffArr, 0, 0);
    }

    /**
     * 迭代洗杯子
     *
     * @param drinkOffArr   咖啡喝完的时间点列表
     * @param next          当前要处理的咖啡杯
     * @param startWashTime 洗碗机可以开始工作的时间
     * @return 所有杯子全部变干净的最早时间
     */
    private int process(int[] drinkOffArr, int next, int startWashTime) {
        // 所有的杯子都已经处理,洗碗机下次可用的时间,即之前的杯子全部变干净的最早时间
        if (next >= drinkOffArr.length) {
            return startWashTime;
        }

        // 针对当前要处理的杯子,有两种方案进行处理.
        // 1.使用洗碗机洗杯子,那么杯子变干净的最早时间为,洗碗机可以开始工作的时间+洗一个杯子所用的时间 Max(drinkOffTime,startWashTime)+washPeriod
        int washOffTime = Math.max(drinkOffArr[next], startWashTime) + washPeriod;
        int leftTimeAfterWash = process(drinkOffArr, next + 1, washOffTime);
        int earliestTime1 = Math.max(washOffTime, leftTimeAfterWash);
        // 2.选择让当前的杯子挥发变干净,那么当前杯子变干净的时间为挥发时间 evaporateTime,那么最早变干净的时间为 drinkOffTime+evaporateTime
        int evaporateOffTime = drinkOffArr[next] + evaporatePeriod;
        int leftTimeAfterEvaporate = process(drinkOffArr, next + 1, startWashTime);
        int earliestTime2 = Math.max(evaporateOffTime, leftTimeAfterEvaporate);
        // 做完选择后,取当前选择和选择后的最大值作为结束时间
        // 两种方案中,选择最早结束的时间作为最优时间
        return Math.min(earliestTime1, earliestTime2);
    }

    /**
     * 排队获取所有人最优的喝到咖啡的时间列表(喝到咖啡就认为已经喝完)
     *
     * @param coffeeMachine 咖啡机列表
     * @param peopleNum     排毒喝咖啡的人数
     * @return 所有人可以喝到咖啡的最早时间列表
     */
    private int[] lineUp(int[] coffeeMachine, int peopleNum) {
        // 初始化咖啡机
        int[] drinkOffArr = new int[peopleNum];
        // 喝咖啡的人排毒取咖啡,取到咖啡后,可以认为是瞬间喝完,取到咖啡的时间就是喝完的时间
        for (int i = 0; i < peopleNum; i++) {
            CoffeeMachine machine = MACHINES.poll();
            if (machine != null) {
                machine.availableTime += machine.workTime;
                drinkOffArr[i] = machine.availableTime;
                MACHINES.add(machine);
            }
        }
        return drinkOffArr;
    }

    /**
     * 初始化咖啡机
     *
     * @param coffeeMachine 咖啡机泡一杯咖啡的时长列表
     */
    private void initCoffeeMachine(int[] coffeeMachine) {
        for (int j : coffeeMachine) {
            CoffeeMachine machine = new CoffeeMachine(0, j);
            MACHINES.add(machine);
        }
    }

    /**
     * 初始化洗碗机
     *
     * @param washPeriod      洗一个杯子的时间
     * @param evaporatePeriod 蒸发一个杯子的时间
     */
    private void intiWashMachine(int washPeriod, int evaporatePeriod) {
        this.washPeriod = washPeriod;
        this.evaporatePeriod = evaporatePeriod;
    }

    public static class CoffeeMachine {
        /**
         * 冲泡一杯咖啡需要的时长
         */
        private final int workTime;
        /**
         * 下次可用时间
         */
        private int availableTime;

        public CoffeeMachine(int availableTime, int workTime) {
            this.availableTime = availableTime;
            this.workTime = workTime;
        }
    }

    public static class TestClass {
        @Test
        public void test1() {
            int[] machineArr = new int[] {3, 5, 8, 11};
            int peopleNum = 10;
            int washPeriod = 3;
            int evaporatePeriod = 25;
            Solution solution = new Solution();
            int earliestTime = solution.calculateEarliestTime(machineArr, peopleNum, washPeriod, evaporatePeriod);
            System.out.println("所有杯子变干净的最早时间:" + earliestTime);
        }
    }

}
