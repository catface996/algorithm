package question.class1_只有两种字符的数组分区需要的最小交换次数.code;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/20 3:59 下午
 */
@Slf4j
public class Solution1 {

    //TODO 需要认真思考

    public int minSwapNum(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] arr = s.toCharArray();
        return Math.min(minSwapNum(arr, 'G'), minSwapNum(arr, 'B'));
    }

    /**
     * 复杂度O(N),right不回退
     *
     * @param arr      数组
     * @param leftChar 放在左侧的字符
     * @return 最小交换次数
     */
    public int minSwapNum(char[] arr, char leftChar) {
        int step = 0;
        int left = 0;
        int right = 0;
        while (right < arr.length) {
            // 如果当前左侧的字符与左侧下一个字符相同,左侧向右扩张
            if (arr[left] == leftChar) {
                right++;
                left++;
                continue;
            }
            // 发现第一个与左侧不同的字符,用left记录当前位置
            // right继续向右,寻找越过第一个不同的字符,首先出现的相同字符
            while (right < arr.length && arr[right] != leftChar) {
                right++;
            }
            if (right < arr.length) {
                swap(arr, left, right);
                step += right - left;
                right++;
                left++;
            }
        }

        return step;
    }

    public void swap(char[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static class TestClass {

        public static String randomString(int maxLen) {
            char[] str = new char[(int)(Math.random() * maxLen)];
            for (int i = 0; i < str.length; i++) {
                str[i] = Math.random() < 0.5 ? 'G' : 'B';
            }
            return String.valueOf(str);
        }

        @Test
        public void test1() {
            String s = "GBG";
            Solution1 solution = new Solution1();
            int ans1 = solution.minSwapNum(s);
            int ans2 = solution.minSwapNum(s);
            System.out.println(ans1);
            System.out.println(ans2);

        }

        @Test
        public void test2() {
            String s = "GBBG";
            Solution1 solution = new Solution1();
            int ans1 = solution.minSwapNum(s);
            System.out.println(ans1);
        }

        @Test
        public void test3() {
            String s = "GBBGG";
            Solution1 solution = new Solution1();
            int ans1 = solution.minSwapNum(s);
            System.out.println(ans1);
        }

        @Test
        public void test4() {
            String s = "BBGGBGGBBBBBBGBGBGBBBGGGG";
            Solution1 solution = new Solution1();
            int ans1 = solution.minSwapNum(s);
            int ans11 = solution.minSwapNum(s);
            int ans2 = Code04_MinSwapStep.minSteps2(s);
            log.info("ans1:{},ans11:{}.ans2:{}", ans1, ans11, ans2);
        }

        @Test
        public void testForce() {
            int maxLen = 100;
            int testTime = 1000000;
            Solution1 solution1 = new Solution1();
            System.out.println("测试开始");
            for (int i = 0; i < testTime; i++) {
                String str = randomString(maxLen);
                int ans1 = solution1.minSwapNum(str);
                int ans2 = Code04_MinSwapStep.minSteps2(str);
                if (ans1 != ans2) {
                    System.out.println("Oops!");
                    System.out.println(str);
                    assert false;
                }
            }
            System.out.println("测试结束");
        }

    }
}
