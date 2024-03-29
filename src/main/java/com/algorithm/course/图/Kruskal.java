package com.algorithm.course.图;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

import com.algorithm.course.图.structure.Edge;
import com.algorithm.course.图.structure.Graph;
import com.algorithm.course.图.structure.GraphGenerator;
import com.algorithm.course.图.structure.Node;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/20 5:22 下午
 */
public class Kruskal {

    /**
     * 贪心算法,每次选最小的边进行连接
     * <p>
     * 测试数据参考: https://blog.csdn.net/qq_42152365/article/details/83515782
     */
    public Set<Edge> minTree(Graph graph) {

        PriorityQueue<Edge> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        for (Edge edge : graph.edges) {
            heap.add(edge);
        }
        UnionFind unionFind = new UnionFind();
        unionFind.makeSets(graph.nodes.values());
        Set<Edge> ans = new HashSet<>();
        while (!heap.isEmpty()) {
            Edge edge = heap.poll();
            if (!unionFind.isSameSet(edge.from, edge.to)) {
                unionFind.union(edge.from, edge.to);
                ans.add(edge);
            }
        }
        return ans;
    }

    public static class UnionFind {
        private Map<Node, Node> parent;
        private Map<Node, Integer> size;

        public UnionFind() {
            parent = new HashMap<>();
            size = new HashMap<>();
        }

        public void makeSets(Collection<Node> nodes) {
            parent.clear();
            size.clear();
            for (Node node : nodes) {
                parent.put(node, node);
                size.put(node, 1);
            }
        }

        private Node find(Node node) {
            Stack<Node> path = new Stack<>();
            while (node != parent.get(node)) {
                path.push(node);
                node = parent.get(node);
            }
            while (!path.isEmpty()) {
                Node pathNode = path.pop();
                parent.put(pathNode, node);
            }
            return node;
        }

        /**
         * 合并
         *
         * @param nodeI
         * @param nodeJ
         */
        public void union(Node nodeI, Node nodeJ) {
            Node iRoot = find(nodeI);
            Node jRoot = find(nodeJ);
            if (iRoot != jRoot) {
                if (size.get(iRoot) >= size.get(jRoot)) {
                    size.put(iRoot, size.get(iRoot) + size.get(jRoot));
                    parent.put(jRoot, iRoot);
                } else {
                    size.put(jRoot, size.get(iRoot) + size.get(jRoot));
                    parent.put(iRoot, jRoot);
                }
            }
        }

        private boolean isSameSet(Node nodeI, Node nodeJ) {
            return find(nodeI) == find(nodeJ);
        }

    }

    public static class TestClass {
        @Test
        public void test1() {
            int[][] matrix = GraphGenerator.buildMatrix();
            Graph graph = GraphGenerator.createGraph(matrix);
            Kruskal kruskal = new Kruskal();
            Set<Edge> ans = kruskal.minTree(graph);
            int totalWeight = 0;
            for (Edge an : ans) {
                totalWeight += an.weight;
            }
            System.out.println("最小生成树权重和:"+totalWeight);
        }
    }
}
