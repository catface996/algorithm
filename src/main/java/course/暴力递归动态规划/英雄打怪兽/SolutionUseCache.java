package course.暴力递归动态规划.英雄打怪兽;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.ArrayUtil;

/**
 * @author by catface
 * @date 2021/4/15 4:26 下午
 */
@Slf4j
public class SolutionUseCache {

    //给定3个参数，N，M，K
    //怪兽有N滴血，等着英雄来砍自己
    //英雄每一次打击，都会让怪兽流失[0~M]的血量
    //到底流失多少？每一次在[0~M]上等概率的获得一个值
    //求K次打击之后，英雄把怪兽砍死的概率

    /**
     * 计算在指定次的攻击次数内,击杀怪兽的概率
     *
     * @param monsterHp   怪兽的血量
     * @param maxMinusHp  每次攻击减少的最大血量
     * @param attackTimes 指定的攻击次数
     * @return 击杀怪兽的成功率
     */
    public double killMonster(int monsterHp, int maxMinusHp, int attackTimes) {
        if (monsterHp <= 0 || maxMinusHp <= 0 || attackTimes <= 0) {
            return 0;
        }
        int[][] cache = ArrayUtil.initCache(monsterHp + 1, attackTimes + 1, -1);
        long successNum = process(maxMinusHp, monsterHp, attackTimes, cache);
        long allNum = (long)Math.pow(maxMinusHp + 1, attackTimes);
        return 1.0 * successNum / allNum;
    }

    /**
     * 怪兽在当前剩余血量的前提下,指定本次攻击的血量,通过剩余次攻击,击杀怪兽的方案数
     *
     * @param leftMonsterHp   怪兽剩余的血量
     * @param leftAttackTimes 剩余的攻击次数
     * @return 杀死怪兽的方案数
     */
    public long process(int maxMinusHp, int leftMonsterHp, int leftAttackTimes, int[][] cache) {
        // 怪兽剩余血量小于等于0,还有剩余次数,方案数为 (最大掉血+1)的leftAttackTimes次方
        if (leftAttackTimes == 0) {
            return leftMonsterHp <= 0 ? 1 : 0;
        }
        // 剩余攻击次数小于等于0,此时仍有血量剩余,无效的攻击方案
        if (leftMonsterHp <= 0) {
            return (long)Math.pow(maxMinusHp + 1, leftAttackTimes);
        }
        // 检查是否命中缓存,命中则返回
        long ans = cache[leftMonsterHp][leftAttackTimes];
        if (ans != -1) {
            return ans;
        }
        int ways = 0;
        for (int currentMinusHp = 0; currentMinusHp <= maxMinusHp; currentMinusHp++) {
            ways += process(maxMinusHp, leftMonsterHp - currentMinusHp, leftAttackTimes - 1, cache);
        }
        cache[leftMonsterHp][leftAttackTimes] = ways;
        return ways;
    }

    public static class TestClass {
        @Test
        public void test1() {
            int monsterHp = 0;
            int maxMinusHp = 9;
            int attackTimes = 9;
            SolutionUseCache solution = new SolutionUseCache();
            double killRate = solution.killMonster(monsterHp, maxMinusHp, attackTimes);
            double killRate2 = Code01_KillMonster.right(monsterHp, maxMinusHp, attackTimes);
            System.out.println("击杀怪兽的概率1:" + killRate);
            System.out.println("击杀怪兽的概率2:" + killRate2);
            assert Double.compare(killRate, killRate2) == 0;
        }

        @Test
        public void test2() {
            int NMax = 10;
            int MMax = 10;
            int KMax = 10;
            for (int i = 0; i < 100; i++) {
                int monsterHp = (int)(Math.random() * NMax);
                int maxMinusHp = (int)(Math.random() * MMax);
                int attackTimes = (int)(Math.random() * KMax);
                SolutionUseCache solution = new SolutionUseCache();
                double killRate = solution.killMonster(monsterHp, maxMinusHp, attackTimes);
                double killRate2 = Code01_KillMonster.dp1(monsterHp, maxMinusHp, attackTimes);
                if (Double.compare(killRate, killRate2) != 0) {
                    log.info("monsterHp:{},maxMinusHp:{},attackTimes:{}", monsterHp, maxMinusHp, attackTimes);
                    System.out.println("击杀怪兽的概率1:" + killRate);
                    System.out.println("击杀怪兽的概率2:" + killRate2);
                }
            }
            System.out.println("Good,you success!");
        }
    }
}
