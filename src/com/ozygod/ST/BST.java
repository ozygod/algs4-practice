package com.ozygod.ST;

/**
 * 二叉查找树
 * @param <T>
 * @param <V>
 */
public class BST<T extends Comparable<T>, V> {
    private Node root;

    private class Node {
        private T key;
        private V value;
        private Node left, right;
        private int size;

        public Node(T key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    public V get(T key) {
        return get(key, root);
    }

    private V get(T key, Node x) {
        if (x == null) return null;
        int cmp = x.key.compareTo(key);
        if (cmp == 0) return x.value;
        else if (cmp > 0) return get(key, x.right);
        else return get(key, x.left);
    }

    public void put(T key, V value) {
        root = put(key, value, root);
    }

    private Node put(T key, V value, Node x) {
        if (x == null) return new Node(key, value, 1);
        int cmp = key.compareTo(x.key);
        if (cmp > 0) x.right = put(key, value, x.right);
        else if (cmp < 0) x.left = put(key, value, x.left);
        else x.value = value;
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public T min() {
        Node x = min(root);
        return x == null ? null : x.key;
    }

    private Node min(Node x) {
        if (x == null) return null;
        if (x.left == null) return x;
        else return min(x.left);
    }

    public T max() {
        Node x = max(root);
        return x == null ? null : x.key;
    }

    private Node max(Node x) {
        if (x == null) return null;
        if (x.right == null) return x;
        else return max(x.right);
    }

    public T floor(T key) {
        Node x = floor(key, root);
        return x == null ? null : x.key;
    }

    // 取小于等于key的最大键，向下取整
    private Node floor(T key, Node x) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        else if (cmp < 0) {
            Node t = floor(key, x.right);
            if (t != null) return t;
            else return x;
        }
        else return floor(key, x.left);
    }

    public T ceiling(T key) {
        Node x = ceiling(key, root);
        return x == null ? null : x.key;
    }

    // 取大于等于key的最小值，向上取整
    private Node ceiling(T key, Node x) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        else if (cmp > 0) {
            Node t = ceiling(key, x.left);
            if (t != null) return t;
            else return x;
        }
        else return ceiling(key, x.right);
    }

    public T select(int k) {
        Node x = select(k, root);
        return x == null ? null : x.key;
    }

    private Node select(int k, Node x) {
        if (x == null) return null;
        int t = size(x.left);
        if (t > k) return select(k, x.left);
        else if (t < k) return select(k-t-1, x.right);
        else return x;
    }

    public int rank(T key) {
        return rank(key, root);
    }

    private int rank(T key, Node x) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else if (cmp < 0) return rank(key, x.left);
        else return size(x.left);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if(x == null) return null;
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(T key) {
        root = delete(key, root);
    }

    public Node delete(T key, Node x) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(key, x.left);
        else if (cmp > 0) x.right = delete(key, x.right);
        else {
            if (x.left == null) {
                return x.right;
            }
            if (x.right == null) {
                return x.left;
            }
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }
}
