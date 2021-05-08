package course.manacher.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.StringUtil;

/**
 * @author by catface
 * @date 2021/5/8 11:35 上午
 */
@Slf4j
public class Solution2 {
    // 回文直径
    // 回文半径,回文半径数组
    // 预处理串,插入'#'不影响回文的判断
    // 最右回文半径以及对应的中心点

    public int manacher(String s) {
        StringBuilder sb = preProcess(s);
        int[] radius = new int[sb.length()];
        radius[0] = 1;
        int mostRight = 0;
        int cn = 0;
        int i = 1;
        int maxRadius = 0;
        while (i < sb.length()) {
            // 探索以i为中心,构成的最大回文长度
            // 判断i是否在mostRight以内
            if (i >= mostRight) {
                // i 压中mostRight或者在mostRight右侧,此时已cn为中心的回文半径无法指导i位置的探索,以i为中心向左右两侧暴力探索
                int tempRadius = 1;
                while (i + tempRadius < sb.length() && i - tempRadius >= 0 && sb.charAt(i + tempRadius) == sb.charAt(
                    i - tempRadius)) {
                    tempRadius++;
                }
                radius[i] = tempRadius;
                cn = i;
                mostRight = i + tempRadius;
                maxRadius = Math.max(maxRadius, tempRadius);
            } else {
                // 分两种情况
                int j = 2 * cn - i;
                if (i + radius[j] >= mostRight) {
                    // 情况1: i位置相对中轴cn位置的对称位置j,i+j位置的回文半径,超过mostRight,i位置的回文半径需要继续向右探索
                    // (i + j)/2 = cn ; -> j = 2 * cn -i; radius[j] = j-k+1; -> k = j-radius[j]+1;
                    //i位置右侧的回文半径,至少超过mostRight
                    int tempRadius = mostRight - i;
                    while (i + tempRadius < sb.length() && i - tempRadius >= 0 && sb.charAt(i + tempRadius) == sb
                        .charAt(i - tempRadius)) {
                        tempRadius++;
                    }
                    radius[i] = tempRadius;
                    cn = i;
                    mostRight = i + tempRadius;
                    maxRadius = Math.max(maxRadius, tempRadius);
                } else {
                    // 情况2: i位置相对中轴cn位置的对称位置j,i+j位置的回文半径,没有超过mostRight,i位置的回文半径必定小于cn位置的回文半径,且没有向右探索的必要
                    // 则以i位置为中心的回文长度必然不会超过以cn为中心的回文长度
                    radius[i] = radius[j];
                }
            }
            i++;
        }
        return (maxRadius * 2 - 1) / 2;
    }

    private StringBuilder preProcess(String s) {
        StringBuilder sb = new StringBuilder(s.length() << 1 | 1);
        sb.append('#');
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i)).append('#');
        }
        return sb;
    }

    public static class TestClass {
        @Test
        public void test1() {
            String s = "abaaba";
            Solution2 solution2 = new Solution2();
            int ans1 = solution2.manacher(s);
            Solution solution = new Solution();
            int ans2 = solution.manacher(s);
            log.info("str:{},ans1:{},ans2:{}", s, ans1, ans2);
        }

        @Test
        public void testForce() {
            Solution solution = new Solution();
            Solution2 solution2 = new Solution2();
            for (int time = 0; time < 100000; time++) {
                String s = StringUtil.randomStrOfNumber((int)(Math.random() * 100));
                int ans1 = solution.manacher(s);
                int ans2 = solution2.manacher(s);
                assert ans1 == ans2;
            }
            System.out.println("Good,you success!");
        }
    }
}
