package com.ozygod.Graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Stack;

import java.io.File;
import java.util.Scanner;

public class Graph {
    private static final String NEWLINE = System.getProperty("line.separator");
    private final int V;
    private int E;
    private Bag<Integer>[] adj;
    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<>();
        }
    }

    public Graph(String path) {
        File file = new File(path);
        try {
            Scanner scanner = new Scanner(file);
            this.V = Integer.valueOf(scanner.nextLine());
            this.adj = (Bag<Integer>[]) new Bag[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new Bag<>();
            }
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] lines = line.split(" ");
                addEdge(Integer.valueOf(lines[0]), Integer.valueOf(lines[1]));
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException("invalid input format in Graph constructor", ex);
        }
    }

    public Graph(Graph G) {
        this.V = G.V;
        this.E = G.E;
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");

        adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<>();
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
        E++;
        adj[v].add(w);
        adj[w].add(v);
    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    public int maxDegree() {
        int max = 0;
        for (int i = 0; i < V(); i++) {
            if (degree(i) > max) max = degree(i);
        }
        return max;
    }

    public double avgDegree() {
        return 2.0 * E() / V();
    }

    public int numberOfSelfLoops() {
        int count = 0;
        for (int i = 0; i < V(); i++) {
            for (int w : adj(i))
                if (i == w) count++;
        }
        return count/2;
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
        String path = "D:\\workspace\\java\\algs4-data\\tinyG.txt";
        Graph graph = new Graph(path);
        System.out.println(graph.toString());
    }
}
