package com.ozygod.ST;

import com.ozygod.Queue;

public class RedBlackBST<T extends Comparable<T>, V> extends ST<T, V> {
    private Node root;

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        private T key;
        private V value;
        private Node left, right;
        private int size;
        boolean color;

        public Node(T key, V value, int size, boolean color) {
            this.key = key;
            this.value = value;
            this.size = size;
            this.color = color;
        }
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
        return x;
    }

    private boolean isRed(Node h) {
        if (h == null) return false;
        return h.color;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(T key) {
        return get(key) != null;
    }

    public V get(T key) {
        return get(key, root);
    }

    private V get(T key, Node x) {
        while(x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x.value;
            else if (cmp > 0) x = x.right;
            else x = x.left;
        }
        return null;
    }

    public void put(T key, V value) {
        if (value == null) {
            delete(key);
            return;
        }
        root = put(key, value, root);
        root.color = BLACK;
    }

    private Node put(T key, V value, Node x) {
        if (x == null) return new Node(key, value, 1, RED);
        int cmp = key.compareTo(x.key);
        if (cmp > 0) x.right = put(key, value, x.right);
        else if (cmp < 0) x.left = put(key, value, x.left);
        else x.value = value;

        if (isRed(x.right) && !isRed(x.left)) x = rotateLeft(x);
        else if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        else if (isRed(x.left) && isRed(x.right)) flipColors(x);

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

    private Node balance(Node x) {
        if (isRed(x.right)) x = rotateLeft(x);
        else if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        else if (isRed(x.right) && isRed(x.left)) flipColors(x);

        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMin() {
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node deleteMin(Node x) {
        if (x == null) return null;
        if (x.left == null) return null;
        if (!isRed(x.left) && !isRed(x.left.left))
            x = moveRedLeft(x);
        x.left = deleteMin(x.left);
        return balance(x);
    }

    private Node moveRedLeft(Node h) {
        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    public void deleteMax() {
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
    }

    @Override
    public int size(T lo, T hi) {
        return 0;
    }

    private Node deleteMax(Node h) {
        if (isRed(h.left)) h = rotateRight(h);
        if (h.right == null) return null;
        if (!isRed(h.right) && !isRed(h.right.left)) h = moveRedRight(h);
        h.right = deleteMax(h.right);
        return balance(h);
    }

    private Node moveRedRight(Node h) {
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    public void delete(T key) {
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = delete(key, root);
        if (!isEmpty()) root.color = BLACK;
    }

    public Node delete(T key, Node x) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            if (!isRed(x.left) && !isRed(x.left.left))
                x = moveRedLeft(x);
            x.left = delete(key, x.left);
        }
        else {
            if (isRed(x.left))
                x = rotateRight(x);
            if (cmp == 0 && x.right == null)
                return null;
            if (!isRed(x.right) && !isRed(x.right.left))
                x = moveRedRight(x);
            if (cmp == 0) {
                Node m = min(x.right);
                x.value = m.value;
                x.key = m.key;
                x.right = deleteMin(x.right);
            }
            else x.right = delete(key, x.right);
        }

        return balance(x);
    }

    public Iterable<T> keys() {
        if (isEmpty()) return new Queue<T>();
        return keys(min(), max());
    }

    public Iterable<T> keys(T min, T max) {
        if (min == null) return null;
        if (max == null) return null;
        Queue<T> queue = new Queue<>();
        keys(root, queue, min, max);
        return queue;
    }

    private void keys(Node x, Queue<T> queue, T lo, T hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
        if (cmphi > 0) keys(x.right, queue, lo, hi);
    }

    private boolean isBST(Node x, T min, T max) {
        if (x == null) return false;
        if (min != null && x.key.compareTo(min) <= 0) return false;
        if (max != null && x.key.compareTo(max) >= 0) return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    private boolean is23(Node x) {
        if (x == null) return false;
        if (isRed(x.right)) return false;
        else if (x != root && isRed(x) && isRed(x.left)) return false;
        return is23(x.left) && is23(x.right);
    }

    private boolean isBalanced() {
        int black = 0;
        Node x = root;
        while(x != null) {
            if (!isRed(x)) black++;
            x = x.left;
        }
        return isBalanced(root, black);
    }

    private boolean isBalanced(Node x, int black) {
        if (x == null) return black == 0;
        if (!isRed(x)) black--;
        return isBalanced(x.left, black) && isBalanced(x.right, black);
    }
}
