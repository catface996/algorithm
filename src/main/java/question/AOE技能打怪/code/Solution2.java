package question.AOE技能打怪.code;

import java.text.MessageFormat;

import org.junit.Test;
import util.ArrayUtil;

/**
 * @author by catface
 * @date 2021/5/21 2:04 下午
 */
public class Solution2 {

    private int mostLeft;
    private int target;
    private int mostRight;
    private String reg = "[{0},{1},{2},{3},{4}]";

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
        StringBuilder stringBuilder = new StringBuilder();
        for (; ; ) {
            mostLeft = mostLeftLiveMonster(hp);
            if (mostLeft == -1) {
                System.out.println(stringBuilder);
                return minAoeTimes;
            }
            int aoeTimes = useAoeOneTime(x, hp, mostLeft, range);
            minAoeTimes += aoeTimes;
            stringBuilder.append(MessageFormat.format(reg, mostLeft, target, mostRight, -aoeTimes, minAoeTimes));
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
     * <p>
     * 优化使用aoe的次数,一次计算出杀死最左侧怪兽的施法次数
     *
     * @param x        怪兽数组,有序
     * @param hp       怪兽血量数组
     * @param mostLeft 最左侧的
     * @param range    aoe技能覆盖的范围
     */
    public int useAoeOneTime(int[] x, int[] hp, int mostLeft, int range) {
        target = mostLeft;
        for (int index = mostLeft; index < x.length; index++) {
            if (x[index] - x[mostLeft] <= range) {
                target = index;
            } else {
                break;
            }
        }
        mostRight = target;
        for (int index = target; index < x.length; index++) {
            if (x[index] - x[target] <= range) {
                mostRight = index;
            } else {
                break;
            }
        }
        int aoeTimes = hp[mostLeft];
        for (int i = mostLeft; i <= mostRight; i++) {
            hp[i] -= aoeTimes;
        }
        return aoeTimes;
    }

    public static class TestClass {

        // 怪兽 [1,3,5,7,12,13,18]
        // 血量 [1,6,2,5,14,7,22]
        @Test
        public void test1() {
            Solution2 solution = new Solution2();
            int[] x = {1, 3, 5, 7, 12, 13, 18};
            int[] hp = {1, 6, 2, 5, 14, 7, 22};
            int[] hp2 = ArrayUtil.clone(hp);
            int range = 5;
            int minAoeTime = solution.minAoe(x, hp, range);
            System.out.println(minAoeTime);

            int ans2 = Code06_AOE.minAoe3(x, hp2, range);
            System.out.println(ans2);
        }
    }
}
