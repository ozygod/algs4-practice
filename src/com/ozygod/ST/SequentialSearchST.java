package com.ozygod.ST;

import com.ozygod.Queue;

public class SequentialSearchST<T, V> {
    private int n;
    private Node<T, V> first;

    public SequentialSearchST() {
    }

    public void put(T key, V val) {
        if (val == null) {
            delete(key);
            return;
        }
        for (Node x = first; x != null; x = x.getNext()) {
            if (key.equals(x.getKey())) {
                x.setValue(val);
                return;
            }
        }
        first = new Node<>(key, val, first);
        n++;
    }

    public V get(T key) {
        for (Node x = first; x != null; x = x.getNext()) {
            if (key.equals(x.getKey())) {
                return (V)x.getValue();
            }
        }
        return null;
    }

    public void delete(T key) {
        if (key.equals(first.getKey())) {
            first = first.getNext();
            return;
        }
        first = delete(first, key);
    }

    private Node delete(Node x, T key) {
        if (x == null) return null;
        if (key.equals(x.getKey())) {
            n--;
            return x.getNext();
        }
        x.setNext(delete(x.getNext(), key));
        return x;
    }

    public boolean contains(T key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return n;
    }

    public Iterable<T> keys() {
        Queue<T> queue = new Queue<T>();
        for (Node x = first; x != null; x = x.getNext())
            queue.enqueue((T)x.getKey());
        return queue;
    }
}
