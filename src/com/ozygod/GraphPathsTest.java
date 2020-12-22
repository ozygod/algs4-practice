package com.ozygod;

import com.ozygod.Graph.BreadthFirstPaths;
import com.ozygod.Graph.Graph;
import com.ozygod.Graph.Paths;

public class GraphPathsTest {
    public static void main(String[] args) {
        int s = 0;

        String path = "D:\\workspace\\java\\algs4-data\\tinyCG.txt";
        Graph graph = new Graph(path);

//        Paths paths = new DepthFirstPaths(graph, s);
        Paths paths = new BreadthFirstPaths(graph, s);
        for (int i = 0; i < graph.V(); i++) {
            System.out.print(s + " to "+ i + ": ");
            if (paths.hasPathTo(i)) {
                for (int x : paths.pathTo(i)) {
                    if (x == s) System.out.print(x);
                    else System.out.print("-"+x);
                }
            }
            System.out.println();
        }
    }
}
