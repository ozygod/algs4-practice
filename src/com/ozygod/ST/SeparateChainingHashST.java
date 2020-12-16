package com.ozygod.ST;

import com.ozygod.Queue;

/**
 * 基于**拉链法**的符号表
 * 根据key的hash值得出索引值，相同索引的元素，基于链表连接在同一索引上
 * @param <T>
 * @param <V>
 */
public class SeparateChainingHashST<T, V> extends ST<T, V> {
    private static final int INIT_CAPACITY = 4;
    private int N; // 键总数
    private int M; // 链表条数
    private SequentialSearchST<T, V>[] st;


    public SeparateChainingHashST() {
        this(INIT_CAPACITY);
    }

    public SeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialSearchST<T, V>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST<>();
        }
    }

    private int hash(T key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void resize(int chains) {
        SeparateChainingHashST<T, V> tmp = new SeparateChainingHashST<>(chains);
        for (int i = 0; i < M; i++) {
            for (T key : st[i].keys()) {
                tmp.put(key, st[i].get(key));
            }
        }
        this.M = tmp.M;
        this.N = tmp.N;
        this.st = tmp.st;
    }

    public int size() {
        return N;
    }

    public V get(T key) {
        if (key == null) return null;
        return st[hash(key)].get(key);
    }

    public void put(T key, V value) {
        if (value == null) {
            delete(key);
            return;
        }
        if (N >= 10*M) resize(2*M);
        if (!contains(key)) N++;
        st[hash(key)].put(key, value);
    }

    public void delete(T key) {
        if (contains(key)) N--;
        st[hash(key)].delete(key);
        if (M > INIT_CAPACITY && N <= 2*M) resize(M/2);
    }

    public Iterable<T> keys() {
        Queue<T> queue = new Queue<>();
        for (int i = 0; i < M; i++) {
            for (T key : st[i].keys()) {
                queue.enqueue(key);
            }
        }
        return queue;
    }

    @Override
    public T min() {
        return null;
    }

    @Override
    public T max() {
        return null;
    }

    @Override
    public T floor(T key) {
        return null;
    }

    @Override
    public T ceiling(T key) {
        return null;
    }

    @Override
    public int rank(T key) {
        return 0;
    }

    @Override
    public T select(int i) {
        return null;
    }

    @Override
    public void deleteMin() {

    }

    @Override
    public void deleteMax() {

    }

    @Override
    public int size(T lo, T hi) {
        return 0;
    }


}
