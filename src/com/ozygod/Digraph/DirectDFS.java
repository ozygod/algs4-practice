package com.ozygod.Digraph;

public class DirectDFS {
    private boolean[] marked;
    private int count;

    public DirectDFS(Digraph graph, int s) {
        marked = new boolean[graph.V()];
        dfs(graph, s);
    }

    public DirectDFS(Digraph digraph, Iterable<Integer> sources) {
        marked = new boolean[digraph.V()];
        for (int v : sources) {
            if (!marked[v]) dfs(digraph, v);
        }
    }

    private void dfs(Digraph graph, int v) {
        marked[v] = true;
        count++;
        for (int w : graph.adj(v)) {
            if (!marked[w]) dfs(graph, w);
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        int s = 2;

        String path = "D:\\workspace\\java\\algs4-data\\tinyDG.txt";
        Digraph graph = new Digraph(path);

        DirectDFS search = new DirectDFS(graph, s);

        for (int i = 0; i < graph.V(); i++) {
            if (search.marked(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
}
