package com.ozygod.ST;

import com.ozygod.Queue;

public class BinarySearchST<T extends Comparable<T>, V> {
    private T[] keys;
    private V[] values;
    private int size;

    public BinarySearchST(int capacity) {
        keys = (T[]) new Comparable[capacity];
        values = (V[]) new Object[capacity];
    }

    private void resize(int capacity) {
        assert capacity >= size;
        T[] tmpK = (T[]) new Comparable[capacity];
        V[] tmpV = (V[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            tmpK[i] = keys[i];
            tmpV[i] = values[i];
        }
        keys = tmpK;
        values = tmpV;
    }

    public void put(T key, V val) {
        if (val == null) {
            delete(key);
            return;
        }
        int i = rank(key);
        if (i < size && keys[i].compareTo(key) == 0) {
            values[i] = val;
            return;
        }

        if (size == keys.length) {
            resize(2*keys.length);
        }

        for (int j = size; j > i; j--) {
            keys[j] = keys[j-1];
            values[j] = values[j-1];
        }
        keys[i] = key;
        values[i] = val;
        size++;
    }


    public V get(T key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < size && keys[i].compareTo(key) == 0) return values[i];
        return null;
    }

    public boolean contains(T key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    public T min() {
        return keys[0];
    }

    public T max() {
        return keys[size-1];
    }

    // 小于等于key的最大键
    public T floor(T key) {
        int i = rank(key);
        if (i < size && keys[i].compareTo(key) == 0) {
            return keys[i];
        }
        if (i == 0) return null;
        return keys[i-1];
    }

    // 大于等于key的最小键
    public T ceiling(T key) {
        int i = rank(key);
        if (i == size) return null;
        return keys[i];
    }

    public int rank(T key) {
        return rank(key, 0, keys.length-1);
    }

    public int rank(T key, int lo, int hi) {
        if (lo > hi) return lo;
        int mid = lo + (hi-lo)/2;
        int cmp = key.compareTo(keys[mid]);
        if (cmp > 0) return rank(key, mid+1, hi);
        else if (cmp < 0) return rank(key, lo, mid-1);
        else return mid;
    }

    public T select(int i) {
        return keys[i];
    }

    public Iterable<T> keys() {
        return keys(min(), max());
    }

    public Iterable<T> keys(T lo, T hi) {
        Queue<T> queue = new Queue<>();
        if (lo.compareTo(hi) > 0) return queue;
        for (int i = rank(lo); i < rank(hi); i++) {
            queue.enqueue(keys[i]);
        }
        if (contains(hi)) queue.enqueue((keys[rank(hi)]));
        return queue;
    }

    public void delete(T key) {
        if (isEmpty()) return;

        int i = rank(key);
        if (i == size || keys[i].compareTo(key) != 0 ) {
            return;
        }

        for (int j = i; j < size-1; j++) {
            keys[j] = keys[j+1];
            values[j] = values[j+1];
        }
        size--;
        keys[size] = null;
        values[size] = null;
        if (size > 0 && size == keys.length/4) resize(keys.length/2);
    }

    public void deleteMin() {
        delete(min());
    }

    public void deleteMax() {
        delete(max());
    }

    public int size(T lo, T hi) {
        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo);
        }
    }
}
