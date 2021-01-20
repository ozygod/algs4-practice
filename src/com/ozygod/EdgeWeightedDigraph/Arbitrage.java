package com.ozygod.EdgeWeightedDigraph;

import java.io.File;
import java.util.Scanner;

/**
 * 套汇
 */
public class Arbitrage {
    private Arbitrage(){}

    public static void main(String[] args) throws Exception {
        String path = "D:\\workspace\\java\\algs4-data\\rates.txt";
        Scanner scanner = new Scanner(new File(path));
        int n = scanner.nextInt();

        String[] names = new String[n];
        EdgeWeightedDigraph digraph = new EdgeWeightedDigraph(n);
        for (int i = 0; i < n; i++) {
            names[i] = scanner.next();
            for (int j = 0; j < n; j++) {
                double rate = scanner.nextDouble();
                DirectedEdge edge = new DirectedEdge(i, j, -Math.log(rate));
                digraph.addEdge(edge);
            }
        }

        BellmanFordSP sp = new BellmanFordSP(digraph, 0);
        if (sp.hasNegativeCycle()) {
            double stake = 1000.0;
            for (DirectedEdge edge : sp.negativeCycle()) {
                System.out.printf("%10.5f %s", stake, names[edge.from()]);
                stake *= Math.exp(-edge.weight());
                System.out.printf("= %10.5f %s\n", stake, names[edge.to()]);
            }
        } else System.out.println("No arbitrage opportunity");
    }
}
