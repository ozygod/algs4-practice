package com.ozygod;

import com.ozygod.Graph.CC;
import com.ozygod.Graph.Graph;
import edu.princeton.cs.algs4.Bag;

public class GraphCCTest {
    public static void main(String[] args) {
        int s = 0;

        String path = "D:\\workspace\\java\\algs4-data\\tinyG.txt";
        Graph graph = new Graph(path);

        CC cc = new CC(graph);

        int M = cc.count();
        System.out.println(M + " components");

        Bag<Integer>[] components = (Bag<Integer>[]) new Bag[M];
        for (int i = 0; i < M; i++) {
            components[i] = new Bag<>();
        }
        for (int i = 0; i < graph.V(); i++) {
            components[cc.id(i)].add(i);
        }
        for (int i = 0; i < M; i++) {
            for (int v: components[i]) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }
}
