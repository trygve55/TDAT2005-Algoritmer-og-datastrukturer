package com.trygve.oving3;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int repeat = 20;

        //oppg1
        System.out.println("#oppg1");
        for (int threshold = 10; threshold < 300; threshold+= 5) {

            int[] orginalArray = getRandomArray(500000, 20000);
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < repeat; i++) {
                int[] array = copyArray(orginalArray);
                Sort.quickSort1(array, threshold);
                //System.out.println("Sort valid: " + Sort.validateSort(array));
            }
            System.out.println("threshold: " + threshold + ", time: " + (double) (System.currentTimeMillis() - startTime) / repeat + " mS");
        }
    }

    public static int[] getRandomArray(int size, int maxInt) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0;i < size;i++) array[i] = random.nextInt(maxInt);
        return array;
    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i != array.length -1) {
                System.out.print(", ");
            } else {
                System.out.println();
            }
        }
    }

    public static int[] copyArray(int[] array) {
        int[] returnArray = new int[array.length];
        for (int i = 0; i < array.length;i++) returnArray[i] = array[i];
        return returnArray;
    }
}
