package com.algorithm.course.图;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.algorithm.course.图.structure.Edge;
import com.algorithm.course.图.structure.Graph;
import com.algorithm.course.图.structure.GraphGenerator;
import com.algorithm.course.图.structure.Node;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/21 10:50 上午
 */
public class Dijkstra1 {

    /**
     * 构建最小生成树
     *
     * @param from 原始节点
     * @return 每个几点的最小路径
     */
    public Map<Node, Integer> dijkstra(Node from) {

        // 定义,从开始节点到目标节点的距离,开始节点到开始节点距离是0
        // key是目标节点,value是开始节点到目标节点的距离
        // 第一个加入map中的节点即开始节点,距离为0
        Map<Node, Integer> distanceMap = new HashMap<>();

        // 已经确定处理完成的节点
        Set<Node> selectedNodes = new HashSet<>();

        // 初始化距离表
        distanceMap.put(from, 0);

        for (; ; ) {
            Node minNode = getMinAndNotSelectedNode(distanceMap, selectedNodes);
            if (minNode == null) {
                break;
            }
            int distance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                Node toNode = edge.to;
                if (!distanceMap.containsKey(toNode)) {
                    // sourceNode,首次加入距离表中的节点; currentNode,正在处理的节点(每轮选出的距离最小节点);toNode:从当前节点可直达的节点
                    // 如果到toNode未出现在距离表中,加入距离表,距离即为 sourceNode到currentNode的距离+当前边的距离(currentNode到toNode的距离)
                    distanceMap.put(toNode, distance + edge.weight);
                } else {
                    // 如果toNode已经记录在距离表中,计算sourceNode经由currentNode到达toNode的距离
                    // 如果小于已经记录在表中的sourceNode到达toNode的距离,更新toNode在表中的距离
                    distanceMap.put(toNode, Math.min(distanceMap.get(toNode), distance + edge.weight));
                }
            }
            // 当前选出的最小节点处理结束,标记已处理
            selectedNodes.add(minNode);
        }
        return distanceMap;
    }

    /**
     * 获取距离表中未处理,且距离最小的节点
     *
     * @param distanceMap   距离表
     * @param selectedNodes 已经处理的节点
     * @return 距离最小, 且未处理的节点
     */
    private Node getMinAndNotSelectedNode(Map<Node, Integer> distanceMap, Set<Node> selectedNodes) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Entry<Node, Integer> entry : distanceMap.entrySet()) {
            if (!selectedNodes.contains(entry.getKey()) && entry.getValue() < minDistance) {
                minNode = entry.getKey();
                minDistance = entry.getValue();
            }
        }
        return minNode;
    }

    public static class TestClass {
        @Test
        public void test1() {
            int[][] matrix = GraphGenerator.buildMatrix();
            Graph graph = GraphGenerator.createGraph(matrix);
            Dijkstra1 dijkstra1 = new Dijkstra1();
            for (Node node : graph.nodes.values()) {
                Map<Node, Integer> distanceMap = dijkstra1.dijkstra(node);
                System.out.println("xx");
                break;
            }
        }
    }

}
