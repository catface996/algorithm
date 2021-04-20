package lintcode.图.P127拓扑排序;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by catface
 * @date 2021/4/20 10:45 上午
 */
public class SolutionDFS2 {

    private final Comparator<Record> comparator = (o1, o2) -> Long.compare(o2.nodes, o1.nodes);

    /**
     * 拓扑排序
     *
     * @param graph 图
     * @return 结果数组
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        Map<DirectedGraphNode, Record> orderMap = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            f(node, orderMap);
        }
        List<Record> records = new ArrayList<>();
        orderMap.forEach((k, v) -> {
            records.add(v);
        });
        records.sort(comparator);
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        for (Record record : records) {
            ans.add(record.node);
        }
        return ans;
    }

    private Record f(DirectedGraphNode node, Map<DirectedGraphNode, Record> orderMap) {
        if (orderMap.containsKey(node)) {
            return orderMap.get(node);
        }
        long nodes = 0;
        for (DirectedGraphNode next : node.neighbors) {
            nodes += f(next, orderMap).nodes;
        }
        Record ans = new Record(node, nodes + 1);
        orderMap.put(node, ans);
        return ans;
    }

    public static class Record {
        private DirectedGraphNode node;
        // 累计节点数较大,需要是用long形
        private long nodes;

        public Record(DirectedGraphNode node, long nodes) {
            this.node = node;
            this.nodes = nodes;
        }
    }

}
