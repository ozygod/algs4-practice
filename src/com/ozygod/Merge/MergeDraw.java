package com.ozygod.Merge;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class MergeDraw {

    public static void sort(double[] a) {
        double[] aux = new double[a.length];
        sort(a, aux, 0, a.length-1);
        assert isSorted(a);
    }

    public static void sort(double[] a, double[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi-lo)/2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid+1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static void merge(double[] a, double[] aux, int lo, int mid, int hi) {
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

            show(a, lo, hi);
//            show(a);
        }

    }

    private static boolean less(double v, double w) {
        return v < w;
    }

    private static void exch(double[] a, int i, int j) {
        double t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static void show(double[] a, int i, int min) {
        StdDraw.setYscale(-a.length + i + 0.8, i + 0.8);
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        for (int k = 0; k < i; k++)
            StdDraw.filledRectangle(k, a[k] * 0.3, 0.25, a[k] * 0.3);
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int k = i; k < a.length; k++)
            StdDraw.filledRectangle(k, a[k] * 0.3, 0.25, a[k] * 0.3);
        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.filledRectangle(min, a[min] * 0.3, 0.25, a[min] * 0.3);
    }

    private static boolean isSorted(double[] a) {
        for (int i = 0; i < a.length; i++) {
            if (less(a[i], a[i-1])) return false;
        }
        return true;
    }

    private static boolean isSorted(double[] a, int start, int end) {
        for (int i = start+1; i <= end; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 10;
        StdDraw.setCanvasSize(160, 640);
        StdDraw.setXscale(-1, n+1);
        StdDraw.setPenRadius(0.006);
        double[] a = new double[n];
        for (int i = 0; i < n; i++)
            a[i] = StdRandom.uniform(0.0, 1.0);
        sort(a);
    }
}
