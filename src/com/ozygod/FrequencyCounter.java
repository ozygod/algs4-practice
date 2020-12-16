package com.ozygod;

import com.ozygod.ST.ST;
import com.ozygod.ST.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.util.Scanner;

public class FrequencyCounter {
    // Do not instantiate.
    private FrequencyCounter() { }

    /**
     * Reads in a command-line integer and sequence of words from
     * standard input and prints out a word (whose length exceeds
     * the threshold) that occurs most frequently to standard output.
     * It also prints out the number of words whose length exceeds
     * the threshold and the number of distinct such words.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int distinct = 0, words = 0;
        int minlen = 8;
//        RedBlackBST<String, Integer> st = new RedBlackBST<String, Integer>();
        ST<String, Integer> st = new SeparateChainingHashST<>();
//        ST<String, Integer> st = new LinearProbingHashST<>();
        File file = new File(("D:\\workspace\\java\\algs4-data\\tale.txt"));
        try {
            Scanner scanner = new Scanner(file);
            // compute frequency counts
            while (scanner.hasNext()) {
                String key = scanner.next();
                if (key.length() < minlen) continue;
                words++;
                if (st.contains(key)) {
                    st.put(key, st.get(key) + 1);
                }
                else {
                    st.put(key, 1);
                    distinct++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // find a key with the highest frequency count
        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            System.out.println(word);
            System.out.println(st.get(word));
            if (st.get(word) > st.get(max))
                max = word;
        }

        StdOut.println(max + " " + st.get(max));
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
    }
}
