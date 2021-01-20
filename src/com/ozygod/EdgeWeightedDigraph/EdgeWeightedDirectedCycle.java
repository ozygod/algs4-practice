package com.ozygod.EdgeWeightedDigraph;

import edu.princeton.cs.algs4.Stack;

public class EdgeWeightedDirectedCycle {
    private boolean[] marked;
    private DirectedEdge[] edgeTo;
    private Stack<DirectedEdge> cycle;
    private boolean[] onStack;

    public EdgeWeightedDirectedCycle(EdgeWeightedDigraph digraph) {
        this.marked = new boolean[digraph.V()];
        this.edgeTo = new DirectedEdge[digraph.V()];
        this.onStack = new boolean[digraph.V()];
        for (int i = 0; i < digraph.V(); i++) {
            if (!marked[i]) dfs(digraph, i);
        }
    }

    private void dfs(EdgeWeightedDigraph digraph, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (DirectedEdge edge : digraph.adj(v)) {
            int w = edge.to();
            if (this.hasCycle()) return;
            else if (!marked[w]) {
                edgeTo[w] = edge;
                dfs(digraph, w);
            } else if (onStack[w]) {
                cycle = new Stack<>();
                DirectedEdge i = edge;
                while (i.from() != w) {
                    cycle.push(i);
                    i = edgeTo[i.from()];
                }
                cycle.push(i);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }
}
