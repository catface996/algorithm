package com.algorithm.experiment.AStar;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.junit.Test;

/**
 * @author by 大猫
 */
public class BreadthFirst {

    public Position process(Position[][] graph, Position start, Position end) {
        LinkedList<Position> queue = new LinkedList<>();
        int rows = graph.length;
        int cols = graph[0].length;
        Set<Position> accessedSet = new HashSet<>();
        queue.addLast(start);
        while (!queue.isEmpty()) {
            Position currentPosition = queue.pollFirst();
            // 从当前位置向四个方向探测
            // 向上
            if (currentPosition.y > 0) {
                Position up = graph[currentPosition.y - 1][currentPosition.x];
                if (!accessedSet.contains(up)) {
                    up.last = currentPosition;
                    if (up == end) {
                        return up;
                    }
                    queue.addLast(up);
                }
            }

            // 向下
            if (currentPosition.y < rows - 1) {
                Position down = graph[currentPosition.y + 1][currentPosition.x];
                if (!accessedSet.contains(down)) {
                    down.last = currentPosition;
                    if (down == end) {
                        return down;
                    }
                    queue.addLast(down);
                }
            }

            // 向左
            if (currentPosition.x > 0) {
                Position left = graph[currentPosition.y][currentPosition.x - 1];
                if (!accessedSet.contains(left)) {
                    left.last = currentPosition;
                    if (left == end) {
                        return left;
                    }
                    queue.addLast(left);
                }

            }

            // 向右
            if (currentPosition.x < cols - 1) {
                Position right = graph[currentPosition.y][currentPosition.x + 1];
                if (!accessedSet.contains(right)) {
                    right.last = currentPosition;
                    if (right == end) {
                        return right;
                    }
                    queue.addLast(right);
                }
            }

            // 当前节点的上下左右全部遍历结束后,加入到已探测集合
            accessedSet.add(currentPosition);

        }
        System.out.println("未发现要到达的终点");
        return null;
    }

    static class Position {
        private int x;
        private int y;
        int cost;
        private Position last;

        public Position(int y, int x) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        public Position getLast() {
            return last;
        }

        public void setLast(Position last) {
            this.last = last;
        }

        public void print() {
            System.out.println("y=" + y + "," + "x=" + x);
        }
    }

    public static class TestClass {

        public static Position[][] generateGraph(int x, int y) {
            Position[][] graph = new Position[y][x];
            for (int i = 0; i < y; i++) {
                for (int j = 0; j < x; j++) {
                    graph[i][j] = new Position(i, j);
                }
            }
            return graph;
        }

        @Test
        public void test1() {
            Position[][] graph = TestClass.generateGraph(10, 10);
            BreadthFirst breadthFirst = new BreadthFirst();
            Position end = breadthFirst.process(graph, graph[2][2], graph[6][8]);
            Position position = end;
            while (position != null) {
                position.print();
                position = position.last;
            }
        }
    }

}
