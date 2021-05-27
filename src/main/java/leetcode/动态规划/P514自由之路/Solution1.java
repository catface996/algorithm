package leetcode.动态规划.P514自由之路;
//电子游戏“辐射4”中，任务“通向自由”要求玩家到达名为“Freedom Trail Ring”的金属表盘，并使用表盘拼写特定关键词才能开门。
//
// 给定一个字符串 ring，表示刻在外环上的编码；给定另一个字符串 key，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数。
//
// 最初，ring 的第一个字符与12:00方向对齐。您需要顺时针或逆时针旋转 ring 以使 key 的一个字符在 12:00 方向对齐，然后按下中心按钮，
//以此逐个拼写完 key 中的所有字符。
//
// 旋转 ring 拼出 key 字符 key[i] 的阶段中：
//
//
// 您可以将 ring 顺时针或逆时针旋转一个位置，计为1步。旋转的最终目的是将字符串 ring 的一个字符与 12:00 方向对齐，并且这个字符必须等于字符
// key[i] 。
// 如果字符 key[i] 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 1 步。按完之后，您可以开始拼写 key 的下一个字符（下一阶段
//）, 直至完成所有拼写。
//
//
// 示例：
//
//
//
//
//
//
//
//输入: ring = "godding", key = "gd"
//输出: 4
//解释:
// 对于 key 的第一个字符 'g'，已经在正确的位置, 我们只需要1步来拼写这个字符。
// 对于 key 的第二个字符 'd'，我们需要逆时针旋转 ring "godding" 2步使它变成 "ddinggo"。
// 当然, 我们还需要1步进行拼写。
// 因此最终的输出是 4。
//
//
// 提示：
//
//
// ring 和 key 的字符串长度取值范围均为 1 至 100；
// 两个字符串中都只有小写字符，并且均可能存在重复字符；
// 字符串 key 一定可以由字符串 ring 旋转拼出。
//
// Related Topics 深度优先搜索 分治算法 动态规划
// 👍 203 👎 0

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
@Slf4j
public class Solution1 {

    // 2:41 下午	info
    //				运行失败:
    //				Time Limit Exceeded
    //				测试用例:"xrrakuulnczywjs"
    //				"jrlucwzakzussrlckyjjsuwkuarnaluxnyzcnrxxwruyr"
    //				stdout:


    String ring;
    int ringSize;
    String key;
    Map<Character, List<Integer>> charOnRingPositionMap;

    public int findRotateSteps(String ring, String key) {
        this.ring = ring;
        this.ringSize = ring.length();
        this.key = key;
        charOnRingPositionMap = buildCharPositionMap(ring);
        return process(0, 0);
    }

    public Map<Character, List<Integer>> buildCharPositionMap(String ring) {
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < ring.length(); i++) {
            char c = ring.charAt(i);
            List<Integer> positions = map.computeIfAbsent(c, k -> new ArrayList<>());
            positions.add(i);
        }
        return map;
    }

    /**
     * ring在当前位置,搞定key由当前位置到最末未需要的最少代价
     *
     * @param currentIndexOnRing 目前环上的下标位置
     * @param currentIndexOnKey  目前要匹配的key上的字符下标,尚未匹配
     * @return 最小代价
     */
    private int process(int currentIndexOnRing, int currentIndexOnKey) {
        if (currentIndexOnKey >= key.length()) {
            // key已经全部打印完成
            return 0;
        }
        char targetChar = key.charAt(currentIndexOnKey);
        List<Integer> targetCharOnRingIndexList = charOnRingPositionMap.get(targetChar);
        int minStep = Integer.MAX_VALUE;
        for (Integer targetCharOnRingIndex : targetCharOnRingIndexList) {
            // 在ring上,从当前位置到达目标位置有顺时针和逆时针两种走法
            // 打印出target字符总共要花费的代价 = 当前决策的代价 + 打印的代价 + 后续的最小代价
            int rightCost = Math.abs(targetCharOnRingIndex - currentIndexOnRing);
            int rightTotalCost = rightCost + 1 + process(targetCharOnRingIndex, currentIndexOnKey + 1);
            int leftCost = Math.abs(ringSize - rightCost);
            int leftTotalCost = leftCost + 1 + process(targetCharOnRingIndex, currentIndexOnKey + 1);
            minStep = Math.min(minStep, rightTotalCost);
            minStep = Math.min(minStep, leftTotalCost);
        }
        return minStep;
    }

    public static class TestClass {
        //输入: ring = "godding", key = "gd"
        //输出: 4
        //解释:
        // 对于 key 的第一个字符 'g'，已经在正确的位置, 我们只需要1步来拼写这个字符。
        // 对于 key 的第二个字符 'd'，我们需要逆时针旋转 ring "godding" 2步使它变成 "ddinggo"。
        // 当然, 我们还需要1步进行拼写。
        // 因此最终的输出是 4。
        @Test
        public void test1() {
            String ring = "godding";
            String key = "gd";
            Solution1 solution = new Solution1();
            int ans = solution.findRotateSteps(ring, key);
            log.info("ans:{}", ans);
        }

        // 2:11 下午	info
        //				解答失败:
        //				测试用例:"abcde"
        //				"ade"
        //				测试结果:7
        //				期望结果:6
        //				stdout:
        @Test
        public void test2() {
            String ring = "abcde";
            String key = "ade";
            Solution1 solution = new Solution1();
            int ans = solution.findRotateSteps(ring, key);
            log.info("ans:{}", ans);
        }

        @Test
        public void test3() {
            String ring = "abc";
            String key = "aaccbb";
            Solution1 solution = new Solution1();
            int ans = solution.findRotateSteps(ring, key);
            log.info("ans:{}", ans);
        }

    }

}
//leetcode submit region end(Prohibit modification and deletion)

