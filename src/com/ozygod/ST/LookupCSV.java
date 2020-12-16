package com.ozygod.ST;

import edu.princeton.cs.algs4.StdIn;

import java.io.File;
import java.util.Scanner;

public class LookupCSV {
    public static void main(String[] args) {
        ST<String, String> st = new RedBlackBST<>();
        File file = new File(("D:\\workspace\\java\\algs4-data\\amino.csv"));
        Scanner scanner = null;
        int key = 0, value = 3;
        try {
            scanner = new Scanner(file);
            // compute frequency counts
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");
                st.put(tokens[key], tokens[value]);
            }

            while(!StdIn.isEmpty()) {
                String query = StdIn.readString();
                if (st.contains(query)) {
                    System.out.println(st.get(query));
                } else {
                    System.out.println("no exist!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
