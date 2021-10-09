package com.algorithm.leetcode.数学.P12整数转罗马数字;

//罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
//
//
//字符          数值
//I             1
//V             5
//X             10
//L             50
//C             100
//D             500
//M             1000
//
// 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做 XXVII, 即为 XX + V +
//II 。
//
// 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5
// 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
//
//
// I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
// X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
// C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
//
//
// 给你一个整数，将其转为罗马数字。
//
//
//
// 示例 1:
//
//
//输入: num = 3
//输出: "III"
//
// 示例 2:
//
//
//输入: num = 4
//输出: "IV"
//
// 示例 3:
//
//
//输入: num = 9
//输出: "IX"
//
// 示例 4:
//
//
//输入: num = 58
//输出: "LVIII"
//解释: L = 50, V = 5, III = 3.
//
//
// 示例 5:
//
//
//输入: num = 1994
//输出: "MCMXCIV"
//解释: M = 1000, CM = 900, XC = 90, IV = 4.
//
//
//
// 提示：
//
//
// 1 <= num <= 3999
//
// Related Topics 哈希表 数学 字符串 👍 696 👎 0

import java.util.TreeMap;

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 3:07 下午	info
    //				解答成功:
    //				执行耗时:12 ms,击败了8.19% 的Java用户
    //				内存消耗:38.6 MB,击败了11.73% 的Java用户
    public String intToRoman(int num) {
        TreeMap<Integer, String> numStrMap = buildNumStrMap();
        StringBuilder ans = new StringBuilder();
        int curNum = num;
        Integer curKey = 1000;
        while (curNum > 0) {
            if (curNum >= curKey) {
                ans.append(numStrMap.get(curKey));
                curNum = curNum - curKey;
                continue;
            }
            curKey = numStrMap.lowerKey(curKey);
        }
        return ans.toString();
    }

    private TreeMap<Integer, String> buildNumStrMap() {
        TreeMap<Integer, String> numStrMap = new TreeMap<>();
        numStrMap.put(1, "I");
        numStrMap.put(2, "II");
        numStrMap.put(3, "III");
        numStrMap.put(4, "IV");
        numStrMap.put(5, "V");
        numStrMap.put(9, "IX");
        numStrMap.put(10, "X");
        numStrMap.put(20, "XX");
        numStrMap.put(30, "XXX");
        numStrMap.put(40, "XL");
        numStrMap.put(50, "L");
        numStrMap.put(90, "XC");
        numStrMap.put(100, "C");
        numStrMap.put(200, "CC");
        numStrMap.put(300, "CCC");
        numStrMap.put(400, "CD");
        numStrMap.put(500, "D");
        numStrMap.put(900, "CM");
        numStrMap.put(1000, "M");
        return numStrMap;
    }

    public static class TestClass {

        // 示例 1:
        //
        //输入: num = 3
        //输出: "III"
        @Test
        public void test1() {
            int num = 3;
            Solution solution = new Solution();
            String ans = solution.intToRoman(num);
            System.out.println(ans);
        }

        // 示例 2:
        //
        //输入: num = 4
        //输出: "IV"
        @Test
        public void test2() {
            int num = 4;
            Solution solution = new Solution();
            String ans = solution.intToRoman(num);
            System.out.println(ans);
        }

        // 示例 3:
        //
        //输入: num = 9
        //输出: "IX"
        @Test
        public void test3() {
            int num = 9;
            Solution solution = new Solution();
            String ans = solution.intToRoman(num);
            System.out.println(ans);
        }

        // 示例 4:
        //
        //输入: num = 58
        //输出: "LVIII"
        //解释: L = 50, V = 5, III = 3.
        @Test
        public void test4() {
            int num = 58;
            Solution solution = new Solution();
            String ans = solution.intToRoman(num);
            System.out.println(ans);
        }

    }

}
//leetcode submit region end(Prohibit modification and deletion)

