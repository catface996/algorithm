package pratice.leetcode.practice;

import java.util.Stack;

import org.junit.Test;

/**
 * @author by catface
 * @date 2020/11/10
 */
public class StackForQueue {

    private Stack<Integer> in;

    private Stack<Integer> out;

    public StackForQueue() {
        in = new Stack<Integer>();
        out = new Stack<Integer>();
    }

    public void appendTail(int value) {
        in.push(value);
    }

    public int deleteHead() {

        if (!out.isEmpty()) {
            return out.pop();
        }

        while (!in.empty()) {
            int value = in.pop();
            out.push(value);
        }

        if (out.isEmpty()) {
            return -1;
        }

        return out.pop();
    }

    @Test
    public void test() {
        StackForQueue stackForQueue = new StackForQueue();
        stackForQueue.appendTail(3);
        System.out.println(stackForQueue.deleteHead());
        System.out.println(stackForQueue.deleteHead());
    }

}
