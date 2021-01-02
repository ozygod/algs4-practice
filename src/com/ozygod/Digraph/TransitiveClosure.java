package com.ozygod.Digraph;

import edu.princeton.cs.algs4.StdOut;

public class TransitiveClosure {
    private DirectDFS[] all;

    public TransitiveClosure(Digraph digraph) {
        all = new DirectDFS[digraph.V()];
        for (int i = 0; i < digraph.V(); i++) {
            all[i] = new DirectDFS(digraph, i);
        }
    }

    public boolean reachable(int v, int w) {
        return all[v].marked(w);
    }

    public static void main(String[] args) {
        String path = "D:\\workspace\\java\\algs4-data\\tinyDG.txt";
        Digraph G = new Digraph(path);

        TransitiveClosure tc = new TransitiveClosure(G);

        // print header
        StdOut.print("     ");
        for (int v = 0; v < G.V(); v++)
            StdOut.printf("%3d", v);
        StdOut.println();
        StdOut.println("--------------------------------------------");

        // print transitive closure
        for (int v = 0; v < G.V(); v++) {
            StdOut.printf("%3d: ", v);
            for (int w = 0; w < G.V(); w++) {
                if (tc.reachable(v, w)) StdOut.printf("  T");
                else                    StdOut.printf("   ");
            }
            StdOut.println();
        }
    }
}
