package com.algorithm.course.图.structure;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {
	public HashMap<Integer, Node> nodes;
	public HashSet<Edge> edges;

	public Graph() {
		nodes = new HashMap<>();
		edges = new HashSet<>();
	}

	public void print() {
		System.out.println("print graph start **********");
		for (Edge edge : edges) {
			edge.print();
		}
		System.out.println("print graph end **********");
	}
}
