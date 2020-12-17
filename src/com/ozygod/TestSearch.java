package com.ozygod;

import com.ozygod.Graph.DepthFirstSearch;
import com.ozygod.Graph.Graph;
import com.ozygod.Graph.Search;

public class TestSearch {
    public static void main(String[] args) {
        int s = 0;

        String path = "D:\\workspace\\java\\algs4-data\\tinyCG.txt";
        Graph graph = new Graph(path);

        Search search = new DepthFirstSearch(graph, s);

        for (int i = 0; i < graph.V(); i++) {
            if (search.marked(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();

        if (search.count() != graph.V()) {
            System.out.print("NOT ");
        }
        System.out.println("connected");
    }
}
