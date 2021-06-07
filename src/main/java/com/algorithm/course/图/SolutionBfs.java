package com.algorithm.course.图;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import com.algorithm.course.图.structure.Node;

/**
 * @author by catface
 * @date 2021/4/20 10:17 上午
 */
public class SolutionBfs {

    /**
     * 从开始节点触发,进行宽度优先遍历
     *
     * @param start 开始节点
     */
    public void bfs(Node start) {
        if (start == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            System.out.println(currentNode.value);
            for (Node next : currentNode.nexts) {
                if (set.contains(next)) {
                    continue;
                }
                queue.add(next);
                set.add(next);
            }
        }
    }
}
