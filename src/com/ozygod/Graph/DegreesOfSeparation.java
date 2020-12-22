package com.ozygod.Graph;

import edu.princeton.cs.algs4.StdIn;

public class DegreesOfSeparation {
    public static void main(String[] args) {
        String path = "D:\\workspace\\java\\algs4-data\\movies.txt";
        String delimiter = "/";
        SymbolGraph symbolGraph = new SymbolGraph(path, delimiter);
        Graph graph = symbolGraph.graph();

        String source = "Bacon, Kevin";
        if (!symbolGraph.contains(source)) {
            System.out.println(source + " not in database.");
            return;
        }

        int s = symbolGraph.indexOf(source);
        BreadthFirstPaths paths = new BreadthFirstPaths(graph, s);
        while(!StdIn.isEmpty()) {
            String sink = StdIn.readLine();
            if (symbolGraph.contains(sink)) {
                int t = symbolGraph.indexOf(sink);
                if (paths.hasPathTo(t)) {
                    for (int v : paths.pathTo(t)) {
                        System.out.println("   "+ symbolGraph.name(v));
                    }
                } else System.out.println("Not connected.");
            } else System.out.println("Not in database.");
        }
    }
}
