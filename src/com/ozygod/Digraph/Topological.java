package com.ozygod.Digraph;

public class Topological {
    private Iterable<Integer> order;

    public Topological(Digraph digraph) {
        DirectCycle directCycle = new DirectCycle(digraph);
        if (!directCycle.hasCycle()) {
            DepthFirstOrder depthFirstOrder = new DepthFirstOrder(digraph);
            order = depthFirstOrder.reversePost();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG() {
        return order != null;
    }

    public static void main(String[] args) {
        String path = "D:\\workspace\\java\\algs4-data\\jobs.txt";
//        edu.princeton.cs.algs4.SymbolDigraph symbolDigraph = new edu.princeton.cs.algs4.SymbolDigraph(path, "/");
//        edu.princeton.cs.algs4.Topological topological = new edu.princeton.cs.algs4.Topological(symbolDigraph.digraph());
        SymbolDigraph symbolDigraph = new SymbolDigraph(path, "/");
        Topological topological = new Topological(symbolDigraph.graph());
        for (int v : topological.order()) {
            System.out.println(symbolDigraph.name(v));
        }
    }
}