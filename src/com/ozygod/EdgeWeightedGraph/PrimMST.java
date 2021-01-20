package com.ozygod.EdgeWeightedGraph;

import com.ozygod.Queue;
import com.ozygod.UF.UF;
import com.ozygod.UF.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.StdOut;

/**
 * Prim算法（实时实现）
 * 一次取一个顶点，然后找到它最小的邻接边，加入最小生成树，已经处理过的顶点不再处理。
 */
public class PrimMST {
    private static final double FLOATING_POINT_EPSILON = 1E-12;

    private Edge[] edgeTo;
    private double[] distTo;
    private boolean[] marked;
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightedGraph graph) {
        edgeTo = new Edge[graph.V()];
        distTo = new double[graph.V()];
        marked = new boolean[graph.V()];
        pq = new IndexMinPQ<>(graph.V());
        for (int i = 0; i < graph.V(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        for (int i = 0; i < graph.V(); i++) {
            if (!marked[i]) prim(graph, i);
        }
        assert check(graph);
    }

    private void prim(EdgeWeightedGraph graph, int s) {
        distTo[s] = 0.0;
        pq.insert(s, distTo[s]);
        while(!pq.isEmpty()) {
            int v = pq.delMin();
            scan(graph, v);
        }
    }

    private void scan(EdgeWeightedGraph graph, int v) {
        marked[v] = true;
        for (Edge e : graph.adj(v)) {
            int w = e.other(v);
            if (marked[w]) continue;
            if (e.weight() < distTo[w]) {
                distTo[w] = e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }

    public Iterable<Edge> edges() {
        Queue<Edge> mst = new Queue<>();
        for (int i = 0; i < edgeTo.length; i++) {
            Edge e = edgeTo[i];
            if (e != null) mst.enqueue(e);
        }
        return mst;
    }

    public double weight() {
        double weight = 0.0;
        for (int i = 0; i < distTo.length; i++) {
            weight += distTo[i];
        }
        return weight;
    }

    // check optimality conditions (takes time proportional to E V lg* V)
    private boolean check(EdgeWeightedGraph G) {

        // check weight
        double totalWeight = 0.0;
        for (Edge e : edges()) {
            totalWeight += e.weight();
        }
        if (Math.abs(totalWeight - weight()) > FLOATING_POINT_EPSILON) {
            System.err.printf("Weight of edges does not equal weight(): %f vs. %f\n", totalWeight, weight());
            return false;
        }

        // check that it is acyclic
        UF uf = new WeightedQuickUnionUF(G.V());
        for (Edge e : edges()) {
            int v = e.either(), w = e.other(v);
            if (uf.find(v) == uf.find(w)) {
                System.err.println("Not a forest");
                return false;
            }
            uf.union(v, w);
        }

        // check that it is a spanning forest
        for (Edge e : G.edges()) {
            int v = e.either(), w = e.other(v);
            if (uf.find(v) != uf.find(w)) {
                System.err.println("Not a spanning forest");
                return false;
            }
        }

        // check that it is a minimal spanning forest (cut optimality conditions)
        for (Edge e : edges()) {

            // all edges in MST except e
            uf = new WeightedQuickUnionUF(G.V());
            for (Edge f : edges()) {
                int x = f.either(), y = f.other(x);
                if (f != e) uf.union(x, y);
            }

            // check that e is min weight edge in crossing cut
            for (Edge f : G.edges()) {
                int x = f.either(), y = f.other(x);
                if (uf.find(x) != uf.find(y)) {
                    if (f.weight() < e.weight()) {
                        System.err.println("Edge " + f + " violates cut optimality conditions");
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String path = "D:\\workspace\\java\\algs4-data\\tinyEWG.txt";
        EdgeWeightedGraph G = new EdgeWeightedGraph(path);
        PrimMST mst = new PrimMST(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
    }
}
