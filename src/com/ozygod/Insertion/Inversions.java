package com.ozygod.Insertion;

import edu.princeton.cs.algs4.StdOut;

public class Inversions {
    public static long count(int[] a) {
        int[] b = new int[a.length];
        int[] aux = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
        }
        long inversions = count(a, b, aux, 0, a.length-1);
        return inversions;
    }

    public static long count(int[] a, int[] b, int[] aux, int lo, int hi) {
        long inversions = 0;
        if (hi <= lo) return inversions;
        int mid = lo + (hi-lo)/2;
        inversions += count(a, b, aux, lo, mid);
        inversions += count(a, b, aux, mid+1, hi);
        inversions += merge(b, aux, lo, mid, hi);
        assert inversions == brute(a, lo, hi);
        return inversions;
    }

    public static long merge(int[] a, int[] aux, int lo, int mid, int hi) {
        long inversions = 0;

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) { a[k] = aux[j++]; inversions += (mid-i+1); }
            else a[k] = aux[i++];
        }
        return inversions;
    }

    private static boolean less(int v, int w) {
        return v < w;
    }

    private static long brute(int[]a, int lo, int hi) {
        long inversions = 0;
        for (int i = lo; i <= hi; i++) {
            for (int j = i+1; j <= hi; j++)
                if (a[j] < a[i]) inversions++;
        }
        return inversions;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1,3,4,5,7,9};
        int n = a.length;
        Integer[] b = new Integer[n];
        for (int i = 0; i < n; i++)
            b[i] = a[i];
        StdOut.println(Inversions.count(a));
    }
}
