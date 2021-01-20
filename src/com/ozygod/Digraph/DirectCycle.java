package com.ozygod.Digraph;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 检测有向图是否有环
 */
public class DirectCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;
    private boolean[] onStack;

    public DirectCycle(Digraph digraph) {
        this.marked = new boolean[digraph.V()];
        this.edgeTo = new int[digraph.V()];
        this.onStack = new boolean[digraph.V()];
        for (int i = 0; i < digraph.V(); i++) {
            if (!marked[i]) dfs(digraph, i);
        }
    }

    private void dfs(Digraph digraph, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : digraph.adj(v)) {
            if (this.hasCycle()) return;
            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(digraph, w);
            } else if (onStack[w]) {
                cycle = new Stack<>();
                for (int i = v; i != w; i = edgeTo[i]) {
                    cycle.push(i);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    private Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
        String path = "D:\\workspace\\java\\algs4-data\\tinyDG.txt";
        Digraph digraph = new Digraph(path);

        DirectCycle finder = new DirectCycle(digraph);
        if (finder.hasCycle()) {
            StdOut.print("Directed cycle: ");
            for (int v : finder.cycle()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }

        else {
            StdOut.println("No directed cycle");
        }
        StdOut.println();
    }
}
