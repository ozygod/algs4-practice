package com.ozygod.ST;

import com.ozygod.Queue;

/**
 * 基于线性探测法的符号表
 * 根据key的hash值计算出索引值，相同索引值的键按照(i+1)%M的规则查找空位索引
 * @param <T>
 * @param <V>
 */
public class LinearProbingHashST<T, V> extends ST<T, V> {
    private static final int INIT_CAPACITY = 16;

    private T[] keys;
    private V[] vals;
    private int N; // 键值对总数
    private int M; // 线性探测表的大小

    public LinearProbingHashST() {
        this(INIT_CAPACITY);
    }

    public LinearProbingHashST(int capacity) {
        keys = (T[]) new Object[capacity];
        vals = (V[]) new Object[capacity];
        M = capacity;
        N = 0;
    }

    private int hash(T key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    private void resize(int size) {
        LinearProbingHashST<T, V> st = new LinearProbingHashST<>(size);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                st.put(keys[i], vals[i]);
            }
        }
        M = st.M;
        keys = st.keys;
        vals = st.vals;
    }

    @Override
    public int size() {
        return N;
    }

    public void put(T key, V val) {
        if (val == null) {
            delete(key);
            return;
        }
        if (N >= M/2) resize(M*2);

        int i;
        for (i = hash(key);  keys[i] != null ; i = (i+1)%M) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public V get(T key) {
        for (int i = hash(key); keys[i] != null ; i = (i+1)%M) {
            if (keys[i].equals(key)) {
                return vals[i];
            }
        }
        return null;
    }

    public void delete(T key) {
        if (!contains(key)) return;
        int i = hash(key);
        while (!key.equals(keys[i]))
            i = (i+1)%M;
        keys[i] = null;
        vals[i] = null;
        i = (i+1)%M;
        while (keys[i] != null) {
            T tmpK = keys[i];
            V tmpV = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;
            put(tmpK, tmpV);
            i = (i+1)%M;
        }
        N--;
        if (N > 0 && N <= M/8) resize(M/2);
    }

    @Override
    public Iterable<T> keys() {
        Queue<T> queue = new Queue<>();
        for (T key : keys) {
            if (key != null) {
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
