package com.ozygod.ST;

public class SET<T extends Comparable<T>> {
    private RedBlackBST<T, Object> st;
    public SET() {
        st = new RedBlackBST<>();
    }

    public void add(T key) {
        st.put(key, "");
    }

    public void delete(T key) {
        st.delete(key);
    }

    public boolean contains(T key) {
        return st.contains(key);
    }

    public boolean isEmpty() {
        return st.isEmpty();
    }

    public int size() {
        return st.size();
    }

    public String toString() {
        StringBuffer toStr = new StringBuffer();
        for (T key: st.keys()) {
            toStr.append(key);
        }
        return toStr.toString();
    }
}
