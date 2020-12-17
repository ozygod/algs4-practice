package com.ozygod.UF;

import java.io.File;
import java.util.Scanner;

public class WeightedQuickUnionUF implements UF {
    private int[] ids;
    private int[] sz;
    private int count;

    public WeightedQuickUnionUF(int n) {
        this.count = n;
        this.ids = new int[n];
        this.sz = new int[n];
        for (int i = 0; i < n; i++) {
            this.ids[i] = i;
            this.sz[i] = 1;
        }
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;

        if (sz[pRoot] < sz[qRoot]) {
            ids[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            ids[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }

        count--;
    }

    @Override
    public int find(int p) {
        while(p != ids[p]) p = ids[p];
        return p;
    }

    @Override
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public int count() {
        return count;
    }

    public static void main(String[] args) {
        File file = new File("D:\\workspace\\java\\algs4-data\\largeUF.txt");
        try {
            Scanner scanner = new Scanner(file);
            int n = scanner.nextInt();
            UF uf = new WeightedQuickUnionUF(n);
            while (scanner.hasNext()) {
                int p = scanner.nextInt();
                int q = scanner.nextInt();
                if (uf.connected(p, q)) continue;
                uf.union(p, q);
                System.out.println(p + " " + q);
            }
            System.out.println(uf.count() + " components");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
