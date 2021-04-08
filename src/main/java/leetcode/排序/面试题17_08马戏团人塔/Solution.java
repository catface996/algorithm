package leetcode.排序.面试题17_08马戏团人塔;

//有个马戏团正在设计叠罗汉的表演节目，一个人要站在另一人的肩膀上。出于实际和美观的考虑，在上面的人要比下面的人矮一点且轻一点。已知马戏团每个人的身高和体重，请
//编写代码计算叠罗汉最多能叠几个人。
//
// 示例：
//
//
//输入：height = [65,70,56,75,60,68] weight = [100,150,90,190,95,110]
//输出：6
//解释：从上往下数，叠罗汉最多能叠 6 层：(56,90), (60,95), (65,100), (68,110), (70,150), (75,190)
//
//
// 提示：
//
//
// height.length == weight.length <= 10000
//
// Related Topics 排序 二分查找 动态规划
// 👍 66 👎 0

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
@Slf4j
public class Solution {
    public int bestSeqAtIndex(int[] height, int[] weight) {

        return 1;
    }

    //输入：height = [65,70,56,75,60,68] weight = [100,150,90,190,95,110]
    //输出：6
    //解释：从上往下数，叠罗汉最多能叠 6 层：(56,90), (60,95), (65,100), (68,110), (70,150), (75,190)
    @Test
    public void test_1() {
        int[] height = new int[] {65, 70, 56, 75, 60, 68};
        int[] weight = new int[] {100, 150, 90, 190, 95, 110};
        log.info("height:{}", height);
        log.info("weight:{}", weight);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

