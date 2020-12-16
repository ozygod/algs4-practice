package com.ozygod.ST;

public abstract class ST<T, V> {

    public abstract void put(T key, V val);

    public abstract V get(T key);

    public abstract void delete(T key);

    public boolean contains(T key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public abstract int size();

    public abstract Iterable<T> keys();

    public abstract T min();

    public abstract T max();

    public abstract T floor(T key);

    public abstract T ceiling(T key);

    public abstract int rank(T key);

    public abstract T select(int i);

    public abstract void deleteMin();

    public abstract void deleteMax();

    public abstract int size(T lo, T hi);
}
