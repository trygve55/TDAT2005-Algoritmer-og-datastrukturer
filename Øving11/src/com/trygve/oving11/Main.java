package com.trygve.oving11;

import com.intellij.codeInsight.template.postfix.templates.SoutPostfixTemplate;

public class Main {

    public static void main(String[] args) {
        Automat automat = new Automat(new char[]{'0', '1'}, new int[]{1}, new int[][]{new int[]{3, 1, 2, 3}, new int[]{2, 2, 2, 1}});

        System.out.println("Automat 3a) 00*10*");
        printAT(automat, "");
        printAT(automat, "010");
        printAT(automat, "111");
        printAT(automat, "010110");
        printAT(automat, "0010000");

        automat = new Automat(new char[]{'a', 'b'}, new int[]{3}, new int[][]{new int[]{1, 4, 3, 3, 4}, new int[]{2, 3, 4, 3, 4}});

        System.out.println("\nAutomat 3b) (ab|ba)(a|b)*");
        printAT(automat, "abbb");
        printAT(automat, "aaab");
        printAT(automat, "babab");
    }

    public static void printAT(Automat automat, String input) {
        System.out.println(input + " : " + automat.sjekkInput(input.toCharArray()));
    }
}
