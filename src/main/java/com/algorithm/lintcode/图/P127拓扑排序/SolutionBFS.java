package com.algorithm.lintcode.图.P127拓扑排序;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author by catface
 * @date 2021/4/20 10:45 上午
 */
public class SolutionBFS {
    /**
     * 描述 给定一个有向图，图节点的拓扑排序定义如下:
     * <p>
     * 对于图中的每一条有向边 A -> B , 在拓扑排序中A一定在B之前.
     * <p>
     * 拓扑排序中的第一个节点可以是图中的任何一个没有其他节点指向它的节点.
     * <p>
     * 针对给定的有向图找到任意一种拓扑排序的顺序.
     *
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {

        // 首先找到所有入度为0的节点
        Map<DirectedGraphNode, Integer> inDegree = new HashMap<>();
        for (DirectedGraphNode graphNode : graph) {
            inDegree.put(graphNode, 0);
        }

        // 对图中的每一个节点的邻居节点做遍历,每个邻居节点都被当前节点指向,所以,每个邻居节点的入度+1
        for (DirectedGraphNode cur : graph) {
            for (DirectedGraphNode next : cur.neighbors) {
                inDegree.put(next, inDegree.get(next) + 1);
            }
        }

        // 将所有入度为0的节点加入到队列中
        Queue<DirectedGraphNode> queue = new LinkedList<>();
        for (DirectedGraphNode node : inDegree.keySet()) {
            if (inDegree.get(node) == 0) {
                queue.add(node);
            }
        }

        // 依次处理队列中的节点,处理到的入度为0的节点时,该节点指向的所有节点的入度-1,如果剩余的入度为0,符合加入队列的条件
        // 广度优先遍历
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            DirectedGraphNode currentNode = queue.poll();
            ans.add(currentNode);
            for (DirectedGraphNode neighbor : currentNode.neighbors) {
                if (inDegree.get(neighbor) == 1) {
                    queue.add(neighbor);
                } else {
                    inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                }
            }
        }
        return ans;
    }
}
