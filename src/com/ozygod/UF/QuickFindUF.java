package com.ozygod.UF;

import java.io.File;
import java.util.Scanner;

public class QuickFindUF implements UF {
    private int[] ids;
    private int count;

    public QuickFindUF(int n) {
        this.count = n;
        this.ids = new int[n];
        for (int i = 0; i < n; i++) {
            this.ids[i] = i;
        }
    }

    @Override
    public void union(int p, int q) {
        int pid = find(p);
        int qid = find(q);
        if (pid == qid) return;

        for (int i = 0; i < ids.length; i++) {
            if (ids[i] == pid) ids[i] = qid;
        }
        count--;
    }

    @Override
    public int find(int p) {
        return ids[p];
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
            UF uf = new QuickFindUF(n);
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
