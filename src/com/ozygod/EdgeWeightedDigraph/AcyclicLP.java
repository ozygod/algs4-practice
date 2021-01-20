package com.ozygod.EdgeWeightedDigraph;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 无环加权有向图中的单点最长路径
 * 给定无环加权有向图，找出一条从顶点s到给定顶点v的最长路径
 */
public class AcyclicLP implements SP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public AcyclicLP(EdgeWeightedDigraph digraph, int s) {
        edgeTo = new DirectedEdge[digraph.V()];
        distTo = new double[digraph.V()];

        for (int i = 0; i < digraph.V(); i++) {
            distTo[i] = Double.NEGATIVE_INFINITY;
        }
        distTo[s] = 0.0;

        EdgeWeightedTopological top = new EdgeWeightedTopological(digraph);
        for (int v : top.order())
            for (DirectedEdge edge : digraph.adj(v))
                relax(edge);
    }

    private void relax(DirectedEdge edge) {
        int v = edge.from(), w = edge.to();
        if (distTo[w] < distTo[v] + edge.weight()) {
            distTo[w] = distTo[v] + edge.weight();
            edgeTo[w] = edge;
        }
    }

    @Override
    public double distTo(int v) {
        return distTo[v];
    }

    @Override
    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    @Override
    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> result = new Stack<>();
        for (DirectedEdge edge = edgeTo[v]; edge != null; edge = edgeTo[edge.from()])
            result.push(edge);
        return result;
    }

    public static void main(String[] args) {
        String path = "D:\\workspace\\java\\algs4-data\\tinyEWDAG.txt";
        int s = 5;
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(path);

        // find shortest path from s to each other vertex in DAG
        AcyclicLP sp = new AcyclicLP(G, s);
        for (int v = 0; v < G.V(); v++) {
            if (sp.hasPathTo(v)) {
                StdOut.printf("%d to %d (%.2f)  ", s, v, sp.distTo(v));
                for (DirectedEdge e : sp.pathTo(v)) {
                    StdOut.print(e + "   ");
                }
                StdOut.println();
            }
            else {
                StdOut.printf("%d to %d         no path\n", s, v);
            }
        }
    }
}
