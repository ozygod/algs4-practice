package com.ozygod.Merge;

public class MergeSizes {
    private static void merge(int lo, int mid, int hi) {
        System.out.print(hi-lo+1);
        System.out.print(" ");
    }
    private static void topDownMergesort(int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi-lo)/2;
        topDownMergesort(lo, mid);
        topDownMergesort(mid+1, hi);
        merge(lo, mid, hi);
    }
    private static void topDownMergesort(Comparable[] a) {
        topDownMergesort(0, a.length-1);
    }

    private static void bottomUpMergesort(int hi) {
        for (int sz = 1; sz <= hi; sz = sz + sz) {
            for (int lo = 0; lo <= hi-sz; lo+= sz+sz) {
                merge(lo, lo+sz-1, Math.min(lo+sz+sz-1, hi));
            }
        }
    }

    private static void bottomUpMergesort(Comparable[] a) {
        bottomUpMergesort(a.length-1);
    }

    public static void main(String[] args) {
        int n = 39;
        String[] a = new String[n];
        MergeSizes.topDownMergesort(a);
        System.out.println();
        MergeSizes.bottomUpMergesort(a);
        System.out.println();
    }
}
