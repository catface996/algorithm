package com.algorithm.course.图;

import java.util.HashSet;
import java.util.Stack;

import com.algorithm.course.图.structure.Node;

/**
 * @author by catface
 * @date 2021/4/20 10:37 上午
 */
public class SolutionDfs {

    public void dfs(Node start) {
        if (start == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.push(start);
        set.add(start);
        System.out.println(start.value);
        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();
            for (Node next : currentNode.nexts) {
                if (!set.contains(next)) {
                    stack.push(currentNode);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }

            }

        }
    }
}
