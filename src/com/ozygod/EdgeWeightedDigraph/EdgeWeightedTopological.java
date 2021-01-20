package com.ozygod.EdgeWeightedDigraph;

/**
 * 加权有向图的拓扑排序
 */
public class EdgeWeightedTopological {
    private Iterable<Integer> order;

    public EdgeWeightedTopological(EdgeWeightedDigraph digraph) {
        EdgeWeightedDirectedCycle directCycle = new EdgeWeightedDirectedCycle(digraph);
        if (!directCycle.hasCycle()) {
            EdgeWeightedDepthFirstOrder depthFirstOrder = new EdgeWeightedDepthFirstOrder(digraph);
            order = depthFirstOrder.reversePost();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG() {
        return order != null;
    }
}
