package com.algorithm.course.图;

import com.algorithm.course.图.Dijkstra2.NodeHeap.NodeRecord;
import com.algorithm.course.图.structure.Edge;
import com.algorithm.course.图.structure.Graph;
import com.algorithm.course.图.structure.GraphGenerator;
import com.algorithm.course.图.structure.Node;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by catface
 * @date 2021/4/21 3:31 下午
 */
public class Dijkstra2 {

    public HashMap<Node, Integer> dijkstra(Graph graph) {
        NodeHeap nodeHeap = new NodeHeap(graph.nodes.size());
        Node from = null;
        for (Node value : graph.nodes.values()) {
            from = value;
            break;
        }
        HashMap<Node, Integer> ans = new HashMap<>();
        nodeHeap.addOrUpdate(from, 0);
        while (!nodeHeap.isEmpty()) {
            NodeRecord curRecord = nodeHeap.pop();
            for (Edge edge : curRecord.node.edges) {
                nodeHeap.addOrUpdate(edge.to, curRecord.distance + edge.weight);
            }
            ans.put(curRecord.node, curRecord.distance);
        }
        return ans;
    }

    public static class NodeHeap {
        private Node[] nodes;
        private int size;
        private Map<Node, Integer> distanceMap;
        private Map<Node, Integer> indexMap;

        public NodeHeap(int n) {
            nodes = new Node[n];
            this.size = 0;
            distanceMap = new HashMap<>(n);
            indexMap = new HashMap<>(n);
        }

        public void addOrUpdate(Node node, int newDistance) {
            if (inHeap(node)) {
                distanceMap.put(node, Math.min(distanceMap.get(node), newDistance));
                heapify(indexMap.get(node));
            }
            if (!isEntered(node)) {
                nodes[size] = node;
                indexMap.put(node, size);
                distanceMap.put(node, newDistance);
                heapInsert(size++);
            }

        }

        public NodeRecord pop() {
            if (size <= 0) {
                return null;
            }
            NodeRecord record = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            swap(0, size - 1);
            nodes[size - 1] = null;
            distanceMap.remove(record.node);
            indexMap.put(record.node, -1);
            size--;
            heapify(0);
            return record;
        }

        public void heapify(int index) {
            Node node = nodes[index];
            int left = index * 2 + 1;
            while (left < size) {
                int swapIndex = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left]) ?
                    left + 1 : left;
                swapIndex = distanceMap.get(node) < distanceMap.get(nodes[swapIndex]) ? index : swapIndex;
                if (index == swapIndex) {
                    return;
                }
                swap(index, swapIndex);
                index = swapIndex;
                left = index * 2 + 1;
            }
        }

        private void heapInsert(int index) {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void swap(int i, int j) {
            if (i == j) {
                return;
            }
            Node iNode = nodes[i];
            Node jNode = nodes[j];
            nodes[i] = jNode;
            indexMap.put(jNode, i);
            nodes[j] = iNode;
            indexMap.put(iNode, j);
        }

        public boolean isEmpty() {
            return size <= 0;
        }

        private boolean isEntered(Node node) {
            return indexMap.containsKey(node);
        }

        private boolean inHeap(Node node) {
            return isEntered(node) && indexMap.get(node) != -1;
        }

        public static class NodeRecord {
            private Node node;
            private int distance;

            public NodeRecord(Node node, int distance) {
                this.node = node;
                this.distance = distance;
            }
        }
    }

    public static class TestClass {
        @Test
        public void test1() {
            int[][] matrix = GraphGenerator.buildMatrix();
            Graph graph = GraphGenerator.createGraph(matrix);
            Dijkstra2 dijkstra2 = new Dijkstra2();
            HashMap<Node, Integer> ans1 = dijkstra2.dijkstra(graph);
            Node from = null;
            for (Node value : graph.nodes.values()) {
                from = value;
                break;
            }
            HashMap<Node, Integer> ans2 = Code06_Dijkstra.dijkstra2(from,graph.nodes.size());
            System.out.println("");
        }
    }


}
