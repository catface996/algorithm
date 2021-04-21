package course.图;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import course.图.structure.Edge;
import course.图.structure.Graph;
import course.图.structure.GraphGenerator;
import course.图.structure.Node;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/21 10:09 上午
 */
public class Prim {

    private final Comparator<Edge> comparator = Comparator.comparingInt(o -> o.weight);

    public Set<Edge> primMST(Graph graph) {
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(comparator);
        Set<Node> nodeSet = new HashSet<>();
        Set<Edge> ans = new HashSet<>();
        for (Node node : graph.nodes.values()) {
            nodeSet.add(node);
            for (Edge edge : node.edges) {
                priorityQueue.add(edge);
            }
            while (!priorityQueue.isEmpty()) {
                Edge edge = priorityQueue.poll();
                if (!nodeSet.contains(edge.to)) {
                    ans.add(edge);
                    nodeSet.add(edge.to);
                    for (Edge nextEdge : edge.to.edges) {
                        priorityQueue.add(nextEdge);
                    }
                }
            }
        }
        return ans;
    }

    public static class TestClass {
        @Test
        public void test1() {
            int[][] matrix = GraphGenerator.buildMatrix();
            Graph graph = GraphGenerator.createGraph(matrix);
            Prim prim = new Prim();
            Set<Edge> ans = prim.primMST(graph);
            int totalWeight = 0;
            for (Edge an : ans) {
                totalWeight += an.weight;
            }
            System.out.println("最小生成树权重和:"+totalWeight);
        }
    }

}
