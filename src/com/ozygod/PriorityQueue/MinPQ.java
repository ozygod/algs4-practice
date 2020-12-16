package com.ozygod.PriorityQueue;

import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class MinPQ<T> {
    private static final int defaultCapacity = 1;

    private T[] pq;
    private int count;
    private Comparator<T> comparator;

    public MinPQ() {
        this(defaultCapacity);
    }

    public MinPQ(int initCapacity) {
        pq = (T[])new Object[initCapacity+1];
        count = 0;
    }

    public MinPQ(int initCapacity, Comparator<T> comparator) {
        this.comparator = comparator;
        pq = (T[]) new Object[initCapacity+1];
        count = 0;
    }

    public MinPQ(Comparator<T> comparator) {
        this(defaultCapacity, comparator);
    }

    public MinPQ(T[] a) {
        pq = (T[])new Object[a.length+1];
        count = a.length;
        for (int i = 0; i < count; i++) {
            pq[i] = a[i];
        }
        // 将最大元素快速移到1位置
        for (int i = count/2; i >= 1; i--) {
            sink(i);
        }

        assert isMinHeap();
    }

    public void insert(T v) {
        if (count == pq.length-1) resize(2*pq.length);
        pq[++count] = v;
        swim(count);
        assert isMinHeap();
    }

    public T min() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    public T delMin() {
        if (isEmpty()) throw new  NoSuchElementException("Priority queue underflow");
        T v = pq[1];
        exch(1, count--);
        sink(1);
        pq[count+1]=null;
        if ((count>0) && (count == (pq.length-1)/4)) resize(pq.length/2);
        assert isMinHeap();
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
            StdOut.print(pq[i] + " ");
        }
    }

    public void resize(int capacity) {
        assert capacity > count;
        T[] temp = (T[])new Object[capacity];
        for (int i = 1; i <= count; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    private void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            exch(k/2, k);
            k = k/2;
        }
    }

    /**
     * 将k位置的元素移到右边元素都小于它的位置
     * @param k
     */
    private void sink(int k) {
        while(2*k <= count) {
            int j = 2*k;
            if (j < count && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean greater(int i, int j) {
        if (comparator == null) {
            return ((Comparable<T>) pq[i]).compareTo(pq[j]) > 0;
        } else {
            return comparator.compare(pq[i], pq[j]) > 0;
        }
    }

    private void exch(int i, int j) {
        T swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    // is pq[1..n] a min heap?
    private boolean isMinHeap() {
        for (int i = 1; i <= count; i++) {
            if (pq[i] == null) return false;
        }
        for (int i = count+1; i < pq.length; i++) {
            if (pq[i] != null) return false;
        }
        if (pq[0] != null) return false;
        return isMinHeapOrdered(1);
    }

    // is subtree of pq[1..n] rooted at k a min heap?
    private boolean isMinHeapOrdered(int k) {
        if (k > count) return true;
        int left = 2*k;
        int right = 2*k + 1;
        if (left  <= count && greater(k, left))  return false;
        if (right <= count && greater(k, right)) return false;
        return isMinHeapOrdered(left) && isMinHeapOrdered(right);
    }


    public static void main(String[] args) {
        MinPQ<String> pq = new MinPQ<String>();
        String[] inputs = new String[]{"2","1","3","4","8","6","5","-"};
        for (int i = 0; i < inputs.length; i++) {
            String item = inputs[i];
            if (!item.equals("-")) pq.insert(item);
            else if (!pq.isEmpty()) StdOut.print(pq.delMin() + " ");
        }
        StdOut.println("(" + pq.size() + " left on pq)");
        pq.show();
    }
}
