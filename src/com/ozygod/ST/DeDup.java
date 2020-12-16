package com.ozygod.ST;

import java.io.File;
import java.util.Scanner;

public class DeDup {
    public static void main(String[] args) {
        SET<String> set = new SET<>();
        File file = new File(("D:\\workspace\\java\\algs4-data\\tinyTale.txt"));
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            // compute frequency counts
            while (scanner.hasNext()) {
                String key = scanner.next();
                if (!set.contains(key)) {
                    set.add(key);
                    System.out.print(key+" ");
                }
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
