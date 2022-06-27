package com.algorithm.course.图;

import org.junit.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author by 大猫
 * @date 2021/10/11 2:17 下午
 * <p>
 * Copyright 2021  catface996, Inc. All rights reserved.
 */
public class AStar {

    // f(n)是节点n的综合优先级。当我们选择下一个要遍历的节点时，我们总会选取综合优先级最高（值最小）的节点。
    // 从起点经过节点n,到达终点的总和优先级
    // g(n) 是节点n距离起点的代价。
    // h(n)是节点n距离终点的预计代价，这也就是A*算法的启发函数。关于启发函数我们在下面详细讲解。
    private Point[][] graph;
    private int rowRange;
    private int colRange;
    private PriorityQueue<Point> openQ = new PriorityQueue<>(new Comparator<Point>() {
        @Override
        public int compare(Point o1, Point o2) {
            return o2.getCostFromStart() - o1.getCostFromStart();
        }
    });
    private Set<Point> closeSet = new HashSet<>();

    public AStar(int rowRange, int colRange) {
        this.rowRange = rowRange;
        this.colRange = colRange;
        graph = new Point[colRange + 1][rowRange + 1];
        for (int rowIndex = 0; rowIndex <= rowRange; rowIndex++) {
            for (int colIndex = 0; colIndex <= colRange; colIndex++) {
                graph[rowIndex][colIndex] = new Point(rowIndex, colIndex, (int) (Math.random() * 10 / 5));
            }
        }
    }


    public int findMinCost(Point start, Point end) {
        start.setCostFromStart(0);
        openQ.add(start);
        while (!openQ.isEmpty()) {
            Point curPoint = openQ.poll();
            // 上

            // 下

            // 左

            // 右

        }
        return 1;
    }


    private int manhattanDistance(Point a, Point b) {
        return Math.abs(a.rowIndex - b.rowIndex) + Math.abs(a.colIndex - b.colIndex);
    }

    private Point getUpPoint(Point n) {
        if (n.rowIndex - 1 >= 0 && n.rowIndex <= rowRange && n.colIndex >= 0 && n.colIndex <= colRange) {
            return graph[n.rowIndex - 1][n.colIndex];
        }
        return null;
    }

    private Point getDownPoint(Point n) {
        if (n.rowIndex >= 0 && n.rowIndex + 1 <= rowRange && n.colIndex >= 0 && n.colIndex <= colRange) {
            return graph[n.rowIndex + 1][n.colIndex];
        }
        return null;
    }

    private Point getLeftPoint(Point n) {
        if (n.rowIndex >= 0 && n.rowIndex <= rowRange && n.colIndex - 1 >= 0 && n.colIndex <= colRange) {
            return graph[n.rowIndex][n.colIndex - 1];
        }
        return null;
    }

    private Point getRightPoint(Point n) {
        if (n.rowIndex >= 0 && n.rowIndex <= rowRange && n.colIndex >= 0 && n.colIndex + 1 <= colRange) {
            return graph[n.rowIndex][n.colIndex + 1];
        }
        return null;
    }

    public static class Point {
        private int rowIndex;
        private int colIndex;
        private int priority;
        private int costFromStart;
        private Point parent;

        public Point(int rowIndex, int colIndex) {
            this.rowIndex = rowIndex;
            this.colIndex = colIndex;
        }

        public Point(int rowIndex, int colIndex, int priority) {
            this.rowIndex = rowIndex;
            this.colIndex = colIndex;
            this.priority = priority;
        }

        public int getRowIndex() {
            return rowIndex;
        }

        public void setRowIndex(int rowIndex) {
            this.rowIndex = rowIndex;
        }

        public int getColIndex() {
            return colIndex;
        }

        public void setColIndex(int colIndex) {
            this.colIndex = colIndex;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public int getCostFromStart() {
            return costFromStart;
        }

        public void setCostFromStart(int costFromStart) {
            this.costFromStart = costFromStart;
        }

        public Point getParent() {
            return parent;
        }

        public void setParent(Point parent) {
            this.parent = parent;
        }
    }

    public static class TestClass {

        @Test
        public void test1() {
            AStar aStar = new AStar(3, 3);
        }
    }

}
