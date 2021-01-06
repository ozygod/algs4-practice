package com.ozygod.EdgeWeightedGraph;

import com.ozygod.UF.UF;
import com.ozygod.UF.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.StdOut;

public class BoruvkaMST {
    private static final double FLOATING_POINT_EPSILON = 1E-12;
    private double weight;
    private Bag<Edge> mst;

    public BoruvkaMST(EdgeWeightedGraph graph) {
        mst = new Bag<>();
        UF uf = new WeightedQuickUnionUF(graph.V());
        for (int t = 1; t < graph.V() && mst.size() < graph.V() - 1; t += t) {
            Edge[] closest = new Edge[graph.V()];
            for (Edge e : graph.edges()) {
                int v = e.either(), w = e.other(v);
                int i = uf.find(v), j = uf.find(w);
                if (i == j) continue;
                if (closest[i] == null || less(e, closest[i])) closest[i] = e;
                if (closest[j] == null || less(e, closest[j])) closest[j] = e;
            }

            for (int i = 0; i < graph.V(); i++) {
                Edge e = closest[i];
                if (e != null) {
                    int v = e.either(), w = e.other(v);
                    if (uf.connected(v, w)) continue;
                    mst.add(e);
                    weight += e.weight();
                    uf.union(v, w);
                }
            }
        }

        assert check(graph);
    }

    private boolean less(Edge i, Edge j) {
        return i.compareTo(j) < 0;
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
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
        BoruvkaMST mst = new BoruvkaMST(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
    }
}
