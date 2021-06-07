package com.algorithm.lintcode.图.P127拓扑排序;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by catface
 * @date 2021/4/20 10:45 上午
 */
public class SolutionDFS1 {

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // 对所有的节点计算深度
        Map<DirectedGraphNode, Record> order = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            f(node, order);
        }
        List<Record> records = new ArrayList<>();
        for (Record value : order.values()) {
            records.add(value);
        }
        records.sort((o1, o2) -> o2.deep - o1.deep);
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        for (Record record : records) {
            ans.add(record.node);
        }
        return ans;
    }

    /**
     * 求拓扑序,图中一定无环路
     * @param cur 当前节点节
     * @param order 记录节点深度的map
     * @return
     */
    public Record f(DirectedGraphNode cur, Map<DirectedGraphNode, Record> order) {
        if (order.containsKey(cur)) {
            return order.get(cur);
        }
        int follow = 0;
        for (DirectedGraphNode next : cur.neighbors) {
            follow = Math.max(follow, f(next, order).deep);
        }
        Record ans = new Record(cur, follow + 1);
        order.put(cur, ans);
        return ans;
    }

    public static class Record {
        private DirectedGraphNode node;
        private int deep;
        public Record(DirectedGraphNode node, int deep) {
            this.node = node;
            this.deep = deep;
        }
    }

}
