package com.trygve.oving13;

public class Main {

    public static void main(String[] args) throws Exception{

        GPS gps = new GPS("kanter.txt","interessepkt.txt", "noder.txt");
        System.out.println(gps.getStartLog());
        System.out.println(gps.navigateD("Oslo", "Stureby"));
        System.out.println();
        System.out.println(gps.navigateA("Oslo", "Stureby"));
    }
}
