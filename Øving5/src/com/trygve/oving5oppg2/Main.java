package com.trygve.oving5oppg2;

import java.util.HashMap;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        /*
        long time = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) Prime.getPrime(100000);
        System.out.println((System.currentTimeMillis() - time) / 10.0);
        */

        HashTable hashTable = new HashTable(5500000);

        HashMap hashMap = new HashMap(5500000);

        Random random = new Random();
        int[] source = new int[5000000];
        for (int i = 0;i < source.length;i++) source[i] = random.nextInt();

        long time = System.currentTimeMillis();
        for (int i = 0;i < source.length;i++) hashMap.put(source[i], source[i]);
        System.out.println("java.util.HashMap: " +(System.currentTimeMillis() - time));

        time = System.currentTimeMillis();
        for (int i = 0;i < source.length;i++) hashTable.insert(source[i], source[i]);
        System.out.println("This: " + (System.currentTimeMillis() - time));

        System.out.println("col: " + hashTable.getCol());
    }
}
