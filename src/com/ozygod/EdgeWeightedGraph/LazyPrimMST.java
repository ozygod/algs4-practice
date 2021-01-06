package com.ozygod.EdgeWeightedGraph;

import com.ozygod.PriorityQueue.MinPQ;
import com.ozygod.Queue;
import com.ozygod.UF.UF;
import com.ozygod.UF.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdOut;

/**
 * Prim算法（延时实现）
 */
public class LazyPrimMST {
    private static final double FLOATING_POINT_EPSILON = 1E-12;
    private boolean[] marked;
    private Queue<Edge> mst;
    private double weight;
    private MinPQ<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph graph) {
        mst = new Queue<>();
        pq = new MinPQ<>();
        marked = new boolean[graph.V()];
        for (int i = 0; i < graph.V(); i++) {
            if (!marked[i]) prim(graph, i);
        }

        assert check(graph);
    }

    private void prim(EdgeWeightedGraph graph, int s) {
        scan(graph, s);
        while(!pq.isEmpty()) {
            // 得到权重最小的横切边
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            assert marked[v] || marked[w];
            // 相邻两个节点如果都处理过则跳过
            if (marked[v] && marked[w]) continue;

            mst.enqueue(e);
            weight += e.weight();

            // 继续处理未被标记的节点
            if (!marked[v]) scan(graph, v);
            if (!marked[w]) scan(graph, w);
        }
    }

    /**
     * 标记节点，并找出其（未失效的）邻接边加入到最小优先队列中
     * @param graph
     * @param v
     */
    private void scan(EdgeWeightedGraph graph, int v) {
        assert !marked[v];
        marked[v] = true;
        for (Edge e : graph.adj(v)) {
            int w = e.other(v);
            if (!marked[w]) {
                pq.insert(e);
            }
        }
    }

    private Iterable<Edge> edges() {
        return mst;
    }

    private double weight() {
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
            for (Edge f : mst) {
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
        LazyPrimMST mst = new LazyPrimMST(G);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
    }
}
