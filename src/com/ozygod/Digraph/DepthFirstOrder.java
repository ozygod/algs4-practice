package com.ozygod.Digraph;

import com.ozygod.Queue;
import edu.princeton.cs.algs4.Stack;


public class DepthFirstOrder {
    private boolean[] marked;
    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reversePost;
    private int count;

    public DepthFirstOrder(Digraph graph) {
        marked = new boolean[graph.V()];
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();

        for (int i = 0; i < graph.V(); i++) {
            if (!marked[i]) dfs(graph, i);
        }
    }

    private void dfs(Digraph graph, int v) {
        pre.enqueue(v);
        marked[v] = true;
        count++;
        for (int w : graph.adj(v)) {
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

    public static void main(String[] args) {
        String path = "D:\\workspace\\java\\algs4-data\\tinyDAG.txt";
        Digraph digraph = new Digraph(path);
        DepthFirstOrder order = new DepthFirstOrder(digraph);
        System.out.println(order.pre());
        System.out.println(order.post());
        System.out.println(order.reversePost());
    }
}
