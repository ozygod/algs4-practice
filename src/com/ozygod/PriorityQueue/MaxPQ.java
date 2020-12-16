package com.ozygod.PriorityQueue;

import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class MaxPQ<T> {
    private static final int defaultCapacity = 1;

    private T[] pq;
    private int count;
    private Comparator<T> comparator;

    public MaxPQ() {
        this(defaultCapacity);
    }

    public MaxPQ(int initCapacity) {
        pq = (T[]) new Object[initCapacity + 1];
        count = 0;
    }

    public MaxPQ(int initCapacity, Comparator<T> comparator) {
        this.comparator = comparator;
        pq = (T[]) new Object[initCapacity + 1];
        count = 0;
    }

    public MaxPQ(Comparator<T> comparator) {
        this(defaultCapacity, comparator);
    }

    public MaxPQ(T[] a) {
        pq = (T[]) new Object[a.length + 1];
        count = a.length;
        for (int i = 0; i < count; i++) {
            pq[i] = a[i];
        }
        // 将最大元素快速移到1位置
        for (int i = count / 2; i >= 1; i--) {
            sink(i);
        }

        assert isMaxHeap();
    }

    public void insert(T v) {
        if (count == pq.length - 1) resize(2 * pq.length);
        pq[++count] = v;
        swim(count);
        assert isMaxHeap();
    }

    public T max() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    public T delMax() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        T v = pq[1];
        exch(1, count--);
        sink(1);
        pq[count + 1] = null;
        if ((count > 0) && (count == (pq.length - 1) / 4)) resize(pq.length / 2);
        assert isMaxHeap();
        return v;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    public void show() {
        for (int i = 1; i <= count; i++) {
            StdOut.print("index: " + i + " value:" + pq[i] + " ");
        }
    }

    public void resize(int capacity) {
        assert capacity > count;
        T[] temp = (T[]) new Object[capacity];
        for (int i = 1; i <= count; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    // 由下至上的二叉堆排序（上浮）
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    // 由上至下的二叉堆排序（下沉）
    private void sink(int k) {
        while (2 * k <= count) {
            int j = 2 * k;
            if (j < count && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        if (comparator == null) {
            return ((Comparable<T>) pq[i]).compareTo(pq[j]) < 0;
        } else {
            return comparator.compare(pq[i], pq[j]) < 0;
        }
    }

    private void exch(int i, int j) {
        T swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    // is pq[1..n] a max heap?
    private boolean isMaxHeap() {
        for (int i = 1; i <= count; i++) {
            if (pq[i] == null) return false;
        }
        for (int i = count + 1; i < pq.length; i++) {
            if (pq[i] != null) return false;
        }
        if (pq[0] != null) return false;
        return isMaxHeapOrdered(1);
    }

    // is subtree of pq[1..n] rooted at k a max heap?
    private boolean isMaxHeapOrdered(int k) {
        if (k > count) return true;
        int left = 2 * k;
        int right = 2 * k + 1;
        if (left <= count && less(k, left)) return false;
        if (right <= count && less(k, right)) return false;
        return isMaxHeapOrdered(left) && isMaxHeapOrdered(right);
    }


    public static void main(String[] args) {
        MaxPQ<String> pq = new MaxPQ<String>();
        String[] inputs = new String[]{"2", "1", "3", "8", "6", "7", "-", "9", "-", "10", "15", "13", "-", "11", "12", "14", "-", "-", "-"};
        for (int i = 0; i < inputs.length; i++) {
            String item = inputs[i];
            if (!item.equals("-")) pq.insert(item);
            else if (!pq.isEmpty()) StdOut.print(pq.delMax() + " ");
        }
        StdOut.println("(" + pq.size() + " left on pq)");
        pq.show();
    }
}
