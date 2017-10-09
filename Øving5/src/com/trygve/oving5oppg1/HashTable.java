package com.trygve.oving5oppg1;

/**
 * Created by Trygve on 12.09.2017.
 */
public class HashTable {
    private int size;
    private Node[] array;
    private int col = 0;

    public HashTable(int size) {
        size = Prime.getPrime(size);
        this.size = size;
        array = new Node[size];
    }

    public int h1(String value) {
        int hash = 0;
        for (int i = 0; i < value.length();i++) hash += (value.charAt(i)+3)*(i + 1);
        return (hash % size);
    }

    public int h2(String value) {
        int hash = 0;
        for (int i = 0; i < value.length();i++) hash += Math.pow(value.charAt(i), i + 1);
        hash %= size;
        if (hash == 0) hash++;
        while (!relativePrime(hash, size)) hash++;
        return (hash);
    }

    public int getSize() {
        return size;
    }

    public void insert(String value) {
        insert(value, value);
    }

    public void insert(String keyString, String value) {
        int key = h1(keyString);
        int i = 1,index = key;
        while (array[index] != null) {
            System.out.println("Colition: " + array[index].getValue());
            index = key + (i * h2(value));
            i++;
            col++;
            index %= size;

        }
        array[key] = new Node(key, value);
    }

    public void delete(int key) {
        while (true) {
            if (array[key].getKey() == key) {
                array[key] = null;
            }
            key++;
            key %= size;
        }
    }

    public String get(String keyString) {
        int key = h1(keyString);

        int i = 1,index = key;
        while (i != size) {
            if (array[index] != null && array[index].getKey() == key) return array[index].getValue();
            else if (array[index] != null) System.out.println("Col: " + keyString + " " + array[index].getValue());
            System.out.println("Colition: " + array[index].getValue());
            index = key + (i * h2(keyString));
            i++;
            col++;
            index %= size;
        }
        return null;
    }

    public double getLast() {
        int sum = 0;
        for (int i = 0;i < array.length;i++) if (array[i] != null) sum++;
        return ((double) sum / size);
    }

    public int getCol() {
        return col;
    }

    private boolean relativePrime(int a, int b) {
        int t;
        while(b != 0){
            t = a;
            a = b;
            b = t%b;
        }
        return (a == 1);
    }
}
