package com.ozygod.Merge;

import edu.princeton.cs.algs4.StdOut;

/**
 * 归并排序
 */
public class Merge {
    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length-1);
        assert isSorted(a);
    }

    public static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi-lo)/2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid+1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        StdOut.println();
//        show(a);
        StdOut.print("参数：" +lo + " " + mid + " " + hi);
        StdOut.println();

        // 从小到大排序
        // 归并排序要点：aux，前半部分有序，后半部分有序，但前半部分不一定小于后半部分
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++]; // 当前半部分数据小于后半部分数据时，后取aux后半部分(mid+1 ~ hi)数据
            else if (j > hi) a[k] = aux[i++]; // 当后半部分数据小于前半部分数据时，后取aux前半部分(lo ~ mid+1)数据
            else if (less(aux[j], aux[i])) a[k] = aux[j++]; // 当后半部分数据小于前半部分数据时，先取aux后半部分(mid+1 ~ hi)数据
            else a[k] = aux[i++]; // 当前半部分数据小于后半部分数据时， 先取aux前半部分(lo ~ mid+1)数据

            show(a);
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
        String[] a = new String[]{"A", "E", "Q", "S","U","Y","E","I","N","O","S","T"};
        sort(a);
        assert isSorted(a);

        StdOut.println();
        show(a);
    }
}
