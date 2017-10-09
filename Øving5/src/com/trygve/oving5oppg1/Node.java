package com.trygve.oving5oppg1;

/**
 * Created by Trygve on 12.09.2017.
 */
public class Node {
    private int key;
    private String value;

    public Node(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}

