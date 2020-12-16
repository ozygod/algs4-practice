package com.ozygod.ST;

public class SparseVector {
    private ST<Integer, Double> st;

    public SparseVector() {
        st = new RedBlackBST<>();
    }

    public int size() {
        return st.size();
    }
    public void put(int i, double x) {
        if (x == 0.0) st.delete(i);
        st.put(i, x);
    }
    public double get(int i) {
        if (!st.contains(i)) return 0.0;
        return st.get(i);
    }
    public double dot(double[] that) {
        double sum = 0.0;
        for (int i : st.keys()) {
            sum += that[i]*this.get(i);
        }
        return sum;
    }
    public double dot(SparseVector that) {
        double sum = 0.0;
        if (this.st.size() <= that.st.size()) {
            for (int i : this.st.keys())
                if (that.st.contains(i)) sum += this.get(i)*that.get(i);
        } else  {
            for (int i: that.st.keys())
                if (this.st.contains(i)) sum += that.get(i)*this.get(i);
        }
        return sum;
    }
}
