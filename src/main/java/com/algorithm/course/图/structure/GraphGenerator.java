package com.algorithm.course.图.structure;

public class GraphGenerator {

    // matrix 所有的边
    // N*3 的矩阵
    // [weight, from节点上面的值，to节点上面的值]
    //
    // [ 5 , 0 , 7]
    // [ 3 , 0,  1]
    //
    public static Graph createGraph(int[][] matrix) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            // 拿到每一条边， matrix[i]
            int weight = matrix[i][0];
            int from = matrix[i][1];
            int to = matrix[i][2];
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge newEdge = new Edge(weight, fromNode, toNode);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(newEdge);
            graph.edges.add(newEdge);
        }
        return graph;
    }

    public static int[][] buildMatrix() {
        int[][] matrix = new int[24][3];
        matrix[0] = new int[] {12, 1, 2};
        matrix[1] = new int[] {12, 2, 1};

        matrix[2] = new int[] {14, 1, 7};
        matrix[3] = new int[] {14, 7, 1};

        matrix[4] = new int[] {16, 1, 6};
        matrix[5] = new int[] {16, 6, 1};

        matrix[6] = new int[] {10, 2, 3};
        matrix[7] = new int[] {10, 3, 2};

        matrix[8] = new int[] {7, 2, 6};
        matrix[9] = new int[] {7, 6, 2};

        matrix[10] = new int[] {6, 3, 6};
        matrix[11] = new int[] {6, 6, 3};

        matrix[12] = new int[] {5, 3, 5};
        matrix[13] = new int[] {5, 5, 3};

        matrix[14] = new int[] {3, 3, 4};
        matrix[15] = new int[] {3, 4, 3};

        matrix[16] = new int[] {4, 4, 5};
        matrix[17] = new int[] {4, 5, 4};

        matrix[18] = new int[] {2, 5, 6};
        matrix[19] = new int[] {2, 6, 5};

        matrix[20] = new int[] {8, 5, 7};
        matrix[21] = new int[] {8, 7, 5};

        matrix[22] = new int[] {9, 6, 7};
        matrix[23] = new int[] {9, 7, 6};

        return matrix;
    }

}
