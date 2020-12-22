package com.ozygod.Graph;

import edu.princeton.cs.algs4.Stack;

public class TwoColor {
    private boolean isTwoColor = true;
    private boolean[] marked;
    private boolean[] color;
    private int[] edgeTo;
    private Stack<Integer> cycle;

    public TwoColor(Graph graph) {
        marked = new boolean[graph.V()];
        color = new boolean[graph.V()];
        edgeTo = new int[graph.V()];

        for (int i = 0; i < graph.V(); i++) {
            if (!marked[i]) dfs(graph, i);
        }
    }

    private void dfs(Graph graph, int v) {
        marked[v] = true;
        for (int w : graph.adj(v)) {
            if (cycle != null) return;

            if (!marked[w]) {
                edgeTo[w] = v;
                color[w] = !color[v];
                dfs(graph, w);
            } else if (color[w] == color[v]) {
                isTwoColor = false;
                cycle = new Stack<>();
                cycle.push(w);
                for (int i = v; i != w; i = edgeTo[i]) {
                    cycle.push(i);
                }
                cycle.push(w);
            }
        }
    }

    public boolean isTwoColor() {
        return isTwoColor;
    }

    public boolean color(int v) {
        return color[v];
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
