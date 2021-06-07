package com.algorithm.lintcode.图.P127拓扑排序;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by catface
 * @date 2021/4/20 11:10 上午
 */
class DirectedGraphNode {
    int label;
    List<DirectedGraphNode> neighbors;

    DirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<DirectedGraphNode>();
    }
}
