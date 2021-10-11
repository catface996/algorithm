package com.algorithm.course.图.structure;

public class Edge {
    public int weight;
    public Node from;
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }

    public void print() {
        System.out.println("from " + from.value + " to " + to.value + " cost " + weight);
    }

}