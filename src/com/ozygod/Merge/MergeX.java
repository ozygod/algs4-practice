package com.ozygod.Merge;

import edu.princeton.cs.algs4.StdOut;

public class MergeX {
    private static final int CUTOFF = 7;
    public static void sort(Comparable[] a) {
        Comparable[] aux = a.clone();
        sort(a, aux, 0, a.length-1);
        assert isSorted(a);
    }

    public static void sort(Comparable[] src, Comparable[] dst, int lo, int hi) {
        if (hi <= lo + CUTOFF) {
            insertionSort(dst, lo, hi);
            return;
        }
        int mid = lo + (hi-lo)/2;
        sort(dst, src, lo, mid);
        sort(dst, src, mid+1, hi);

        if (!less(src[mid+1], src[mid])) {
            System.arraycopy(src, lo, dst, lo, hi-lo+1);
        }
        merge(src, dst, lo, mid, hi);
    }

    public static void merge(Comparable[] src, Comparable[] dst, int lo, int mid, int hi) {
        assert isSorted(src, lo, mid);
        assert isSorted(src, mid+1, hi);

//        StdOut.println();
//        show(a);
//        StdOut.print("参数：" +lo + " " + mid + " " + hi);
//        StdOut.println();

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) dst[k] = src[j++];
            else if (j > hi) dst[k] = src[i++];
            else if (less(src[j], src[i])) dst[k] = src[j++];
            else dst[k] = src[i++];

//            show(a);
        }

    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i < hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j-1]) ; j--) {
                exch(a, j, j-1);
            }
        }
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
        String[] a = new String[]{"1", "0", "2"};
        sort(a);
        assert isSorted(a);

        StdOut.println();
        show(a);
    }
}
