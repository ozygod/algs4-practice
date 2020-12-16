package com.ozygod.Search;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

import java.util.Arrays;

public class BinarySearch {
    public static int rank(int key, int[] arr) {
        if (arr.length == 0) return -1;
        int lo = 0;
        int hi = arr.length-1;
        while (lo < hi) {
            int mid = lo + (hi-lo)/2;
            if (key > arr[mid]) lo = mid + 1;
            else if (key < arr[mid]) hi = mid -1;
            else return mid;
        }
        return -1;
    }

    public static int rank(int key, int[] arr, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = lo + (hi-lo)/2;
        if (key > arr[mid]) return rank(key, arr, mid+1, hi);
        else if (key < arr[mid]) return rank(key, arr, lo, mid-1);
        else return mid;
    }

    public static void main(String[] args) {
        int[] whileList = In.readInts(args[0]);
        Arrays.sort(whileList);
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            int index = BinarySearch.rank(key, whileList);
            System.out.println(index);
            int index2 = BinarySearch.rank(key, whileList, 0, whileList.length-1);
            System.out.println(index2);
        }
    }
}
