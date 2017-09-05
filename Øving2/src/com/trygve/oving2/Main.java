package com.trygve.oving2;

public class Main {

    public static void main(String[] args) {
        System.out.println(oppg2_1_1(2, 6));
        System.out.println(oppg2_1_2("bobob"));
        System.out.println(oppg2_2_3(2, 5));

        benchmark2_2_3(100000, 2, 8000);
        benchmark3(200000, 2, 8000);
    }

    private static void benchmark2_2_3(int p, double x, int n) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < p;i++) {
            oppg2_1_1(x, n);
        }
        System.out.println("Oppg 2.1-1. Times ran: " + p + "\nTime used: " + (System.currentTimeMillis() - startTime) + " mS");

        startTime = System.currentTimeMillis();
        for (int i = 0; i < p;i++) {
            oppg2_2_3(x, n);
        }
        System.out.println("Oppg 2.2-3. Times ran: " + p + "\nTime used: " + (System.currentTimeMillis() - startTime) + " mS");
    }

    private static void benchmark3(int p, double x, int n) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < p;i++) {
            Math.pow(x, n);
        }
        System.out.println("Math.Pow Times ran: " + p + "\nTime used: " + (System.currentTimeMillis() - startTime) + " mS");

        startTime = System.currentTimeMillis();
        for (int i = 0; i < p;i++) {
            oppg2_2_3(x, n);
        }
        System.out.println("Oppg 2.2-3. Times ran: " + p + "\nTime used: " + (System.currentTimeMillis() - startTime) + " mS");
    }

    private static double oppg2_1_1(double x, int n) {
        return ((n == 0) ? 1 : x * oppg2_1_1(x, n - 1));
    }

    private static boolean oppg2_1_2(String text) {
        if (text.length() <= 1) return true;
        if (Character.toLowerCase(text.charAt(0)) != Character.toLowerCase(text.charAt(text.length()-1))) return false;
        return oppg2_1_2(text.substring(1, text.length()-1));
    }

    private static double oppg2_2_3(double x, int n) {
        if (n == 0) return 1;
        if (n % 2 == 0) return x * x * oppg2_2_3(x, n/2);
        return  x * x * oppg2_1_1(x, n/2);
    }


}
