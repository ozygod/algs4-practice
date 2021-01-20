package com.ozygod.EdgeWeightedDigraph;

/**
 * 给定两点的最短路径算法
 * 无环，无负权重
 * 给定一个起点s和一个终点t，是否存在一条最短路径？
 */
public class DijkstraAllPairsSP {
    private DijkstraSP[] all;

    public DijkstraAllPairsSP(EdgeWeightedDigraph digraph) {
        all = new DijkstraSP[digraph.V()];
        for (int i = 0; i < digraph.V(); i++) {
            all[i] = new DijkstraSP(digraph, i);
        }
    }

    public Iterable<DirectedEdge> path(int s, int t) {
        return all[s].pathTo(t);
    }

    public double dist(int s, int t) {
        return all[s].distTo(t);
    }
}
