package com.ozygod.Digraph;

import com.ozygod.ST.RedBlackBST;
import com.ozygod.ST.ST;
import edu.princeton.cs.algs4.StdIn;

import java.io.File;
import java.util.Scanner;

public class SymbolDigraph {
    private ST<String, Integer> st;
    private String[] keys;
    private Digraph graph;

    public SymbolDigraph(String path, String delimiter) {
        st = new RedBlackBST<>();
        File file = new File(path);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] lines = scanner.nextLine().split(delimiter);
                for (String line : lines) {
                    if (!st.contains(line)) {
                        st.put(line, st.size());
                    }
                }
            }

            keys = new String[st.size()];
            for (String name : st.keys()) {
                keys[st.get(name)] = name;
            }

            graph = new Digraph(st.size());
            scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                String[] lines = scanner.nextLine().split(delimiter);
                int v = st.get(lines[0]);
                for (int i = 1; i < lines.length; i++) {
                    int w = st.get(lines[i]);
                    graph.addEdge(v, w);
                }
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException("invalid input format in Digraph constructor", ex);
        }
    }

    public boolean contains(String key) {
        return st.contains(key);
    }

    public int indexOf(String key) {
        return st.get(key);
    }

    public String name(int v) {
        return keys[v];
    }

    public Digraph graph() {
        return graph;
    }

    public static void main(String[] args) {
        String path = "D:\\workspace\\java\\algs4-data\\routes.txt";
        String delimiter = " ";
        SymbolDigraph symbolGraph = new SymbolDigraph(path, delimiter);
        Digraph graph = symbolGraph.graph();
        while (StdIn.hasNextLine()) {
            String source = StdIn.readLine();
            for (int w : graph.adj(symbolGraph.indexOf(source))) {
                System.out.println("   "+symbolGraph.name(w));
            }
        }

    }
}
