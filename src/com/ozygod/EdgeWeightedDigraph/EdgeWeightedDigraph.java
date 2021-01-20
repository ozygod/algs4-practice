package com.ozygod.EdgeWeightedDigraph;

import com.ozygod.EdgeWeightedGraph.Edge;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.StdRandom;

import java.io.File;
import java.util.Scanner;

public class EdgeWeightedDigraph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int E;
    private Bag<DirectedEdge>[] adj;
    private int[] indegree;

    public EdgeWeightedDigraph(int V) {
        this.V = V;
        this.E = 0;
        this.indegree = new int[V];
        this.adj = new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<>();
        }
    }

    public EdgeWeightedDigraph(int V, int E) {
        this(V);
        for (int i = 0; i < E; i++) {
            int v = StdRandom.uniform(V);
            int w = StdRandom.uniform(V);
            double weight = 0.01*StdRandom.uniform(100);
            addEdge(new DirectedEdge(v, w, weight));
        }
    }

    public EdgeWeightedDigraph(String path) {
        File file = new File(path);
        try {
            Scanner scanner = new Scanner(file);
            this.V = scanner.nextInt();
            this.indegree = new int[V];
            this.adj = new Bag[this.V];
            for (int i = 0; i < V; i++) {
                adj[i] = new Bag<>();
            }
            int e = scanner.nextInt();
            if (e < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
            while (e > 0) {
                int v = scanner.nextInt();
                int w = scanner.nextInt();
                double weight = scanner.nextDouble();
                addEdge(new DirectedEdge(v, w, weight));
                e--;
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException("invalid input format in EdgeWeightedDigraph  constructor", ex);
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(DirectedEdge edge) {
        int v = edge.from();
        int w = edge.to();
        adj[v].add(edge);
        indegree[w]++;
        E++;
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public int outdegree(int v) {
        return adj[v].size();
    }

    public int indegree(int v) {
        return indegree[v];
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> edges = new Bag<>();
        for (int i = 0; i < V; i++) {
            for (DirectedEdge edge : adj[i]) {
                edges.add(edge);
            }
        }
        return edges;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (DirectedEdge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public static void main(String[] args) {
        String path = "D:\\workspace\\java\\algs4-data\\tinyEWD.txt";
        EdgeWeightedDigraph digraph = new EdgeWeightedDigraph(path);
        System.out.println(digraph);
    }
}
