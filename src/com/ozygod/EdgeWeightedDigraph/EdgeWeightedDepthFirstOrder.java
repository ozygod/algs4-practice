package com.ozygod.EdgeWeightedDigraph;

import com.ozygod.Queue;
import edu.princeton.cs.algs4.Stack;

public class EdgeWeightedDepthFirstOrder {
    private boolean[] marked;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;
    private int count;

    public EdgeWeightedDepthFirstOrder(EdgeWeightedDigraph graph) {
        marked = new boolean[graph.V()];
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();

        for (int i = 0; i < graph.V(); i++) {
            if (!marked[i]) dfs(graph, i);
        }
    }

    private void dfs(EdgeWeightedDigraph graph, int v) {
        pre.enqueue(v);
        marked[v] = true;
        count++;
        for (DirectedEdge edge : graph.adj(v)) {
            int w = edge.to();
            if (!marked[w]) dfs(graph, w);
        }
        post.enqueue(v);
        reversePost.push(v);
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public int count() {
        return count;
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }
}
