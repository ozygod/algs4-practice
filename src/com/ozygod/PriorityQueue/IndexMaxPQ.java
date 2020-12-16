package com.ozygod.PriorityQueue;

import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class IndexMaxPQ<T> {
    private static final int defaultCapacity = 30;

    private T[] items;
    private int[] itemPqIndex; // 值为对应元素在pqIndex中的索引，索引与items的索引相对应

    private int[] pqIndex; // 值为对应元素在items中的索引

    private int count;
    private Comparator<T> comparator;

    public IndexMaxPQ() {
        this(defaultCapacity);
    }

    public IndexMaxPQ(int initCapacity) {
        items = (T[]) new Object[initCapacity + 1];
        itemPqIndex = new int[initCapacity + 1];
        pqIndex = new int[initCapacity + 1];
        count = 0;
        for (int i = 0; i <= initCapacity; i++) {
            itemPqIndex[i] = -1;
        }
    }

    public IndexMaxPQ(int initCapacity, Comparator<T> comparator) {
        this.comparator = comparator;
        items = (T[]) new Object[initCapacity + 1];
        itemPqIndex = new int[initCapacity + 1];
        pqIndex = new int[initCapacity + 1];
        count = 0;
        for (int i = 0; i <= initCapacity; i++) {
            itemPqIndex[i] = -1;
        }
    }

    public IndexMaxPQ(Comparator<T> comparator) {
        this(defaultCapacity, comparator);
    }

    public IndexMaxPQ(T[] a) {
        items = (T[]) new Object[a.length + 1];
        count = a.length;
        for (int i = 0; i < count; i++) {
            items[i] = a[i];
            itemPqIndex[i] = i;
            pqIndex[i] = i;
        }
        // 将最大元素快速移到1位置
        for (int i = count / 2; i >= 1; i--) {
            sink(i);
        }

    }

    public void insert(int i,T v) {
        count++;
        items[i] = v;
        itemPqIndex[i] = count;
        pqIndex[count] = i;
        swim(count);
    }

    public T max() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return items[pqIndex[1]];
    }

    public T delMax() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        int max = pqIndex[1];

        exch(1, count--);
        sink(1);

        T v = items[max];

        assert pqIndex[count + 1] == max;
        items[max] = null;
        itemPqIndex[max] = -1;
        pqIndex[count + 1] = -1;

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
            StdOut.print("index: " + i + " value:" + items[pqIndex[i]] + " ");
        }
        System.out.println();
        for (int i = 1; i < items.length; i++) {
            StdOut.print("index: " + i + " value:" + items[i] + " ");
        }
    }

    // 由下至上的二叉堆排序（上浮）
    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k/2);
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
            return ((Comparable<T>) items[pqIndex[i]]).compareTo(items[pqIndex[j]]) < 0;
        } else {
            return comparator.compare(items[pqIndex[i]], items[pqIndex[j]]) < 0;
        }
    }

    private void exch(int i, int j) {
        int swap = pqIndex[i];
        pqIndex[i] = pqIndex[j];
        pqIndex[j] = swap;
        itemPqIndex[pqIndex[i]] = i;
        itemPqIndex[pqIndex[j]] = j;
    }


    public static void main(String[] args) {
        IndexMaxPQ<String> pq = new IndexMaxPQ<String>();
        String[] inputs = new String[]{"2", "1", "3", "8", "6", "7", "-", "9", "-", "10", "15", "13", "-", "11", "12", "14", "-", "-", "-"};
//        String[] inputs = new String[]{"2", "1", "3"};
        for (int i = 0; i < inputs.length; i++) {
            String item = inputs[i];
            if (!item.equals("-")) pq.insert(i, item);
            else if (!pq.isEmpty()) StdOut.print(pq.delMax() + " ");
        }
        StdOut.println("(" + pq.size() + " left on pq)");
        pq.show();
    }
}
