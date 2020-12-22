package com.ozygod.Graph;

import edu.princeton.cs.algs4.Stack;

public class Cycle {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;

    public Cycle(Graph graph) {
        marked = new boolean[graph.V()];
        edgeTo = new int[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            if (!marked[i]) dfs(graph, i, -1);
        }
    }

    private void dfs(Graph graph, int v, int u) {
        marked[v] = true;
        for (int w : graph.adj(v)) {
            if (cycle != null) return;

            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(graph, w, v);
            } else if (w != u) {
                cycle = new Stack<>();
                for (int i = v; i != w; i = edgeTo[i]) {
                    cycle.push(i);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
        String path = "D:\\workspace\\java\\algs4-data\\tinyG.txt";
        Graph graph = new Graph(path);

        Cycle cycle = new Cycle(graph);
        if (cycle.hasCycle()) {
            for (int v : cycle.cycle()) {
                System.out.print(v + " ");
            }
            System.out.println();
        } else {
            System.out.println("Graph is acyclic");
        }
    }
}
