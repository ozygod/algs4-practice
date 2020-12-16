package com.ozygod.PriorityQueue;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class HeapSort {
    private HeapSort() {}

    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = n/2; i >= 1; i--) {
            sink(a, i, n);
        }
        while (n > 1) {
            exch(a, 1, n--);
            sink(a, 1, n);
        }
    }

    // 由下至上的二叉堆排序（上浮）
    private static void swim(Comparable[] a, int k) {
        while (k > 1 && less(a,k / 2, k)) {
            exch(a, k, k/2);
            k = k / 2;
        }
    }

    // 由上至下的二叉堆排序（下沉）
    private static void sink(Comparable[] a, int k, int length) {
        while (2 * k <= length) {
            int j = 2 * k;
            if (j < length && less(a, j, j + 1)) j++;
            if (!less(a, k, j)) break;
            exch(a, k, j);
            k = j;
        }
    }

    private static boolean less(Comparable[] a, int i, int j) {
        return a[i-1].compareTo(a[j-1]) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i-1];
        a[i-1] = a[j-1];
        a[j-1] = swap;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }

    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i <= a.length; i++) {
            if (less(a, i, i-1)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        sort(a);
        assert isSorted(a);

        show(a);
    }
}
