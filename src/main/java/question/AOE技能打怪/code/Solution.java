package question.AOE技能打怪.code;

import org.junit.Test;
import util.ArrayUtil;

/**
 * @author by catface
 * @date 2021/5/21 2:04 下午
 */
public class Solution {

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
        int mostLeft;
        for (; ; ) {
            mostLeft = mostLeftLiveMonster(hp);
            if (mostLeft == -1) {
                return minAoeTimes;
            }
            useAoeOneTime(x, hp, mostLeft, range);
            minAoeTimes++;
        }
    }

    /**
     * 找到所有存活的怪兽中,最左侧的怪兽
     *
     * @param hp 血量
     * @return 最左侧怪兽的下标
     */
    public int mostLeftLiveMonster(int[] hp) {
        for (int i = 0; i < hp.length; i++) {
            if (hp[i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 以第i号怪兽为AOE技能的最左侧,释放AOE技能一次
     *
     * @param x        怪兽数组,有序
     * @param hp       怪兽血量数组
     * @param mostLeft 最左侧的
     * @param range    aoe技能覆盖的范围
     */
    public void useAoeOneTime(int[] x, int[] hp, int mostLeft, int range) {
        int target = mostLeft;
        for (int index = mostLeft; index < x.length; index++) {
            if (x[index] - x[mostLeft] <= range) {
                target = index;
            } else {
                break;
            }
        }
        int mostRight = target;
        for (int index = target; index < x.length; index++) {
            if (x[index] - x[target] <= range) {
                mostRight = index;
            } else {
                break;
            }
        }
        for (int i = mostLeft; i <= mostRight; i++) {
            hp[i]--;
        }
    }

    public static class TestClass {

        // 怪兽 [1,3,5,7,12,13,18]
        // 血量 [1,6,2,5,14,7,22]
        @Test
        public void test1() {
            Solution solution = new Solution();
            int[] x = {1, 3, 5, 7, 12, 13, 18};
            int[] hp = {1, 6, 2, 5, 14, 7, 22};
            int[] hp2 = ArrayUtil.clone(hp);
            int range = 5;
            int minAoeTime = solution.minAoe(x, hp, range);
            System.out.println(minAoeTime);

            int ans2 = Code06_AOE.minAoe2(x, hp2, range);
            System.out.println(ans2);
        }
    }
}
