package com.trygve.oving5oppg1;

import com.intellij.codeInsight.template.postfix.templates.SoutPostfixTemplate;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) throws Exception {
        HashTable hashTable = new HashTable(35);

        try (BufferedReader br = new BufferedReader(new FileReader("navn.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                hashTable.insert(line);
            }
        }

        System.out.println("\nCaro:" + hashTable.h1("Caro"));
        System.out.println("Cora:" + hashTable.h1("Cora"));

        System.out.println("\nCol insert: " + hashTable.getCol());
        System.out.println("Last: " + hashTable.getLast());
    }
}
