package com.algorithm.question.class8.计算器;

import java.util.Stack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/6/15 2:17 下午
 */
@Slf4j
public class Solution {

    String expression;
    int maxIndex;

    public int calculate(String expression) {
        this.expression = expression;
        maxIndex = expression.length() - 1;
        return f(0, maxIndex);
    }

    public int f(int start, int end) {
        Stack<Integer> numberStack = new Stack<>();
        Stack<Op> opStack = new Stack<>();
        int cur = start;
        char symbol;
        boolean newNumberSwitch = true;
        while (cur <= end) {
            symbol = expression.charAt(cur);
            Op op = Op.codeOf(symbol);
            if (op != null) {
                // 与栈顶的symbol比较,如果优先级小于等于栈顶,结算栈中的计算结果
                if (!opStack.isEmpty() && lessThan(opStack.peek(), op)) {
                    int temR = alu(numberStack.pop(), opStack.pop(), numberStack.pop());
                    numberStack.push(temR);
                }
                opStack.push(op);
                newNumberSwitch = true;
            } else if (isNumber(symbol)) {
                if (newNumberSwitch) {
                    numberStack.push(symbol - '0');
                    newNumberSwitch = false;
                } else {
                    int tempN = numberStack.pop() * 10 + symbol - '0';
                    numberStack.push(tempN);
                }
            } else if (symbol == '(') {
                int[] tempAns = process(cur, end);
                numberStack.push(tempAns[0]);
                cur = tempAns[1];
                newNumberSwitch = true;
            }
            cur++;
        }
        while (!opStack.isEmpty()) {
            int temR = alu(numberStack.pop(), opStack.pop(), numberStack.pop());
            numberStack.push(temR);
        }
        return numberStack.pop();
    }

    public int[] process(int start, int end) {
        int cur = start + 1;
        int count = 1;
        while (count != 0 && cur <= end) {
            switch (expression.charAt(cur)) {
                case '(':
                    count++;
                    break;
                case ')':
                    count--;
                    break;
                default:
                    break;
            }
            cur++;
        }
        int a = f(start + 1, cur - 2);
        return new int[] {a, cur - 1};
    }

    public int alu(int a, Op op, int b) {
        switch (op) {
            case PLUS:
                return a + b;
            case SUBTRACT:
                return a - b;
            case MULTIPLY:
                return a * b;
            case DIVIDE:
                return a / b;
            default:
                return -1;
        }
    }

    public boolean isNumber(char symbol) {
        int range = symbol - '0';
        return range >= 0 && range <= 9;
    }

    public boolean lessThan(Op opA, Op opB) {
        return opA.priority <= opB.priority;
    }

    public enum Op {
        /**
         * 操作符
         */
        PLUS(10, '+'),
        SUBTRACT(10, '-'),
        MULTIPLY(9, '*'),
        DIVIDE(9, '/'),
        ;

        private int priority;
        private char code;

        Op(int priority, char code) {
            this.priority = priority;
            this.code = code;
        }

        public static Op codeOf(char code) {
            for (Op value : Op.values()) {
                if (value.code == code) {
                    return value;
                }
            }
            return null;
        }

    }

    public static class TestClass {

        @Test
        public void test1() {
            String expression = "1+2*2+3*4";
            int check = 1 + 2 * 2 + 3 * 4;
            Solution solution = new Solution();
            int ans = solution.calculate(expression);
            log.info("ans:{},check;{}", ans, check);
        }

        @Test
        public void test2() {
            String expression = "1+2*(2+3)*4";
            int check = 1 + 2 * (2 + 3) * 4;
            Solution solution = new Solution();
            int ans = solution.calculate(expression);
            log.info("ans:{},check;{}", ans, check);
        }

        @Test
        public void test3() {
            String expression = "((101+210)+(2+3))*41";
            int check = ((101 + 210) + (2 + 3)) * 41;
            Solution solution = new Solution();
            int ans = solution.calculate(expression);
            log.info("ans:{},check;{}", ans, check);
        }
    }

}
