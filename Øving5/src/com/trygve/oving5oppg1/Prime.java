package com.trygve.oving5oppg1;

/**
 * Created by Trygve on 12.09.2017.
 */
public class Prime {

    public static int getPrime(int i) {
        long i2, dele = 2;
        while (true) {
            if (i % dele == 0) {
                i++;
                dele = 2;
            }
            i2 = i >> 1;
            if (dele > i2) return i;
            dele += 2;
            if (dele == 4) dele--;
        }
    }
}
