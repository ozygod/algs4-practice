package com.ozygod.ST;

public class Node<T,V> {
    private T key;
    private V value;
    private Node next;

    public Node(T key, V value, Node next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
