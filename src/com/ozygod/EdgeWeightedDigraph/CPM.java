package com.ozygod.EdgeWeightedDigraph;

import java.io.File;
import java.util.Scanner;

/**
 * 优先级限制下的并行任务调度问题的关键路径算法
 */
public class CPM {
    private CPM(){}

    public static void main(String[] args) throws Exception {
        String path = "D:\\workspace\\java\\algs4-data\\jobsPC.txt";
        Scanner scanner = new Scanner(new File(path));
        int n = scanner.nextInt();

        int source = 2*n;
        int sink = 2*n+1;

        EdgeWeightedDigraph digraph = new EdgeWeightedDigraph(2*n+2);
        for (int i = 0; i < n; i++) {
            double duration = scanner.nextDouble();
            digraph.addEdge(new DirectedEdge(source, i, 0.0));
            digraph.addEdge(new DirectedEdge(i+n, sink, 0.0));
            digraph.addEdge(new DirectedEdge(i, i+n, duration));

            int m = scanner.nextInt();
            for (int j = 0; j < m; j++) {
                int p = scanner.nextInt();
                digraph.addEdge(new DirectedEdge(n+i, p, 0.0));
            }
        }

        AcyclicLP lp = new AcyclicLP(digraph, source);
        System.out.println(" job   start  finish");
        System.out.println("--------------------");
        for (int i = 0; i < n; i++) {
            System.out.printf("%4d %7.1f %7.1f\n", i, lp.distTo(i), lp.distTo(i+n));
        }
        System.out.printf("Finish time: %7.1f\n", lp.distTo(sink));
    }
}
