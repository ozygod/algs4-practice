package com.ozygod.Digraph;

import com.ozygod.Queue;
import edu.princeton.cs.algs4.Stack;

/**
 * 单点最短有向路径
 * 从s到给定顶点v是否存在一条最短的有向路径
 */
public class BreathFirstDirectedPaths {
    private static final int MAX = Integer.MAX_VALUE;
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;

    public BreathFirstDirectedPaths(Digraph digraph, int s) {
        marked = new boolean[digraph.V()];
        edgeTo = new int[digraph.V()];
        distTo = new int[digraph.V()];
        for (int i = 0; i < digraph.V(); i++) {
            distTo[i] = MAX;
        }
        bfs(digraph, s);
    }

    private void bfs(Digraph digraph, int s) {

        Queue<Integer> queue = new Queue<>();
        marked[s] = true;
        distTo[s] = 0;
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : digraph.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    queue.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public int distTo(int v) {
        return distTo[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x])
            path.push(x);
        path.push(x);
        return path;
    }
}
