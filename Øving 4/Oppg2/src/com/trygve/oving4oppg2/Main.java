package com.trygve.oving4oppg2;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception{
        String file = new Scanner(new File("test.java")).useDelimiter("\\Z").next();
        //System.out.println(file);

        char[] parantes = new char[]{'(',')','{','}','[',']'};

        Stack stack = new Stack(100);

        for (int i = 0; i < file.length();i++) {
            for (int p = 0;p < parantes.length;p++) {
                if (file.charAt(i) == parantes[p]) {
                    if (!stack.isEmpty()) {
                        int p2 = stack.pop();
                        if (p2 != p - 1) {
                            stack.push(p2);
                            stack.push(p);
                        }
                    } else {
                        stack.push(p);
                    }
                    //System.out.println(stack.toString());
                }
            }
        }

        if (stack.isEmpty()) System.out.println("No problems with code :)"); else System.out.println("Problem with code :(");
    }
}
