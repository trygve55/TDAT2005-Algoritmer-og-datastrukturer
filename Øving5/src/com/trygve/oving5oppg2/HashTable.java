package com.trygve.oving5oppg2;

import java.util.NoSuchElementException;

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

    public int hash(int key) {
        if (key < 0) return -(key % size);
        return (key % size);
    }

    public int getSize() {
        return size;
    }

    public void insert(int key, int value) {
        int index = hash(key);
        while (array[index] != null) {
            index++;
            index %= size;
            col++;
        }
        array[index] = new Node(key, value);
    }

    public void delete(int key) {
        int index = hash(key);
        while (true) {
            if (array[index].getKey() == key) {
                array[index] = null;
            }
            index++;
            index %= size;
        }
    }

    public int get(int key) {
        int index = hash(key);
        int startIndex = index;
        while (true) {
            if (array[index] != null && array[index].getKey() == key) return array[index].getValue();
            index++;
            index %= size;
            if (index == startIndex) throw new NoSuchElementException();
        }
    }

    public int getCol() {
        return col;
    }
}
