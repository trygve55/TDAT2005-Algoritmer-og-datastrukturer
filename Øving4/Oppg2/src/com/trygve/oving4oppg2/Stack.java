package com.trygve.oving4oppg2;

import java.util.EmptyStackException;

/**
 * Created by Trygve on 12.09.2017.
 */
public class Stack {
    int height = 0;
    int[] stack;

    public Stack(int size) {
        stack = new int[size];
    }

    public void push(int i) {
        if (isFull()) throw new StackOverflowError();
        stack[height] = i;
        height++;
    }

    public int pop() {
        if (isEmpty()) throw new EmptyStackException();
        height--;
        return stack[height];
    }

    public boolean isFull() {
        return (height == stack.length - 1);
    }

    public boolean isEmpty() {
        return (height == 0);
    }

    public String toString() {
        String string = "";
        char[] parantes = new char[]{'(',')','{','}','[',']'};
        for (int i = 0; i < height; i++) string += parantes[stack[i]] + ", ";
        return string;
    }
}
