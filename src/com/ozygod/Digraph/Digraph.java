package com.ozygod.Digraph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Stack;

import java.io.File;
import java.util.Scanner;

public class Digraph {
    private static final String NEWLINE = System.getProperty("line.separator");
    private final int V;
    private int E;
    private Bag<Integer>[] adj;
    private int[] indegree;

    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        indegree = new int[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<>();
        }
    }

    public Digraph(String path) {
        File file = new File(path);
        try {
            Scanner scanner = new Scanner(file);
            this.V = scanner.nextInt();
            this.adj = (Bag<Integer>[]) new Bag[V];
            this.indegree = new int[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new Bag<>();
            }
            int E = scanner.nextInt();
            for (int i = 0; i < E; i++) {
                int v = scanner.nextInt();
                int w = scanner.nextInt();
                addEdge(v, w);
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException("invalid input format in Graph constructor", ex);
        }
    }

    public Digraph(Digraph G) {
        this.V = G.V();
        this.E = G.E();
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");

        adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<>();
        }

        indegree = new int[V];
        for (int i = 0; i < V; i++) {
            this.indegree[i] = G.indegree(i);
        }

        for (int v = 0; v < G.V(); v++) {
            Stack<Integer> reverse = new Stack<>();
            for (int w : G.adj(v)) {
                reverse.push(w);
            }
            for (int w: reverse) {
                adj[v].add(w);
            }
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        indegree[w]++;
        E++;
    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public Digraph reverse() {
        Digraph R = new Digraph(V);
        for (int i = 0; i < V; i++) {
            for (int w: adj(i))
                R.addEdge(w, i);
        }
        return R;
    }

    public int indegree(int v) {
        validateVertex(v);
        return indegree[v];
    }

    public int outdegree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public static void main(String[] args) {
        String path = "D:\\workspace\\java\\algs4-data\\tinyDG.txt";
        Digraph graph = new Digraph(path);
        System.out.println(graph.toString());
    }
}
