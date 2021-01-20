package com.ozygod.Digraph;

import com.ozygod.Queue;

/**
 * 有向图的强连通性
 */
public class KosarajuSSC {
    private boolean[] marked;
    private int[] id;
    private int count;

    public KosarajuSSC(Digraph graph) {
        marked = new boolean[graph.V()];
        id = new int[graph.V()];
        DepthFirstOrder order = new DepthFirstOrder(graph.reverse());
        for (int i : order.reversePost()) {
            if (!marked[i]) {
                dfs(graph, i);
                count++;
            }
        }
    }

    private void dfs(Digraph graph, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : graph.adj(v)) {
            if (!marked[w]) dfs(graph, w);
        }
    }

    public boolean stronglyConnected(int v ,int w) {
        return id(v) == id(w);
    }

    public int id(int v) {
        return id[v];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        String path = "D:\\workspace\\java\\algs4-data\\tinyDG.txt";
        Digraph digraph = new Digraph(path);
        KosarajuSSC ssc = new KosarajuSSC(digraph);

        int m = ssc.count();
        System.out.println(m + " strong components");
        Queue<Integer>[] components = (Queue<Integer>[])new Queue[m];
        for (int i = 0; i < m; i++) {
            components[i] = new Queue<>();
        }
        for (int i = 0; i < digraph.V(); i++) {
            components[ssc.id(i)].enqueue(i);
        }
        for (int i = 0; i < m; i++) {
            for (int v : components[i]) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }
}
