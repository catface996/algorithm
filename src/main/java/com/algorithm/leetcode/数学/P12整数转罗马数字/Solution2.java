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

import java.util.Stack;

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution2 {

    // 4:41 下午	info
    //				解答成功:
    //				执行耗时:8 ms,击败了15.92% 的Java用户
    //				内存消耗:38.9 MB,击败了7.56% 的Java用户
    public String intToRoman(int num) {
        Stack<NumStr> numStrStack = buildNumStrMap();
        StringBuilder ans = new StringBuilder();
        int curNum = num;
        while (curNum > 0) {
            if (curNum >= numStrStack.peek().getNum()) {
                ans.append(numStrStack.peek().getStr());
                curNum = curNum - numStrStack.peek().getNum();
                continue;
            }
            numStrStack.pop();
        }
        return ans.toString();
    }

    private Stack<NumStr> buildNumStrMap() {
        Stack<NumStr> stack = new Stack<>();

        stack.push(new NumStr(1, "I"));
        stack.push(new NumStr(2, "II"));
        stack.push(new NumStr(3, "III"));
        stack.push(new NumStr(4, "IV"));
        stack.push(new NumStr(5, "V"));
        stack.push(new NumStr(9, "IX"));
        stack.push(new NumStr(10, "X"));
        stack.push(new NumStr(20, "XX"));
        stack.push(new NumStr(30, "XXX"));
        stack.push(new NumStr(40, "XL"));
        stack.push(new NumStr(50, "L"));
        stack.push(new NumStr(90, "XC"));
        stack.push(new NumStr(100, "C"));
        stack.push(new NumStr(200, "CC"));
        stack.push(new NumStr(300, "CCC"));
        stack.push(new NumStr(400, "CD"));
        stack.push(new NumStr(500, "D"));
        stack.push(new NumStr(900, "CM"));
        stack.push(new NumStr(1000, "M"));
        return stack;
    }

    public static class NumStr {
        private int num;
        private String str;

        public NumStr(int num, String str) {
            this.num = num;
            this.str = str;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }
    }

    public static class TestClass {

        // 示例 1:
        //
        //输入: num = 3
        //输出: "III"
        @Test
        public void test1() {
            int num = 3;
            Solution2 solution = new Solution2();
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
            Solution2 solution = new Solution2();
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
            Solution2 solution = new Solution2();
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
            Solution2 solution = new Solution2();
            String ans = solution.intToRoman(num);
            System.out.println(ans);
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)

