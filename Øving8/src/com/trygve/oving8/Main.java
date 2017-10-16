package com.trygve.oving8;

public class Main {

    public static void main(String[] args) throws Exception{

        NodePath nodePath = new NodePath("vg1");
        System.out.println(nodePath.getStartLog());
        System.out.println(nodePath.showDijkstras(0));

        NodePath nodePath2 = new NodePath("vg1", true);
        System.out.println(nodePath2.getStartLog());
        System.out.println(nodePath2.showPrims());
		
		/*
        GPS gps = new GPS("vgSkandinavia","L7Skandinavia-navn");
        System.out.println(gps.getStartLog());
        System.out.println(gps.navigateD("Helsinki", "Trondheim"));
		*/
    }
}
