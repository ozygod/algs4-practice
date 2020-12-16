package com.ozygod.Merge;

import edu.princeton.cs.algs4.StdOut;

public class MergeBU {
    private static Comparable[] aux;
    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, a.length-1);
        assert isSorted(a);
    }

    public static void sort(Comparable[] a, int hi) {
        for (int sz = 1; sz <= hi ; sz = sz + sz) {
            for (int lo = 0; lo <= hi-sz; lo+= sz+sz) {
                merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, hi));
            }
        }
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

//        StdOut.println();
//        show(a);
//        StdOut.print("参数：" +lo + " " + mid + " " + hi);
//        StdOut.println();

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];

//            show(a);
        }

    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length-1);
    }

    private static boolean isSorted(Comparable[] a, int start, int end) {
        for (int i = start+1; i <= end; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] a = new String[]{"M", "E", "R", "G", "E", "S","O","R","T","E","X","A","M","P","L","E"};
        sort(a);
        assert isSorted(a);

        StdOut.println();
        show(a);
    }
}
