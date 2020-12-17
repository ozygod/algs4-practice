package com.ozygod.UF;

import java.io.File;
import java.util.Scanner;

public class QuickUnionUF implements UF {
    private int[] ids;
    private int count;

    public QuickUnionUF(int n) {
        this.count = n;
        this.ids = new int[n];
        for (int i = 0; i < n; i++) {
            this.ids[i] = i;
        }
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;
        ids[pRoot] = qRoot;
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
        File file = new File("D:\\workspace\\java\\algs4-data\\tinyUF.txt");
        try {
            Scanner scanner = new Scanner(file);
            int n = scanner.nextInt();
            UF uf = new QuickUnionUF(n);
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
