package course;

import java.util.Stack;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/10 10:04 上午
 */
public class RevertStack {

    public void revert(Stack<Integer> stack) {
        int value = getBottomStack(stack);
        if (stack.isEmpty()) {
            stack.push(value);
            return;
        }
        revert(stack);
        stack.push(value);
    }

    public int getBottomStack(Stack<Integer> stack) {
        int lastValue = stack.pop();
        if (stack.isEmpty()) {
            return lastValue;
        }
        int value = getBottomStack(stack);
        stack.push(lastValue);
        return value;
    }

    public static class TestClass {
        @Test
        public void test1() {
            Stack<Integer> stack = new Stack<>();
            stack.push(3);
            stack.push(2);
            stack.push(1);
            RevertStack revertStack = new RevertStack();
            revertStack.revert(stack);
            System.out.println(stack);
        }
    }
}
