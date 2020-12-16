package com.ozygod.ST;

import com.ozygod.Queue;
import edu.princeton.cs.algs4.StdIn;

import java.io.File;
import java.util.Scanner;

public class LookupIndex {
    public static void main(String[] args) {
        File file = new File(("D:\\workspace\\java\\algs4-data\\movies.txt"));
        Scanner scanner = null;
        String sp = "/";
        ST<String, Queue<String>> st = new RedBlackBST<>();
        ST<String, Queue<String>> ts = new RedBlackBST<>();
        try {
            scanner = new Scanner(file);
            // compute frequency counts
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] lines = line.split(sp);
                String key = lines[0];
                for (int i = 1; i < lines.length; i++) {
                    String val = lines[i];
                    if (!st.contains(key)) st.put(key, new Queue<>());
                    if (!ts.contains(val)) ts.put(val, new Queue<>());
                    st.get(key).enqueue(val);
                    ts.get(val).enqueue(key);
                }
            }

            while(!StdIn.isEmpty()) {
                String query = StdIn.readLine();
                if (st.contains(query)) {
                    for (String s : st.get(query)) {
                        System.out.println(" "+s);
                    }
                } else {
                    System.out.println("key no exist!");
                }
                if (ts.contains(query)) {
                    for (String s : ts.get(query)) {
                        System.out.println(" " +s);
                    }
                } else {
                    System.out.println("val no exist!");
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
